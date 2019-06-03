select Lname, Fname, Description, DateAssigned
from employee, software, softwareassigned
where software.SoftID = softwareassigned.SoftID and
employee.EmpID = softwareassigned.EmpID
order by Lname asc, DateAssigned desc;