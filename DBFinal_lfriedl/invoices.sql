select o.ordDate, o.OrdID, o.shipAddr, o.shipDate, v.vendCompany, p.prodDesc,
 p.prodID, b.ItemQty, p.prodPrice
from products as p, customers as c, orders as o, orderline as l, items as i,
vendors as v, basketcontents as b
where b.prodID = p.prodID and b.ItemID = i.itemID and c.customerID = o.CustID and
o.OrdID = l.OrdID and l.ProdID = p.prodID and b.prodID = p.prodID and
i.itemID = b.ItemID and i.vendID = v.vendID and (o.OrdID = '100422' or o.OrdID = '100500' or
o.OrdID = '100555')
order by OrdID, prodID;