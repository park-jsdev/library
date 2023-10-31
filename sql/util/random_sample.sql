-- Sample a subset of 10% randomly from the main dataset and store the seed.
-- Run complex queries on smaller datasets before on entire database. 
SELECT * FROM your_table SAMPLE (10) SEED (12345);