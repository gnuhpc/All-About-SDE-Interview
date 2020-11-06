SELECT ROUND(SQRT(POWER((a.x - b.x), 2) + POWER((a.y - b.y), 2)), 2) AS shortest
FROM point_2d a INNER JOIN point_2d b
                           ON a.x != b.x OR a.y != b.y
ORDER BY shortest
LIMIT 1