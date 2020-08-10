select name, bonus
from Employee left join Bonus
on Employee.EmpId = Bonus.EmpId
where bonus is null or bonus < 1000;