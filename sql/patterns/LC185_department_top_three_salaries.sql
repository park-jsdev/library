/* Write your PL/SQL query statement below */
select dept.name as department, emp.name as employee, emp.salary as salary
from department dept
join employee emp
on emp.departmentid = dept.id
where 3 > (select count(distinct emp1.salary)
from employee emp1
where emp1.salary > emp.salary
and emp.departmentid = emp1.departmentid)