# Write your MySQL query statement below

SELECT b.Id
FROM Weather as a,Weather as b
WHERE a.Temperature < b.Temperature and DATEDIFF(b.RecordDate,a.recordDate) = 1;
