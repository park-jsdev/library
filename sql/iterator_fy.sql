DECLARE
    v_year NUMBER:= 2020; -- Change the year only
    v_fiscal_year VARCHAR2(20):= TO_CHAR(v_year) || '-' || TO_CHAR(v_year + 1);
    v_date DATE;
    v_month_abbr VARCHAR2(3);

BEGIN
    FOR i IN 1..12 LOOP
        v_month_abbr :=
            CASE i
                WHEN 1 THEN 'APR'
                WHEN 2 THEN 'MAY'
                WHEN 3 THEN 'JUN'
                WHEN 4 THEN 'JUL'
                WHEN 5 THEN 'AUG'
                WHEN 6 THEN 'SEP'
                WHEN 7 THEN 'OCT'
                WHEN 8 THEN 'NOV'
                WHEN 9 THEN 'DEC'
                WHEN 10 THEN 'JAN'
                WHEN 11 THEN 'FEB'
                WHEN 12 THEN 'MAR'
            END;

            -- Due to Fiscal Year
            IF i <= 10 THEN
                v_year := v_year + 1;
            END IF;

            v_date := LAST_DAY(TO_DATE('01-' || 
             CASE
                WHEN i <= 9 THEN TO_CHAR(i + 3) -- Apr=1, May=2, ..., Dec=9
                ELSE TO_CHAR(i - 9)           -- Jan=10, Feb=11, Mar=12
            END || '-' || v_year, 'DD-MM-YYYY')); -- get the last day of the month
            INSERT INTO temp_table

            SELECT * FROM (
            -- Begin Query

 

            -- End Query

            -- Process and Transfer Data
            INSERT INTO curr_Table (
               -- cols
            )

            SELECT
                -- cols
            FROM temp_table;
            DELETE FROM temp_table;
        END LOOP;
    COMMIT;
END;