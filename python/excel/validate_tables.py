# Validate Tables
# @author: Junsoo Park
#
# This program will take two excel files and compare their common columns to see if they match.
# It assumes that the first table is a subset of the second (i.e. the first table is a view of the second).
# It allows the user to specify the actual column names if they are not matching already.
# Use Section A in the case that column names are not matching.

# Install pandas 'pip install pandas'
import pandas as pd

# Reading Excel files and converting them to data frames
file1 = pd.read_excel('path/to/file1.xlsx')
file2 = pd.read_excel('path/to/file2.xlsx')

# Subsetting data frames to include only the common columns
common_cols = list(file1.columns)
file2 = file2[common_cols]

# SECTION A
# Uncomment this section if column names of the tables are not matching
# ---------------------------------------------------------------------------------------
# Specifying column names in both tables
# file1_cols = ['col1', 'col2', 'col3']  # Replace with actual column names in file1
# file2_cols = ['colA', 'colB', 'colC']  # Replace with actual column names in file2

# Subsetting data frames to include only the common columns
# common_cols = list(set(file1_cols).intersection(file2_cols))
# file1 = file1[common_cols]
# file2 = file2[common_cols]

# END A
# ---------------------------------------------------------------------------------------

# Looping through rows and comparing values
for index, row in file1.iterrows():
    match = True
    for col in common_cols:
        # If both values are missing, assume match
        if pd.isna(row[col]) or pd.isna(file2.iloc[index][col]):
            if pd.isna(row[col]) and pd.isna(file2.iloc[index][col]):
                continue
            else:
                # If one is missing, is not matching
                match = False
                break
        elif row[col] != file2.iloc[index][col]:
            match = False
            break
    if match:
        print(f"Row {index+1} matches in both files.")
    else:
        print(f"Row {index+1} does not match in both files.")