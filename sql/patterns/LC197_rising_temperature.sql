/* LAG solution */
SELECT id FROM (
    SELECT
        id,
        temperature,
        recordDate,
        LAG(recordDate) OVER (ORDER BY recordDate) AS prevDate,
        LAG(temperature) OVER (ORDER BY recordDate) AS prevTemp
    FROM Weather
) WHERE temperature > prevTemp AND recordDate - prevDate = 1;


-- Inner Join Solution
-- select a.id from
--  weather a,
--  weather b
--  where a.recorddate-1 = b.recorddate 
--  and a.temperature > b.temperature;