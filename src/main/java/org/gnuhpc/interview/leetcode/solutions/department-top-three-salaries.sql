select d.Name Department,t1.Name Employee,Salary
from Department d,
(select DepartmentId,Id,Name,Salary,dense_rank()over(partition by DepartmentId order by salary desc) rank
from Employee)t1
where t1.rank<=3
and t1.DepartmentId=d.Id;

--# topN问题 sql模板
--select *
--from (
--   select *,
--          row_number() over (partition by 要分组的列名
--                       order by 要排序的列名 desc) as 排名
--   from 表名) as a
--where 排名 <= N;
--链接：https://leetcode-cn.com/problems/department-top-three-salaries/solution/tu-jie-sqlmian-shi-ti-jing-dian-topnwen-ti-by-houz/


SELECT
	Department.NAME AS Department,
	e1.NAME AS Employee,
	e1.Salary AS Salary
FROM
	Employee AS e1,Department
WHERE
	e1.DepartmentId = Department.Id
	AND (SELECT  count( DISTINCT e2.Salary )
			 FROM	Employee AS e2
			 WHERE	e1.Salary < e2.Salary AND e1.DepartmentId = e2.DepartmentId)<3 #不分组的原因：这个语句就是要求比较的时候，是同一个部门
			 #取前三，那么某个数比它大的数就不能超过3个，所以这里的条件写成（select XXX）< 3 更好理解。
ORDER BY Department.NAME,Salary DESC

