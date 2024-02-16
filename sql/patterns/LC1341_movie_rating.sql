/* Write your PL/SQL query statement below */
with cte_movies as (
    select a.*, b.title, c.name
    from Movierating a
    left join movies b
    on a.movie_id = b.movie_id
    left join Users C
    on a.user_id = c.user_id
)
(
    select results from
    (
        select name as results, count(user_id) as cnt
        from cte_movies
        group by name
        order by cnt desc, name asc
    )
    where
    rownum = 1
)
union all
(
    select results from
    (
        select Title as results, avg(rating) as average
        from cte_movies
        where trunc(created_at) between '2020-02-01' and '2020-02-29'
        group by title
        order by average desc, title asc
    )
    where
    rownum = 1
);