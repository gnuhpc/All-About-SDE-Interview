delete from Person
where id not in
(select need.id from
((select min(Id) as id from Person  group by Email) as need ));

--请问下面这么写为什么不对呢，为什么一定要把min id二次提取呢？
--
--delete from Person
--where id not in
--(select min(Id) as id from Person  group by Email)

-- Answer: 找到原因了，这是因为mysql不允许同时对一个表delete和select联合操作