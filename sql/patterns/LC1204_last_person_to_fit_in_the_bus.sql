/* Write your PL/SQL query statement below */
select person_name
from (select person_name, sum(weight) over (order by turn) as sum_weight from queue
    order by sum_weight desc)
where sum_weight <= 1000
    and rownum = 1;