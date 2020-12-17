select ad_id,
       case when sum(action="Clicked")+sum(action="Viewed")=0 then 0.00
            else round(100*sum(action="Clicked")/(sum(action="Clicked")+sum(action="Viewed")),2)
           end as ctr
from Ads
group by ad_id
order by ctr desc,ad_id;