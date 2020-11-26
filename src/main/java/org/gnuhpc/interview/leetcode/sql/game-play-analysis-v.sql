select a1.install_dt,count(a1.player_id) as installs,round(count(a2.player_id)/count(a1.player_id),2) as Day1_retention
from
(
	select player_id,min(event_date) as install_dt
	from Activity
	group by player_id
) as a1
left join Activity as a2
	on (a2.event_date = DATE_ADD(a1.install_dt,interval 1 day) and a2.player_id = a1.player_id)
group by a1.install_dt

https://leetcode-cn.com/problems/game-play-analysis-v/solution/liang-chong-jie-fa-by-jason-2-7/