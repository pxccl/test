-- 一、创建数据库
-- DROP DATABASE chouse ;
-- CREATE DATABASE chouse CHARACTER SET UTF8 ;
-- 二、使用数据库
USE chouse ;
-- 三、删除数据表
-- 1、删除管理员登录日志表
DROP TABLE IF EXISTS admin_login ;
-- 2、删除用户-通知关系表
DROP TABLE IF EXISTS user_notice ;
-- 3、删除通知表
DROP TABLE IF EXISTS notice ;
-- 4、删除权限-角色关系表
DROP TABLE IF EXISTS role_privilege ;
-- 5、删除权限详情表
DROP TABLE IF EXISTS privilege_details ;
-- 6、删除权限表
DROP TABLE IF EXISTS privilege ;
-- 7、删除数据字典表
DROP TABLE IF EXISTS dictionary ;
-- 8、删除消息表
DROP TABLE IF EXISTS message ;
-- 9、删除合同信息表
DROP TABLE IF EXISTS contract ;
-- 10、删除房屋问题咨询表
DROP TABLE IF EXISTS house_qa ;
-- 11、删除房屋图片信息
DROP TABLE IF EXISTS house_photo ;
-- 12、删除房屋信息表
DROP TABLE IF EXISTS house ;
-- 13、删除用户表
DROP TABLE IF EXISTS user ;
-- 14、删除管理员表
DROP TABLE IF EXISTS admin ;
-- 15、删除角色表
DROP TABLE IF EXISTS role ;

-- 四、创建数据表
-- 1、角色表
CREATE TABLE role (
	rid			INT		NOT NULL	AUTO_INCREMENT ,
	title			VARCHAR(50),
	note			TEXT ,
	CONSTRAINT pk_gid PRIMARY KEY (rid)
) type=innodb;
-- 2、管理员表
CREATE TABLE admin (
	adid			VARCHAR(50)	NOT NULL,
	rid			INT,
	password		VARCHAR(32)	NOT NULL,
	lastlogin		TIMESTAMP,
	flag			INT		DEFAULT 0 ,
	CONSTRAINT pk_admin PRIMARY KEY (adid) ,
	CONSTRAINT fk_rid1 FOREIGN KEY(rid) REFERENCES role(rid) ON DELETE SET NULL
) type=innodb;
-- 3、管理员登录日志
CREATE TABLE admin_login (
	alid			INT		AUTO_INCREMENT,
	adid			VARCHAR(50),
	logindate		TIMESTAMP,
	ip			VARCHAR(30),
	CONSTRAINT pk_alid PRIMARY KEY (alid) ,
	CONSTRAINT fk_adid1 FOREIGN KEY(adid) REFERENCES admin(adid) ON DELETE CASCADE
) type=innodb;
-- 4、定义权限表
CREATE TABLE privilege (
	pid			INT		NOT NULL	AUTO_INCREMENT ,
	title			VARCHAR(50),
	note			TEXT ,
	CONSTRAINT pk_pid PRIMARY KEY (pid)
) type=innodb;
-- 5、定义权限详情表
CREATE TABLE privilege_details (
	pdid			INT		NOT NULL	AUTO_INCREMENT ,
	pid			INT,
	title			VARCHAR(50),
	url			VARCHAR(200),
	CONSTRAINT pk_pdid PRIMARY KEY (pdid) ,
	CONSTRAINT fk_pid FOREIGN KEY(pid) REFERENCES privilege(pid) ON DELETE CASCADE
) type=innodb;
-- 6、定义角色与权限组关系表
CREATE TABLE role_privilege (
	pid			INT ,
	rid			INT ,
	CONSTRAINT fk_pid2 FOREIGN KEY(pid) REFERENCES privilege(pid) ON DELETE CASCADE ,
	CONSTRAINT fk_rid2 FOREIGN KEY(rid) REFERENCES role(rid) ON DELETE SET NULL
) type=innodb;
-- 7、数据字典表
CREATE TABLE dictionary (
	dctid			INT 		AUTO_INCREMENT ,
	type			VARCHAR(50),
	title			VARCHAR(50),
	note			TEXT,
	CONSTRAINT pk_dctid PRIMARY KEY (dctid) 
) type=innodb;
-- 8、用户表
CREATE TABLE user (
	uid			VARCHAR(50)	NOT NULL,
	password		VARCHAR(32),
	phone			VARCHAR(20),
	photo			VARCHAR(200),
	regdate			TIMESTAMP,
	lastLoginDate		TIMESTAMP,
	card			VARCHAR(18),
	address			VARCHAR(200),
	status			INT,
	name			VARCHAR(50) ,
	CONSTRAINT pk_uid PRIMARY KEY (uid)
) type=innodb;
-- 9、站内消息
CREATE TABLE message (
	mid			INT		AUTO_INCREMENT,
	suid			VARCHAR(50),
	ruid			VARCHAR(50),
	title			VARCHAR(50),
	note			TEXT,
	status			INT,
	CONSTRAINT pk_mid PRIMARY KEY (mid) ,
	CONSTRAINT fk_suid FOREIGN KEY(suid) REFERENCES user(uid) ON DELETE CASCADE ,
	CONSTRAINT fk_ruid FOREIGN KEY(ruid) REFERENCES user(uid) ON DELETE CASCADE
) type=innodb;
-- 10、房屋信息表
CREATE TABLE house (
	hsid                 INT 		AUTO_INCREMENT ,
	uid                  VARCHAR(50),
	community            VARCHAR(200),
	address              VARCHAR(200),
	area                 DOUBLE,
	total                VARCHAR(50),
	floor                VARCHAR(50),
	orientation          VARCHAR(50),
	structure            VARCHAR(50),
	renovation           VARCHAR(50),
	fdate                TIMESTAMP,
	status               INT,
	price                DOUBLE,
	visits               INT,
	type                 VARCHAR(50),
	note                 TEXT,
	CONSTRAINT pk_hsid PRIMARY KEY (hsid) ,
	CONSTRAINT fk_uid FOREIGN KEY(uid) REFERENCES user(uid) ON DELETE CASCADE
) type=innodb;
-- 11、房屋图片
CREATE TABLE house_photo (
	hpid			INT		AUTO_INCREMENT,
	hsid			INT,
	title			VARCHAR(50),
	photo			VARCHAR(200),
	CONSTRAINT pk_hpid PRIMARY KEY (hpid) ,
	CONSTRAINT fk_hsid1 FOREIGN KEY(hsid) REFERENCES house(hsid) ON DELETE CASCADE
) type=innodb;

-- 12、房屋问题咨询
CREATE TABLE house_qa (
	qaid			INT		AUTO_INCREMENT ,
	uid			VARCHAR(50),
	hsid			INT,
	question		TEXT,
	answer			TEXT,
	pubtime			TIMESTAMP,
	anstime			TIMESTAMP,
	CONSTRAINT pk_qaid PRIMARY KEY (qaid) ,
	CONSTRAINT fk_uid2 FOREIGN KEY(uid) REFERENCES user(uid) ON DELETE CASCADE ,
	CONSTRAINT fk_hsid2 FOREIGN KEY(hsid) REFERENCES house(hsid) ON DELETE CASCADE
) type=innodb;

-- 13、合同信息
CREATE TABLE contract (
	ctid			INT		AUTO_INCREMENT,
	fuid			VARCHAR(50),
	suid			VARCHAR(50),
	adid			VARCHAR(50),
	hsid			INT ,
	sign			TIMESTAMP,
	price			DOUBLE,
	type			VARCHAR(50),
	content			TEXT,
	fphone			VARCHAR(50),
	sphone			VARCHAR(50),
	faddress		VARCHAR(200) ,
	saddress		VARCHAR(200) ,
	fname			VARCHAR(50) ,
	sname			VARCHAR(50) ,
	fcard			VARCHAR(18),
	scard			VARCHAR(18),
	status			INT,
	anote			TEXT,
	adate			TIMESTAMP,
	CONSTRAINT pk_ctid PRIMARY KEY (ctid) ,
	CONSTRAINT fk_fuid1 FOREIGN KEY(fuid) REFERENCES user(uid) ON DELETE SET NULL ,
	CONSTRAINT fk_suid1 FOREIGN KEY(suid) REFERENCES user(uid) ON DELETE SET NULL ,
	CONSTRAINT fk_adid3 FOREIGN KEY(adid) REFERENCES admin(adid) ON DELETE SET NULL ,
	CONSTRAINT fk_hsid3 FOREIGN KEY(hsid) REFERENCES house(hsid) ON DELETE SET NULL 
) type=innodb;
-- 14、公共通知
CREATE TABLE notice (
	nid			INT,
	adid			VARCHAR(50),
	title			VARCHAR(50),
	pubdate			TIMESTAMP,
	content			TEXT,
	photo			VARCHAR(200),
	CONSTRAINT pk_nid PRIMARY KEY (nid) ,
	CONSTRAINT fk_adid5 FOREIGN KEY(adid) REFERENCES admin(adid) ON DELETE SET NULL
) type=innodb;
-- 15、用户_通知浏览
CREATE TABLE user_notice (
	uid			VARCHAR(50),
	nid			INT ,
	status			INT ,
	CONSTRAINT fk_uid6 FOREIGN KEY(uid) REFERENCES user(uid) ON DELETE CASCADE ,
	CONSTRAINT fk_nid6 FOREIGN KEY(nid) REFERENCES notice(nid) ON DELETE CASCADE
) type=innodb;

-- 五、测试数据
-- 1、角色数据
INSERT INTO role(title,note) VALUES ('超级管理','-') ;
INSERT INTO role(title,note) VALUES ('信息管理','-') ;

-- 2、管理员数据
-- 用户名：admin，密码：hello	
INSERT INTO admin(adid,rid,password,lastlogin,flag) VALUES ('admin',1,'5D41402ABC4B2A76B9719D911017C592',now(),1) ;
-- 用户名：mldn，密码：java	
INSERT INTO admin(adid,rid,password,lastlogin,flag) VALUES ('mldn',2,'93F725A07423FE1C889F448B33D21F46',now(),0) ;

-- 3、权限数据
INSERT INTO privilege(title,note) VALUES ('权限管理','-') ;
INSERT INTO privilege(title,note) VALUES ('房屋管理','-') ;
INSERT INTO privilege(title,note) VALUES ('用户管理','-') ;
INSERT INTO privilege(title,note) VALUES ('信息管理','-') ;

-- 4、权限详情数据
-- A、权限管理
INSERT INTO privilege_details(pid,title,url) VALUES (1,'增加管理员','/pages/back/admin/admin/AdminServlet/insertPre') ;
INSERT INTO privilege_details(pid,title,url) VALUES (1,'管理员列表','/pages/back/admin/admin/AdminServlet/list') ;
INSERT INTO privilege_details(pid,title,url) VALUES (1,'增加角色','/pages/back/admin/role/RoleServlet/insertPre') ;
INSERT INTO privilege_details(pid,title,url) VALUES (1,'权限列表','/pages/back/admin/role/RoleServlet/list') ;
INSERT INTO privilege_details(pid,title,url) VALUES (1,'增加权限','/pages/back/admin/privilege/PrivilegeServlet/insertPre') ;
INSERT INTO privilege_details(pid,title,url) VALUES (1,'权限列表','/pages/back/admin/privilege/PrivilegeServlet/list') ;
-- B、房屋管理
INSERT INTO privilege_details(pid,title,url) VALUES (2,'房屋列表','/pages/back/admin/hourse/HourseServlet/list') ;
INSERT INTO privilege_details(pid,title,url) VALUES (2,'咨询列表','/pages/back/admin/hourse/HourseServlet/listQA') ;
INSERT INTO privilege_details(pid,title,url) VALUES (2,'合同列表','/pages/back/admin/hourse/ContractServlet/list') ;
-- C、用户管理
INSERT INTO privilege_details(pid,title,url) VALUES (3,'用户列表','/pages/back/admin/user/UserServlet/list') ;
-- D、信息管理
INSERT INTO privilege_details(pid,title,url) VALUES (4,'发送通知','/pages/back/admin/notice/NoticeServlet/insertPre') ;
INSERT INTO privilege_details(pid,title,url) VALUES (4,'历史通知','/pages/back/admin/notice/NoticeServlet/list') ;

-- 5、权限-角色关系
INSERT INTO role_privilege(pid,rid) VALUES (1,1) ;
INSERT INTO role_privilege(pid,rid) VALUES (2,1) ;
INSERT INTO role_privilege(pid,rid) VALUES (3,1) ;
INSERT INTO role_privilege(pid,rid) VALUES (4,1) ;
INSERT INTO role_privilege(pid,rid) VALUES (2,2) ;
INSERT INTO role_privilege(pid,rid) VALUES (4,2) ;

-- 6、数据字典数据
INSERT INTO dictionary(type,title,note) VALUES ('renovation','精装修','') ;
INSERT INTO dictionary(type,title,note) VALUES ('renovation','普通装修','') ;
INSERT INTO dictionary(type,title,note) VALUES ('renovation','毛坯','') ;
INSERT INTO dictionary(type,title,note) VALUES ('orientation','东','') ;
INSERT INTO dictionary(type,title,note) VALUES ('orientation','南','') ;
INSERT INTO dictionary(type,title,note) VALUES ('orientation','西','') ;
INSERT INTO dictionary(type,title,note) VALUES ('orientation','北','') ;
INSERT INTO dictionary(type,title,note) VALUES ('structure','一厅一卫','') ;
INSERT INTO dictionary(type,title,note) VALUES ('structure','一厅二室一卫','') ;
INSERT INTO dictionary(type,title,note) VALUES ('structure','一厅三室两卫','') ;
INSERT INTO dictionary(type,title,note) VALUES ('structure','二厅五室三卫','') ;

