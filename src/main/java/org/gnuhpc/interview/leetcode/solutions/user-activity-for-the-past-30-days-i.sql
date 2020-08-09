SELECT activity_date AS day, COUNT(DISTINCT user_id) AS active_users
FROM Activity
WHERE datediff('2019-07-27',activity_date) < 30
-- WHERE activity_date BETWEEN '2019-06-28' AND '2019-07-27'
GROUP BY activity_date;