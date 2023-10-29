-- Check rows which are different data (diff)

(select * from T1 minus select * from T2) -- all rows that are in T1 but not in T2
union
(select * from T2 minus select * from T1)  -- all rows that are in T2 but not in T1
;