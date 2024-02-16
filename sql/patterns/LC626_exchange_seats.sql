/* Write your PL/SQL query statement below */
select id, NVL(
    case when mod(id, 2) = 1 then lead(student, 1) over (order by id)
    else lag(student, 1) over (order by id) end,
    student
) student
from seat;