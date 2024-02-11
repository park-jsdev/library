/* Write your PL/SQL query statement below */
select distinct num as ConsecutiveNums
from (
    select id, num,
    lag(num) over(order by id) as prev_num,
    lead(num) over(order by id) as next_num
    from Logs
) l
where l.num = l.prev_num
and l.prev_num = l.next_num
and l.num = l.next_num;