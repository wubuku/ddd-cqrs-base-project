INSERT INTO security_permission VALUES (200,'SEED',NOW(),0,NULL,NULL,0,'Permission for Library Admin','ROLE_LIBADMIN');

INSERT INTO security_GROUP VALUES(200,'SEED',NOW(),0,NULL,NULL,0,'LIBADMIN','1');

INSERT INTO securityGROUP_security_permissions VALUES (200,200);

INSERT INTO USER_LOGIN VALUES (200,'SEED',NOW(),1,NULL,NULL,0,'libadmin','libadmin',1,'libraryPanel','1');

INSERT INTO USERLOGIN_SECURITY_GROUPS VALUES (200,200);

insert into home_page_details values (200,'SEED',NOW(),1,NULL,NULL,0,'Library Management Administrator Home Page','libraryPanel');

