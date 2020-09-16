
select customer_id,count(0) as count_no_trans
from Visits a
left join Transactions b on a.visit_id = b.visit_id
where transaction_id is null
group by customer_id
order by count_no_trans desc;