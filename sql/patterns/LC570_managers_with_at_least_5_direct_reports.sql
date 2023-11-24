select e1.name from Employee e1
inner join (
  select managerId from Employee group by managerId having COUNT(managerId) >= 5
) e2 on e1.id = e2.managerId