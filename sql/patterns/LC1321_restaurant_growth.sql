/* Write your PL/SQL query statement below */
select visited_on, amount, average_amount
from (
    select visited_on,
        sum(amount) over (order by visited_on rows 6 preceding) as amount,  -- sum of amount for current day and 6 days prior
        round(avg(amount) over (order by visited_on rows 6 preceding), 2) as average_amount, -- average amount for curr day and 6 days prior
        row_number() over (order by visited_on) as rank_date
    from
        -- identify total amount per day
        (
            select to_char(visited_on, 'YYYY-MM-DD') as visited_on, sum(amount) as amount
            from customer
            group by to_char(visited_on, 'YYYY-MM-DD')
        )
)
where rank_date > 6 -- a 7 day window starts minimum after rank_date 6
order by 1;