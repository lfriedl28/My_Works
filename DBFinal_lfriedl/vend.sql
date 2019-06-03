select v.vendCompany, v.vendCity, i.itemID, i.itemPrice
from vendors as v, items as i
where v.vendID = i.vendID
order by v.vendCity, v.vendCompany, i.itemID;