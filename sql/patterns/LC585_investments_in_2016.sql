/* Write your PL/SQL query statement below */
with cte as (
select tiv_2016,
count(tiv_2015) over(partition by tiv_2015 order by null) same_2015,
count(lat||lon) over(partition by lat||':'||lon order by null) same_loc
from insurance)
select sum(tiv_2016) tiv_2016 from cte where same_loc = 1 and same_2015 > 1