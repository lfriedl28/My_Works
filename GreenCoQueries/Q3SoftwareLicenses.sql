select distinct Description, QtyPurchased, DateAssigned, (QtyPurchased) as remainder
from software, softwareassigned
where software.SoftID = softwareassigned.SoftID
order by remainder;