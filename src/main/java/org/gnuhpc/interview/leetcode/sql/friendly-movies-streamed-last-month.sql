
SELECT
	DISTINCT c.title
FROM
	TVProgram AS t
	INNER JOIN Content AS c
		ON t.content_id = c. content_id
WHERE
	YEAR(t.program_date) = 2020
	AND MONTH(t.program_date) = 6
	AND c.content_type = 'Movies'
	AND c.Kids_content = 'Y';