/*
  Make sure to join the Child table (Product) onto the Parent (Sale).
  We can determine this because the Sales Table has a reference to the product as a FK.
*/
SELECT
p.product_name,
s.year,
s.price
FROM Sales s
LEFT JOIN
Product p
ON p.product_id = s.product_id;