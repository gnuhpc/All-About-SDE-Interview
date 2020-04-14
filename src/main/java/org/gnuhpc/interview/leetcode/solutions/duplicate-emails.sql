select t.Email
from (select Email, count(Email) as c from Person group by Email) t where c>1;

select Email
from Person
group by Email
having count(Email) > 1;

#如果要对分组查询的结果进行筛选，可以使用having子句