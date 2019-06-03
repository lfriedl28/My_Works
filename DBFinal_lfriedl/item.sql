select p.prodID, i.itemDesc, i.itemPrice, b.ItemQty
from products as p, items as i, basketcontents as b
where i.itemID = b.ItemID and p.prodID = b.prodID
order by prodID, i.ItemID;