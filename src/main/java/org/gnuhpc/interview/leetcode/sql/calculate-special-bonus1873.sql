select employee_id
     ,(case when left(name,1) <> 'M' and employee_id % 2 = 1 then salary
            else 0 end) bonus
from Employees
order by employee_id;