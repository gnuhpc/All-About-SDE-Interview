CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  set n = N-1;
  RETURN (
      # Write your MySQL query statement below.
      select ifnull(
      (
      select distinct Salary getNthHighestSalary
      from Employee
      order by Salary desc
      limit n, 1
      ), null)
  );
END