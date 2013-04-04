/*
SQLyog Community Edition- MySQL GUI v8.2 
MySQL - 5.0.51b-community-nt : Database - tenantdefaultdev
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tenantdefaulttest` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `tenantdefaulttest`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `ID` bigint(20) NOT NULL auto_increment,
  `CREATED_BY` varchar(255) default NULL,
  `CREATED_TX_TIMESTAMP` datetime default NULL,
  `ENTITY_STATUS` int(11) default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  `UPDATED_TX_TIMESTAMP` datetime default NULL,
  `VERSION` bigint(20) default NULL,
  `AUTHORS` varchar(255) default NULL,
  `AVAILABLE_COPIES` int(11) default NULL,
  `UID` varchar(255) default NULL,
  `AMOUNT` decimal(64,2) default NULL,
  `CURRENCY_CODE` varchar(255) default NULL,
  `ISBN` varchar(255) default NULL,
  `NAME` varchar(255) default NULL,
  `TOTAL_COPIES` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `book_lending` */

DROP TABLE IF EXISTS `book_lending`;

CREATE TABLE `book_lending` (
  `ID` bigint(20) NOT NULL auto_increment,
  `CREATED_BY` varchar(255) default NULL,
  `CREATED_TX_TIMESTAMP` datetime default NULL,
  `ENTITY_STATUS` int(11) default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  `UPDATED_TX_TIMESTAMP` datetime default NULL,
  `VERSION` bigint(20) default NULL,
  `UID` varchar(255) default NULL,
  `COMPLETION_COMMENTS` varchar(255) default NULL,
  `FROM_DATE` datetime default NULL,
  `THRU_DATE` datetime default NULL,
  `BOOK` bigint(20) default NULL,
  `MEMBER` bigint(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK7C29091D45F34AA1` (`MEMBER`),
  KEY `FK7C29091D85C3227F` (`BOOK`),
  CONSTRAINT `FK7C29091D85C3227F` FOREIGN KEY (`BOOK`) REFERENCES `book` (`ID`),
  CONSTRAINT `FK7C29091D45F34AA1` FOREIGN KEY (`MEMBER`) REFERENCES `member` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `home_page_details` */

DROP TABLE IF EXISTS `home_page_details`;

CREATE TABLE `home_page_details` (
  `ID` bigint(20) NOT NULL auto_increment,
  `CREATED_BY` varchar(255) default NULL,
  `CREATED_TX_TIMESTAMP` datetime default NULL,
  `ENTITY_STATUS` int(11) default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  `UPDATED_TX_TIMESTAMP` datetime default NULL,
  `VERSION` bigint(20) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `HOMEPAGE_VIEW_ID` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `library_config` */

DROP TABLE IF EXISTS `library_config`;

CREATE TABLE `library_config` (
  `ID` bigint(20) NOT NULL auto_increment,
  `CREATED_BY` varchar(255) default NULL,
  `CREATED_TX_TIMESTAMP` datetime default NULL,
  `ENTITY_STATUS` int(11) default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  `UPDATED_TX_TIMESTAMP` datetime default NULL,
  `VERSION` bigint(20) default NULL,
  `MAX_LENDING_PERIOD` int(11) default NULL,
  `NAME` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `ID` bigint(20) NOT NULL auto_increment,
  `CREATED_BY` varchar(255) default NULL,
  `CREATED_TX_TIMESTAMP` datetime default NULL,
  `ENTITY_STATUS` int(11) default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  `UPDATED_TX_TIMESTAMP` datetime default NULL,
  `VERSION` bigint(20) default NULL,
  `PERSON` bigint(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK87557E9A50356C17` (`PERSON`),
  CONSTRAINT `FK87557E9A50356C17` FOREIGN KEY (`PERSON`) REFERENCES `person` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `person` */

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `ID` bigint(20) NOT NULL auto_increment,
  `CREATED_BY` varchar(255) default NULL,
  `CREATED_TX_TIMESTAMP` datetime default NULL,
  `ENTITY_STATUS` int(11) default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  `UPDATED_TX_TIMESTAMP` datetime default NULL,
  `VERSION` bigint(20) default NULL,
  `DATE_OF_BIRTH` datetime NOT NULL,
  `FIRST_NAME` varchar(255) NOT NULL,
  `LAST_NAME` varchar(255) NOT NULL,
  `MIDDLE_NAME` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `security_group` */

DROP TABLE IF EXISTS `security_group`;

CREATE TABLE `security_group` (
  `ID` bigint(20) NOT NULL auto_increment,
  `CREATED_BY` varchar(255) default NULL,
  `CREATED_TX_TIMESTAMP` datetime default NULL,
  `ENTITY_STATUS` int(11) default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  `UPDATED_TX_TIMESTAMP` datetime default NULL,
  `VERSION` bigint(20) default NULL,
  `NAME` varchar(255) default NULL,
  `UID` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `security_permission` */

DROP TABLE IF EXISTS `security_permission`;

CREATE TABLE `security_permission` (
  `ID` bigint(20) NOT NULL auto_increment,
  `CREATED_BY` varchar(255) default NULL,
  `CREATED_TX_TIMESTAMP` datetime default NULL,
  `ENTITY_STATUS` int(11) default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  `UPDATED_TX_TIMESTAMP` datetime default NULL,
  `VERSION` bigint(20) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `PERMISSION_ID` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `securitygroup_security_permissions` */

DROP TABLE IF EXISTS `securitygroup_security_permissions`;

CREATE TABLE `securitygroup_security_permissions` (
  `SECURITYGROUP` bigint(20) NOT NULL,
  `SECURITYPERMISSIONS` bigint(20) NOT NULL,
  PRIMARY KEY  (`SECURITYGROUP`,`SECURITYPERMISSIONS`),
  KEY `FK85E36305E5AC1C76` (`SECURITYPERMISSIONS`),
  KEY `FK85E36305B3968A7B` (`SECURITYGROUP`),
  CONSTRAINT `FK85E36305B3968A7B` FOREIGN KEY (`SECURITYGROUP`) REFERENCES `security_group` (`ID`),
  CONSTRAINT `FK85E36305E5AC1C76` FOREIGN KEY (`SECURITYPERMISSIONS`) REFERENCES `security_permission` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `simple_person` */

DROP TABLE IF EXISTS `simple_person`;

CREATE TABLE `simple_person` (
  `ID` bigint(20) NOT NULL auto_increment,
  `CREATED_BY` varchar(255) default NULL,
  `CREATED_TX_TIMESTAMP` datetime default NULL,
  `ENTITY_STATUS` int(11) default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  `UPDATED_TX_TIMESTAMP` datetime default NULL,
  `VERSION` bigint(20) default NULL,
  `NAME` varchar(255) default NULL,
  `UID` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `user_login` */

DROP TABLE IF EXISTS `user_login`;

CREATE TABLE `user_login` (
  `ID` bigint(20) NOT NULL auto_increment,
  `CREATED_BY` varchar(255) default NULL,
  `CREATED_TX_TIMESTAMP` datetime default NULL,
  `ENTITY_STATUS` int(11) default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  `UPDATED_TX_TIMESTAMP` datetime default NULL,
  `VERSION` bigint(20) default NULL,
  `PASSWORD` varchar(255) default NULL,
  `USERNAME` varchar(255) default NULL,
  `ENABLED` bit(1) default NULL,
  `HOMEPAGE_VIEW_ID` varchar(255) default NULL,
  `UID` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `userlogin_security_groups` */

DROP TABLE IF EXISTS `userlogin_security_groups`;

CREATE TABLE `userlogin_security_groups` (
  `USERLOGIN` bigint(20) NOT NULL,
  `SECURITYGROUPS` bigint(20) NOT NULL,
  PRIMARY KEY  (`USERLOGIN`,`SECURITYGROUPS`),
  KEY `FK82384FD297B86779` (`USERLOGIN`),
  KEY `FK82384FD2D5896450` (`SECURITYGROUPS`),
  CONSTRAINT `FK82384FD2D5896450` FOREIGN KEY (`SECURITYGROUPS`) REFERENCES `security_group` (`ID`),
  CONSTRAINT `FK82384FD297B86779` FOREIGN KEY (`USERLOGIN`) REFERENCES `user_login` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
