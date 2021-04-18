select employee_id, first_name ||last_name fullname
from employees where employee_id in (
									select distinct manager_id 
									from employees )


select distinct manager_id
from employees


select * from employees

alter trigger trigger_jobs disable;


select * from DEPARTMENTS

select JOBS from employees