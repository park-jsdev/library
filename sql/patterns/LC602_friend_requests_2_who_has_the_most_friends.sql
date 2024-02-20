/* Write your PL/SQL query statement below */
with cte as (
    select id, count(*) as num from (
        select requester_id as id from RequestAccepted
        union all
        select accepter_id as id from RequestAccepted
    ) group by id
) select id, num from cte where num = (select max(num) from cte);