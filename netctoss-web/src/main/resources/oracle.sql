select * from admin_info;--liubei
select * from admin_role;
select * from role_info;--ª  Â
select * from role_privilege;

select ai.*,ri.name,rp.privilege_id 
from admin_info ai
inner join admin_role ar 
on ar.admin_id = ai.id
inner join role_info ri
on ri.id = ar.role_id
inner join role_privilege rp
on rp.role_id = ri.id


select * from (
	select a.*,rownum r from admin_info a
	where id in (
		select admin_id from admin_role ar
		inner join role_info ri on ri.id=ar.role_id
		inner join role_privilege rp on rp.role_id=ri.id
		where 1=1
		and ri.id=500
		and rp.privilege_id=1
	)
) where r<6 and r>0



insert into admin_info values(1,'aaa','a','AAA','123456789','lihh@tarena.com.cn',sysdate);
insert into admin_info values(2,'bbb','a','BBB','123456789','lihh@tarena.com.cn',sysdate);
insert into admin_info values(3,'ccc','a','CCC','123456789','lihh@tarena.com.cn',sysdate);
insert into admin_info values(4,'ddd','a','DDD','123456789','lihh@tarena.com.cn',sysdate);
insert into admin_info values(5,'eee','a','EEE','123456789','lihh@tarena.com.cn',sysdate);

