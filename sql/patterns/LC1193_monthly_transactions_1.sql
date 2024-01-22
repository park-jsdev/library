select TO_CHAR(trans_date, 'YYYY-MM') AS month,
country, 
count(id) trans_count, 
sum(case when state = 'approved' then 1 else 0 end) approved_count,
sum(amount) trans_total_amount,
sum(case when state='approved' then amount else 0 end) as
approved_total_amount 
from Transactions
group by TO_CHAR(trans_date, 'YYYY-MM'), country