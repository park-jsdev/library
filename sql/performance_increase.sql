-- Materialized View (Indexed View in SQL Server):
-- Materialized Views can be used to improve query performance, especially in scenarios where complex aggregations or joins are frequent operations.
-- Regular Views improve performance in SQL, but not Oracle (vs a simple query)

-- A materialized view is a database object that contains the results of a query. Instead of querying the tables directly, you query the materialized view, which can speed up performance for complex queries.
-- The materialized view can be periodically refreshed, either on-demand or at regular intervals, to ensure the data remains current.
-- Materialized views can be indexed, further enhancing retrieval speed.
-- They are especially useful in data warehousing scenarios where periodic refreshes are acceptable, and the primary goal is fast retrieval of pre-aggregated or pre-computed data.
-- Oracle also provides query rewrite capabilities. This means if a query is issued against base tables that can be answered using the materialized view, Oracle can automatically rewrite the query to use the materialized view instead.

-- Trade offs:
-- Storage: They consume additional storage because the results of the query are stored physically.
-- Maintenance: They need to be refreshed to ensure data remains current, and this refresh can be resource-intensive.
-- Complexity: Introducing materialized views adds another layer of complexity to the database environment.

-- Creating a Materialized View:
CREATE MATERIALIZED VIEW mv_name AS
SELECT ...
FROM ...
WHERE ...;

-- Refreshing a Materialized View:
EXEC DBMS_MVIEW.REFRESH('mv_name');