select
t.Name as Department,
e.Name as Employee,
e.salary as Salary
from
(select DepartmentId,
d.Name as Name,
max(Salary) as Salary
FROM Employee as e join Department as d
on d.id=e.DepartmentId
group by DepartmentId) as t
join Employee e
on e.DepartmentId=t.DepartmentId and e.Salary=t.Salary;

SELECT d.Name AS Department,e.Name AS Employee, e.Salary
FROM Employee as e JOIN Department as d ON e.DepartmentId = d.Id
WHERE (e.DepartmentId,Salary) IN
      (
          SELECT DepartmentId,MAX(Salary)
          FROM Employee
          GROUP BY DepartmentId
      )
;

