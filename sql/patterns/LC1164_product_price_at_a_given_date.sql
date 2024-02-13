/* Write your PL/SQL query statement below */
select product_id, FIRST_VALUE(new_price) over (partition by product_id order by change_date desc) as price
from Products
where change_date <= '2019-08-16'
union
select
distinct product_id, 10 as price from Products
where product_id not in (select product_id from Products where change_date <= '2019-08-16');