
DECLARE
    v_year NUMBER:= 2021;
    v_date DATE;
    v_month_abbr VARCHAR2(3);

BEGIN
    FOR i IN 1..12 LOOP
        v_date := TO_DATE('01-' || i || '-' || v_year, 'DD-MM-YYYY');
        v_month_abbr :=
            CASE i
                WHEN 1 THEN 'JAN'
                WHEN 2 THEN 'FEB'
                WHEN 3 THEN 'MAR'
                WHEN 4 THEN 'APR'
                WHEN 5 THEN 'MAY'
                WHEN 6 THEN 'JUN'
                WHEN 7 THEN 'JUL'
                WHEN 8 THEN 'AUG'
                WHEN 9 THEN 'SEP'
                WHEN 10 THEN 'OCT'
                WHEN 11 THEN 'NOV'
                WHEN 12 THEN 'DEC'

            END;
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