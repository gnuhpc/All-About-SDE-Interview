--首先sql的执行顺序是from, where,select，
--其次找出排名也就相当于找出大于等于该数的不重复数字有几个（数相同排名相同）.
--用group by 是因为需要对每个数据进行排名

select s1.Score,count(distinct(s2.score)) Rank
from
Scores s1,Scores s2
where
s1.score<=s2.score
group by s1.Id
order by Rank

select score,
       dense_rank() over(order by Score desc) as Rank
from Scores;

--具体几个rank函数的区别参见：
--链接：https://leetcode-cn.com/problems/rank-scores/solution/tu-jie-sqlmian-shi-ti-jing-dian-pai-ming-wen-ti-by/
