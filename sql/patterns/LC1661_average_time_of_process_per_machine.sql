-- First we need to join the same table to have starting time and ending time in the same line
-- select a1.machine_id, a1.timestamp, a2.timestamp
-- from Activity a1
-- join Activity a2
-- on a1.process_id = a2.process_id
-- and a1.machine_id = a2.machine_id
-- and a1.timestamp < a2.timestamp;

-- Then calculate the difference between timestamps and group the values by machine id
select a1.machine_id,
ROUND(AVG(a2.timestamp - a1.timestamp), 3) as processing_time
from Activity a1
join Activity a2
on a1.process_id = a2.process_id
and a1.machine_id = a2.machine_id
and a1.timestamp < a2.timestamp
group by a1.machine_id;