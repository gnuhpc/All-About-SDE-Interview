select country_name,(
case when avg_weather <=15 then 'Cold'
     when avg_weather >=25 then 'Hot'
     else 'Warm'
    end
)as weather_type from Countries join
(select AVG(weather_state) as avg_weather,country_id from weather
where year(day) = 2019 and month(day)=11 group by country_id)k
on Countries.country_id =k.country_id;