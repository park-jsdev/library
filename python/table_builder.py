'''
Table Builder:
Write an SQL query to CREATE Table in Oracle into a sql file based on the input file.

Setup:
- Input file should be in same dir (or specify path).
- Output is 'query.sql' in the same dir, make sure to delete after use and rerunning this script.
- Confirm DB encoding: (UTF-8), and language parameters (to use NVARCHAR2).

Instructions:
- Specify file name in constants. Preferably copy as input.xlsx in same dir.
- Specify the rows to skip such that the first row in the df will be the header row.
- Specify Schema name and Table Series
- Tweak table name manually in the script.

Considerations:
- Check Oracle 'insert data' to troubleshoot issues.
- Remember to skip header and footer rows when inserting data in Oracle as well. Can do via SQL, or manually remove rows in xl copy.
- There are cases that are not caught by the script, do manual tweaking.
- For column names that are mapped to duplicate names, manually create appropriate names for the SQL query.
'''

import pandas as pd
from unidecode import unidecode
import re

HISTORICAL = True
CHARACTER_BYTES = 4 # UTF-8
BUFFER_DEFAULT = 5 # Consider future data and allocate extra byte size
FILE_NAME = "input.xlsx" # copy file to same dir to resolve path issues 
ROWS_TO_SKIP = 10 # from input file
FOOTER_TO_SKIP = 0 # from input file
SCHEMA_NAME = 'SCHEMA_NAME'
TABLE_SERIES = 'SERIES_NAME'

# From https://www.geeksforgeeks.org/append-digits-to-the-end-of-dupicate-strings-to-make-all-strings-in-an-array-unique/
def replace_duplicates_in_list(lst):
    # Store the frequency of strings
    hash = {}
    res = lst # Build a copy list rather than in-place modification
    # Iterate over the array
    for i in range(0, len(res)):
        # For the first occurrence, update the frequency count
        if res[i] not in hash:
            hash[res[i]] = 2
        else:
            count = hash[res[i]]
            hash[res[i]] += 1
            # Append frequency count

            # to end of the string
            res[i] += str(count)

    # Print the modified array
    #for i in range(0, len(lst)):
        #print(lst[i])
    return res

df = pd.read_excel(FILE_NAME, skiprows=ROWS_TO_SKIP) # skip header rows
df = df[:(len(df)-FOOTER_TO_SKIP)] # cut footer rows

# Make columns list
df.columns = df.columns.str.replace(r'\n',' ', regex=True) 
col_list = df.columns.values.tolist()
col_list  = list(filter(None, col_list))
col_list  = [s.strip() for s in col_list]
col_list  = [' '.join(s.split()) for s in col_list]
col_list  = [s.replace(" ", "_") for s in col_list]
col_list  = [s.replace("-", "_") for s in col_list]
col_list  = [s.replace("(", "") for s in col_list]
col_list  = [s.replace(")", "") for s in col_list]
col_list  = [s.replace("/", "_") for s in col_list]
col_list  = [s.replace("\\", "_") for s in col_list]
col_list  = [s.replace("&", "AND") for s in col_list] # & is a substitution variable in Oracle SQL
col_list  = [re.sub('\_\_+', '_', s) for s in col_list] # Replace 2 or more _ with one
col_list  = [unidecode(s.replace("'", "").upper()) for s in col_list]
col_list  = [s.replace(s,s[0:30]) for s in col_list] # Only take the first 30 characters of the name to meet Oracle reqmt
col_list  = [s.rstrip("_") for s in col_list] # strip the last char if it is _ for readability

# Handle duplicate mapped column names
dup = {x for x in col_list if col_list.count(x) > 1}
if (len(dup)>1):
    print("duplicate columns. Please change column names manually, such that they are under 30 characters in SQL: ")
    print(dup)
    
    '''
    If the preprocessing of column names result in duplicates, it will cause the duplicates to be put into a dataframe.
    Handle by adding suffix i, ..., i+1, etc. It will result in char len > 30 and Oracle will raise an error, so handle manually
    (decide your own col name in this case).
    '''
    col_list = sql_helper.replace_duplicates_in_list(col_list)

df.columns = col_list

# Get data type of each column in df and write to dict
data_types_dict = df.dtypes.to_dict()

# Init sql script
sql_script_lst = [[0 for x in range(2)] for y in range(len(data_types_dict))] # Python 2d Array: https://stackoverflow.com/questions/2397141/how-to-initialize-a-two-dimensional-array-in-python
i = 0

# need to update script, Date and number cant have lengths

# populate sql dict
for k,v in data_types_dict.items():
    
    # Create attribute dictionary per key (columnn in df)
    attributes_dict = dict()
    default_max_len = 4000 # the max length of varchar2 or nvarchar2 is 4000 bytes by default
    
    # Data Type and Max Length
    if v == 'object':
        # get max len
        '''
        There are multiple approaches: https://stackoverflow.com/questions/21295334/find-length-of-longest-string-in-pandas-dataframe-column
        Take the max of the 3 (byte len, utf-8 str len, str len) for most coverage.
        '''
        try:
            k_bytes_len = int(df[k].astype(str).str.encode('utf-8').astype(bytes).str.len().max()) # Find max byte length by first encoding as UTF8
            k_u_str_len = int(df[k].astype(str).str.encode('utf-8').str.len().max())
            k_str_len = int(df[k].astype(str).str.len().max())
            
            # Get the std of the length of the col (for variable length data types)
            std = int(df[k].astype(str).str.encode('utf-8').astype(bytes).str.len().std())
            
            # Create buffer based on std dev of length of col, take the greater of it and the default buffer len
            '''
            Assuming Normal Distribution, 1 std dev from upper bound is ≈ 4σ, using the 3σ rule: https://en.wikipedia.org/wiki/68%E2%80%9395%E2%80%9399.7_rule
            '''
            buffer = max(BUFFER_DEFAULT, std)
            
            # Take the max of the 3 calcs, add buffer bytes, then round up to the nearest 10
            k_max_len = round((max(max(k_bytes_len, k_u_str_len), k_str_len) * CHARACTER_BYTES + buffer)/10)*10
            
            # print("col " + str(k) + ": strlen 1σ: " + str(std) + ", kmax: " + str(k_max_len))
        except:
            print("Error in calculating col max len in "+ str(k) + ", using default len 255 bytes. Manually correct in SQL if issue persists.")
            k_max_len = 255
        
        # Choose CLOB or NVARCHAR2 based on len
        if (k_max_len > 255):
            attributes_dict['dtype'] = 'CLOB' # Use CLOB for long strings like comments
            attributes_dict['len'] = str(min(k_max_len , default_max_len)) # take the lesser of the found max len and default
        else:
            attributes_dict['dtype'] = 'VARCHAR2'
            attributes_dict['len'] = str(min(k_max_len , default_max_len)) # take the lesser of the found max len and default
    elif v == 'datetime64[ns]':
        
        # do a check for TIMESTAMP here
        
        attributes_dict['dtype'] = 'DATE'
    elif v == 'int64' or 'float64':
        attributes_dict['dtype'] = 'NUMBER'
    else:
        attributes_dict['dtype'] = 'VARCHAR2' # default
        attributes_dict['len'] = '255' # default
          
    # Assign attributes to the key (col)
    sql_script_lst[i][0] = k # index 0 = col name
    sql_script_lst[i][1] = attributes_dict # index 1 = attributes 
    i += 1
    
# Write to .sql file
f = open("query.sql", "w")

# SQL Script

# If Historical Table, add suffix and Date As Of col
if (HISTORICAL == True):
    print("CREATE TABLE " + SCHEMA_NAME + "." + TABLE_SERIES + "_ <insert number> _H" + "(")
    f.write("CREATE TABLE " + SCHEMA_NAME + "." + TABLE_SERIES + "_ <insert number> _H" + "( \n")
    print("AS_OF_DATE DATE,")
    f.write("AS_OF_DATE DATE,\n")
else:
    print("CREATE TABLE " + SCHEMA_NAME + "." + TABLE_SERIES + "_ <insert number>" + "(")
    f.write("CREATE TABLE " + SCHEMA_NAME + "." + TABLE_SERIES + "_ <insert number>" + "( \n")

#print("id NUMBER GENERATED BY DEFAULT AS IDENTITY,")
#f.write("id NUMBER GENERATED BY DEFAULT AS IDENTITY, \n")   

# Strip last comma
if (len(sql_script_lst) > 1):
    for x in range(len(sql_script_lst)-1): 
        # Only need length parameter if dtype is VARCHAR or NVARCHAR
        if(sql_script_lst[x][1]['dtype'] == 'NVARCHAR2' or sql_script_lst[x][1]['dtype'] == 'VARCHAR2'):
            print(sql_script_lst[x][0] + " " + sql_script_lst[x][1]['dtype'] + '(' + sql_script_lst[x][1]['len'] + '),')
            f.write(sql_script_lst[x][0] + " " + sql_script_lst[x][1]['dtype'] + '(' + sql_script_lst[x][1]['len'] + '),\n')
        else:
            print(sql_script_lst[x][0] + " " + sql_script_lst[x][1]['dtype'] + ",")
            f.write(sql_script_lst[x][0] + " " + sql_script_lst[x][1]['dtype'] + ",\n")
    
    x = len(sql_script_lst)-1 # Set x to last line, no comma for this row
    # Only need length parameter if dtype is VARCHAR or NVARCHAR
    if(sql_script_lst[x][1]['dtype'] == 'NVARCHAR2' or sql_script_lst[x][1]['dtype'] == 'VARCHAR2'):
        print(sql_script_lst[x][0] + " " + sql_script_lst[x][1]['dtype'] + '(' + sql_script_lst[x][1]['len'] + ')')
        f.write(sql_script_lst[x][0] + " " + sql_script_lst[x][1]['dtype'] + '(' + sql_script_lst[x][1]['len'] + ')\n')
    else:
        print(sql_script_lst[x][0] + " " + sql_script_lst[x][1]['dtype'])
        f.write(sql_script_lst[x][0] + " " + sql_script_lst[x][1]['dtype'] + "\n")
else:
    for x in range(len(sql_script_lst)): 
        # Only need length parameter if dtype is VARCHAR or NVARCHAR
        if(sql_script_lst[x][1]['dtype'] == 'NVARCHAR2' or sql_script_lst[x][1]['dtype'] == 'VARCHAR2'):
            print(sql_script_lst[x][0] + " " + sql_script_lst[x][1]['dtype'] + '(' + sql_script_lst[x][1]['len'] + '),')
            f.write(sql_script_lst[x][0] + " " + sql_script_lst[x][1]['dtype'] + '(' + sql_script_lst[x][1]['len'] + '),\n')
        else:
            print(sql_script_lst[x][0] + " " + sql_script_lst[x][1]['dtype'] + ",")
            f.write(sql_script_lst[x][0] + " " + sql_script_lst[x][1]['dtype'] + ",\n")

#print("PRIMARY KEY(id)")
#f.write("PRIMARY KEY(id) \n")  
print(");")
f.write(");")  
f.close()


