-- Utility Script for timing queries and estimation in Oracle SQL Developer

-- Enable timing
SET TIMING ON;

-- Generate execution plan for the query
EXPLAIN PLAN FOR
SELECT * FROM your_table;

-- Display the execution plan
SELECT * FROM TABLE(DBMS_XPLAN.DISPLAY);

-- Your SQL query here
SELECT * FROM your_table;

-- Disable timing (optional)
SET TIMING OFF;