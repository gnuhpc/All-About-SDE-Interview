
UPDATE salary
SET
    sex = CASE sex
        WHEN 'm' THEN 'f'
        WHEN 'f' THEN 'm'
    END;