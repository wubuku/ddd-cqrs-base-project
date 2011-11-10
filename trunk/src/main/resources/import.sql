delete from
USERLOGIN_SECURITY_GROUPS;

delete from securityGROUP_security_permissions;

delete from security_permission;

delete from security_GROUP;

delete from USER_LOGIN;

delete from USERLOGIN_SECURITY_GROUPS;

delete from securityGROUP_security_permissions;

delete from security_permission;

delete from security_GROUP;

delete from USER_LOGIN;

delete from home_page_details;

INSERT INTO security_permission VALUES (1,'SEED','2011-11-07 12:01:52',0,NULL,NULL,0,'Permission for Super Admin','ROLE_SUPERADMIN');

--INSERT INTO security_permission VALUES (2,'SEED','2011-11-07 12:01:52',0,NULL,NULL,0,'ROLE_TEST');

INSERT INTO security_GROUP VALUES(1,'SEED','2011-11-07 13:51:34',0,NULL,NULL,0,'SUPERADMIN','1');

INSERT INTO securityGROUP_security_permissions VALUES (1,1);	

--INSERT INTO securityGROUP_security_permissions VALUES (1,2);

INSERT INTO USER_LOGIN VALUES (1,'SEED','2011-11-07 13:54:32',1,NULL,NULL,0,'sa','sa',1,'personPanel','1');

INSERT INTO USERLOGIN_SECURITY_GROUPS VALUES (1,1);

insert into home_page_details values (1,'SEED','2011-11-07 13:54:32',1,NULL,NULL,0,'Superadmin Home Page','personPanel');
