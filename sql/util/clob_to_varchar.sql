-- Script when altering table columns from clob to varchar2 is needed

-- Check Table statistics for an estimates size, max value may be possible

-- See if the col contains any value that cannot be stored in a VARCHAR2 (32767 in PL/SQL, 4000 in SQL)
SELECT COUNT(1)
FROM table_name
WHERE DBMS_LOB.GETLENGTH(col_name) ) 32767;

-- Test col individually (manually) to see the max length
SELECT MAX(LENGTHB(TO_CHAR(SUBSTR(column_name,1,32767))))
FROM table_name;

-- SQL to generate more SQL queries without procedural code:
SELECT 'SELECT MAX(LENGTHB(TO_CHAR(SUBSTR(' || column_name || ',1,32767)))) FROM ' || table_name || ';' 
FROM all_tab_columns 
WHERE table_name = 'YOUR_TABLE_NAME' 
AND data_type = 'CLOB';


-- Procedural Code:

-- Binary Search to find min length where count is 0 (one column):
DECLARE
  v_sql VARCHAR2(1000);
  v_count NUMBER;
  v_low NUMBER := 1;  -- Lower bound of length
  v_high NUMBER := 1000; -- Upper bound of length
  v_mid NUMBER;
  v_table_name VARCHAR2(50) := 'table_name'; 
  v_col_name VARCHAR2(50) := 'col_name'; 
BEGIN
  LOOP
    v_mid := FLOOR((v_low + v_high) / 2);
    v_sql := 'SELECT COUNT(1) FROM ' || v_table_name || 
             ' WHERE LENGTHB(TO_CHAR(SUBSTR(' || v_col_name || ', 1, 32767))) > ' || v_mid;
             
    EXECUTE IMMEDIATE v_sql INTO v_count;
    
    IF v_count = 0 THEN
      v_high := v_mid - 1;
    ELSE
      v_low := v_mid + 1;
    END IF;
    
    EXIT WHEN v_low > v_high;
  END LOOP;
  
  DBMS_OUTPUT.PUT_LINE('Recommended max length for column ' || v_col_name || ' is: ' || v_high);
END;
/

-- Iterate all cols of the table on CLOB columns
DECLARE
  v_sql VARCHAR2(1000);
  v_count NUMBER;
  v_low NUMBER;
  v_high NUMBER;
  v_mid NUMBER;
  v_table_name VARCHAR2(50) := 'table_name';
BEGIN
  FOR rec IN (SELECT column_name
              FROM user_tab_columns
              WHERE table_name = UPPER(v_table_name)
                AND data_type = 'CLOB') LOOP
    
    v_low := 1;  -- Lower bound of length
    v_high := 1000;  -- Upper bound of length

    LOOP
      v_mid := FLOOR((v_low + v_high) / 2);
      v_sql := 'SELECT COUNT(1) FROM ' || v_table_name || 
               ' WHERE LENGTHB(TO_CHAR(SUBSTR(' || rec.column_name || ', 1, 32767))) > ' || v_mid;
               
      EXECUTE IMMEDIATE v_sql INTO v_count;
      
      IF v_count = 0 THEN
        v_high := v_mid - 1;
      ELSE
        v_low := v_mid + 1;
      END IF;
      
      EXIT WHEN v_low > v_high;
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('Recommended max length for column ' || rec.column_name || ' is: ' || v_high);
  END LOOP;
END;
/
