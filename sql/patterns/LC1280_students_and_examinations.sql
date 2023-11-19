-- Left join steps, no cross join
-- select t.student_id, t.student_name, t.subject_name,
--     sum(case when x.student_id is null then 0 else 1 end) attended_exams
-- from (select stu.*, sub.* from subjects sub
-- left join students stu
-- on 1 = 1) t
-- left join examinations x
-- on x.student_id = t.student_id and x.subject_name = t.subject_name
-- where t.student_id is not null
-- group by t.student_id, t.student_name, t.subject_name
-- order by t.student_id, t.subject_name;

-- Cross Join
select s.student_id, s.student_name, sub.subject_name,
count(e.subject_name) as attended_exams from students s cross join
subjects sub left outer join examinations e on
s.student_id = e.student_id and sub.subject_name = e.subject_name
group by s.student_id, s.student_name, sub.subject_name order by
s.student_id, sub.subject_name