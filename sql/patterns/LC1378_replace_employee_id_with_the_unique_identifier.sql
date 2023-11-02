-- LEFT JOIN USING
SELECT unique_id, name FROM Employees LEFT OUTER JOIN EmployeeUNI
USING (id); -- can also use ON
