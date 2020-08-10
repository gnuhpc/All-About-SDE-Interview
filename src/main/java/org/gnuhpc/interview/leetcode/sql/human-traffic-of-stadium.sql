# Write your MySQL query statement below

#比较重要：参考官方答案
#（1）筛选出所有大于100记录
#（2）要求连续三天，做两次全连接，获取包含当前记录任意3天的超过100的记录
select distinct s1.*
from stadium s1,stadium s2,stadium s3
where s1.people>=100 and s2.people>=100 and s3.people>=100
and (
(s1.id=s2.id+1 and s1.id=s3.id+2) or
(s1.id=s2.id+1 and s1.id=s3.id-1) or
(s1.id=s2.id-1 and s1.id=s3.id-2)
)
order by s1.id