SELECT
    query_name,
    ROUND(AVG(rating/position), 2) as quality,
    ROUND(SUM(IF(rating < 3, 1, 0)) * 100 / COUNT(*), 2) as poor_query_percentage
FROM Queries
GROUP BY query_name