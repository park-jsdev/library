SELECT P.product_id, NVL(ROUND(SUM(U.units* P.price)/SUM(u.units), 2), 0) average_price 
FROM Prices P
LEFT JOIN UnitsSold U
ON U.purchase_date BETWEEN P.start_date AND P.end_date AND P.product_id = U.product_id
GROUP BY P.product_id;