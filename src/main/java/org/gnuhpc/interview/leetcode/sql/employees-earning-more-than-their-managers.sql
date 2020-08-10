select a.Name as Employee from Employee a, Employee b where a.ManagerId = b.Id and a.Salary > b.Salary;

SELECT
     a.NAME AS Employee
FROM Employee AS a JOIN Employee AS b
     ON a.ManagerId = b.Id
     AND a.Salary > b.Salary
;

select a.Name as Employee
from (
select p.Name,p.Salary,p.ManagerId
from Employee p
where p.ManagerId is not null
) a
left join Employee s on a.ManagerId = s.Id
where a.Salary > s.Salary

