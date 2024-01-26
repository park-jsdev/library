select TO_CHAR(activity_date,'YYYY-MM-DD') as day, 
count(distinct user_id) as active_users
from Activity
where activity_date BETWEEN '2019-06-28' AND '2019-07-27'
group by activity_date