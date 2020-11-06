select seller_name
from Seller
where seller_id not in(
    select seller_id
    from Orders
    where sale_date between "2020-01-01" and "2020-12-31"
)
order by seller_name asc