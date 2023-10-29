-- Insert columns from another table
INSERT INTO target_table (column1, column2, ... columnN)
SELECT columnA, columnB, ... columnZ
FROM source_table
WHERE some_condition;

-- Add a column
ALTER TABLE your_table_name
ADD new_column_name data_type;

-- Set all values on the column
UPDATE your_table_name
SET new_column_name = 'your_value';

-- Modify a column
ALTER TABLE table_name
MODIFY column_name new_data_type;