-- If this query returns any rows, it means that there are values in column1 of tableA which are not in column2 of tableB,
--  i.e., column1 of tableA is not fully contained in column2 of tableB. If it returns no rows, then it means column1 is fully contained in column2.

SELECT column1
FROM tableA
WHERE NOT EXISTS (
  SELECT 1
  FROM tableB
  WHERE tableB.column2 = tableA.column1
);

-- If the above query returns no rows, then column1 of tableA is fully contained in column2 of tableB.
-- If the query returns one or more rows, then those are the values that exist in column1 of tableA but not in column2 of tableB.

-- Select Distinct Method
SELECT DISTINCT col1 -- col1 should match col2 
FROM table1 -- Subset
WHERE col1 NOT IN (SELECT DISTINCT col2 FROM table2); -- Superset
