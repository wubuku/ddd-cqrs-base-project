CREATE DATABASE /*!32312 IF NOT EXISTS*/`common` /*!40100 DEFAULT CHARACTER SET latin1 */;

DROP TABLE IF EXISTS common.`tenant_customisation_details`;

CREATE TABLE common.`tenant_customisation_details` ( `customisationId` bigint(20) NOT NULL, `jdbcUrl` varchar(100) NOT NULL, `jdbcUsername` varchar(100) NOT NULL, `jdbcPassword` varchar(100) NOT NULL, PRIMARY KEY  (`customisationId`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO common.tenant_customisation_details (customisationId, jdbcUrl, jdbcUsername, jdbcPassword ) VALUES (1, 'jdbc:mysql://localhost:3306/TenantOne', 'root', 'nthdimenzion' );

INSERT INTO common.tenant_customisation_details (customisationId, jdbcUrl, jdbcUsername, jdbcPassword ) VALUES (2, 'jdbc:mysql://localhost:3306/TenantTwo', 'root', 'nthdimenzion' );

INSERT INTO common.tenant_customisation_details (customisationId, jdbcUrl, jdbcUsername, jdbcPassword ) VALUES (3, 'jdbc:mysql://localhost:3306/TenantDefaultDev', 'root', 'nthdimenzion' );

INSERT INTO common.tenant_customisation_details (customisationId, jdbcUrl, jdbcUsername, jdbcPassword ) VALUES (4, 'jdbc:mysql://localhost:3306/TenantDefaultTest', 'root', 'nthdimenzion' );

INSERT INTO common.tenant_customisation_details (customisationId, jdbcUrl, jdbcUsername, jdbcPassword ) VALUES (5, 'jdbc:mysql://localhost:3306/TenantDefaultProd', 'root', 'nthdimenzion' );


DROP TABLE IF EXISTS common.`tenant`;

CREATE TABLE common.`tenant` ( `tenantId` varchar(10) NOT NULL, `tenantName` varchar(100) NOT NULL, `isEnabled` tinyint(1) NOT NULL, `customisationId` int(11) NOT NULL, PRIMARY KEY  (`tenantId`) ) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO common.tenant (tenantId, tenantName, isEnabled, customisationId ) VALUES ('001', 'Me', TRUE, 1 );

INSERT INTO common.tenant (tenantId, tenantName, isEnabled, customisationId ) VALUES ('002', 'You', TRUE, 2 );

INSERT INTO common.tenant (tenantId, tenantName, isEnabled, customisationId ) VALUES ('003', 'Dev', TRUE, 3 );

INSERT INTO common.tenant (tenantId, tenantName, isEnabled, customisationId ) VALUES ('004', 'Test', TRUE, 4 );

INSERT INTO common.tenant (tenantId, tenantName, isEnabled, customisationId ) VALUES ('005', 'Prod', TRUE, 5 );