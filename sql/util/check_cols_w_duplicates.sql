SELECT column_name, COUNT(column_name) 
FROM table_name 
GROUP BY column_name 
HAVING COUNT(column_name) > 1;
