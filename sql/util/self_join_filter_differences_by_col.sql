-- Find rows where columnA has the same value but columnB has different values.
-- t1 and t2 are aliases, but my_table should be the same name
SELECT t1.columnA, t1.columnB, t2.columnB AS columnB2
FROM my_table t1
JOIN my_table t2 ON t1.columnA = t2.columnA
WHERE t1.columnB <> t2.columnB;
