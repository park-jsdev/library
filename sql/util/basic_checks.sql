-- Basic Checks for data queries:
-- - TDD (if possible)
-- - Check row count before + after (per change)
-- - Check hash vs original (if exists)
-- - Check for duplicates
-- - End-to-end (random, typical, and edge case)

-- Quick check: https://stackoverflow.com/questions/26538625/how-to-count-unique-rows-in-oracle
-- Check distinct rows and make sure they are equal to the rows
SELECT COUNT(*) 
FROM (SELECT DISTINCT * FROM Table);

SELECT COUNT(*) FROM Table;

-- https://stackoverflow.com/questions/59232/how-do-i-find-duplicate-values-in-a-table-in-oracle
-- This only checks by column

-- Check for duplicates in 1 column
select col_name, count(*)
from table_name
group by col_name
having count(*) > 1;

-- Check all columns for duplicates
select * from col_name
where col_name 
in (
    select col_name 
    from table_name 
    group by col_name 
    having count(*) > 1
    ) 

