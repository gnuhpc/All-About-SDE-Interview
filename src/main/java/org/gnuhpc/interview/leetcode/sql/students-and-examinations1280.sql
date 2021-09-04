

select  t1.student_id,
        t1.student_name, t1.subject_name,
        case when isnull(t2.total) then 0 else t2.total end as attended_exams
from
    (select *
     from Students cross join Subjects)
        t1
        left join
    (select student_id, subject_name, count(*) total
     from Examinations
     group by student_id, subject_name)
        t2
    using(student_id, subject_name)
order by
    student_id, subject_name


#链接：https://leetcode-cn.com/problems/students-and-examinations/solution/cross-join-left-join-by-0owu-ming-xiao-cao-o0/
