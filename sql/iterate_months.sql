DECLARE
    v_asof_date DATE;
    v_year NUMBER := EXTRACT(YEAR FROM SYSDATE); 
    v_month VARCHAR2(3);

BEGIN
    FOR i IN 1..12 LOOP
        -- Compute the date for the first day of the month
        v_asof_date := TO_DATE('01-' || i || '-' || v_year, 'DD-MM-YYYY');
        v_month := TO_CHAR(v_asof_date, 'MON');

        -- Populate the temporary table with data for the given month
        INSERT INTO temp_table
        SELECT * FROM your_complex_query_view 
        WHERE some_date_column = v_asof_date;

        -- Transfer data from the temp table to the main table
        INSERT INTO your_main_table (col1, col2, month, fiscal_year, ...)
        SELECT col1, col2, v_month, your_fiscal_year_logic_here, ...
        FROM temp_table;

        -- Clear the temporary table for the next iteration
        DELETE FROM temp_table;

    END LOOP;
    
    COMMIT;
END;
