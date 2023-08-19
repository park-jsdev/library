## Table Builder

Encodings:
- Max bytes per char in UTF-8 (4): https://www.ibm.com/docs/en/db2-for-zos/12?topic=unicode-utfs
- DB Parameter must be set to use NVARCHAR2: https://www.oracletutorial.com/oracle-basics/oracle-nvarchar2/
- Consider the Max size of each row when using UTF8 encoding: https://stackoverflow.com/questions/13506832/what-is-the-mysql-varchar-max-size
- It is better to use max length of bytes and use byte length to set the size for VARCHAR2: https://stackoverflow.com/questions/21295334/find-length-of-longest-string-in-pandas-dataframe-column
- However, NVARCHAR2 can only use char length as max size parameter, in that case perform a conversion from bytes to char length,
but keep in mind the encoding (UTF8): https://stackoverflow.com/questions/11131958/what-is-the-maximum-characters-for-the-nvarcharmax
- Oracle Data Types Max size limits: https://docs.oracle.com/en/database/oracle/oracle-database/19/refrn/datatype-limits.html#GUID-963C79C9-9303-49FE-8F2D-C8AAF04D3095
- Some Date datatypes require TIMESTAMP rather than DATE. May need to change manually.
- Long strings such as comments (require VARCHAR2 of byte len > 1000) should be BLOB or CLOB.