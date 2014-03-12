-- TURN OFF AUTOCOMMIT!!
DECLARE
--
newAppId NUMBER(19);
newResourceId NUMBER(19);
updateUser VARCHAR2(50);
oraclePoolSize VARCHAR2(512);
jdbcPassword VARCHAR2(512);
db2PoolSize VARCHAR2(512);

blobfileDsUrl VARCHAR2(512);
blobfileDsUser VARCHAR2(512);
blobfileDsPass   VARCHAR2(512);

blobdataDsUrl VARCHAR2(512);
blobdataDsUser VARCHAR2(512);
blobdataDsPass   VARCHAR2(512);

blobAUTHDsUser VARCHAR2(512);
blobAUTHDsPass VARCHAR2(512);
blobAUTHDsUrl VARCHAR2(512);

connectionType VARCHAR2(512);


--
BEGIN
--Config Values
updateUser := 'BLOB_STARTUP_SCRIPT';
oraclePoolSize := '10';
db2PoolSize := '2';
--
blobfileDsUrl := 'jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS =(PROTOCOL = TCP)(HOST = devnixoradb01.imanheim.com)(PORT = 1525))(CONNECT_DATA =(SERVER = DEDICATED) (SERVICE_NAME = MHDEV2)))';
blobfileDsUser := 'BLOBMAN';
blobfileDsPass := 'bland3v3';
--
blobdataDsUrl := 'jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS =(PROTOCOL = TCP)(HOST = devnixoradb01.imanheim.com)(PORT = 1525))(CONNECT_DATA =(SERVER = DEDICATED) (SERVICE_NAME = MHDEV2)))';
blobdataDsUser := 'BLOBMAN';
blobdataDsPass := 'bland3v3';
--
blobAUTHDsUser := 'MOLJDBC';
blobAUTHDsPass := 'MOLJDBC';
blobAUTHDsUrl := 'jdbc:as400://mmscd400.man.cox.com';

--
connectionType :='blobdata';

--
-- Done Setting Variables
--
-- Application Insert
newAppId := JNDIADM.SEQ_APPLICATIONS.NEXTVAL;
INSERT INTO JNDIADM.APPLICATIONS 
		(APPLICATION_ID, APPLICATION_NAME, IS_ACTIVE, IS_GLOBAL, UPDATE_USER)
VALUES	(newAppId, 'BlobServerWebService', 1, 0, updateUser);
--
-- Blob File Data Source definition
newResourceId := JNDIADM.SEQ_RESOURCES.NEXTVAL;
INSERT INTO JNDIADM.RESOURCES (RESOURCE_ID, APPLICATION_ID, RESOURCE_NAME, RESOURCE_TYPE,
	RESOURCE_AUTH, RESOURCE_CLOSE_METHOD, RESOURCE_DESCRIPTION, RESOURCE_SCOPE, RESOURCE_SINGLETON, IS_ACTIVE, UPDATE_USER)
VALUES (newResourceId, newAppId, 'jdbc/blobs-file', 'javax.sql.DataSource',
	'Container', NULL, NULL, NULL, NULL, 1, updateUser);
--
-- Blob File Data Source properties
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'maxActive', oraclePoolSize, 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'maxIdle', oraclePoolSize, 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'poolPreparedStatements', 'true', 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'maxWait', '15000', 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'removeAbandoned', 'true', 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'removeAbandonedTimeout', '60', 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'validationQuery', 'SELECT 1 FROM DUAL', 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'username', blobfileDsUser, 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'password', blobfileDsPass, 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'driverClassName', 'oracle.jdbc.driver.OracleDriver', 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'url', blobfileDsUrl, 1, updateUser);
--
-- Blobs Blob-Data Source definition
newResourceId := JNDIADM.SEQ_RESOURCES.NEXTVAL;
INSERT INTO JNDIADM.RESOURCES (RESOURCE_ID, APPLICATION_ID, RESOURCE_NAME, RESOURCE_TYPE,
	RESOURCE_AUTH, RESOURCE_CLOSE_METHOD, RESOURCE_DESCRIPTION, RESOURCE_SCOPE, RESOURCE_SINGLETON, IS_ACTIVE, UPDATE_USER)
VALUES (newResourceId, newAppId, 'jdbc/blobs-blobdata', 'javax.sql.DataSource',
	'Container', NULL, NULL, NULL, NULL, 1, updateUser);
--
-- Blob File Data Source properties
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'maxActive', oraclePoolSize, 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'maxIdle', oraclePoolSize, 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'poolPreparedStatements', 'true', 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'maxWait', '15000', 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'removeAbandoned', 'true', 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'removeAbandonedTimeout', '60', 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'validationQuery', 'SELECT 1 FROM DUAL', 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'username', blobdataDsUser, 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'password', blobdataDsPass, 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'driverClassName', 'oracle.jdbc.driver.OracleDriver', 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'url', blobdataDsUrl, 1, updateUser);
--
-- AUTHENTICATION DATASOURCES
--
newResourceId := JNDIADM.SEQ_RESOURCES.NEXTVAL;
INSERT INTO JNDIADM.RESOURCES (RESOURCE_ID, APPLICATION_ID, RESOURCE_NAME, RESOURCE_TYPE,
	RESOURCE_AUTH, RESOURCE_CLOSE_METHOD, RESOURCE_DESCRIPTION, RESOURCE_SCOPE, RESOURCE_SINGLETON, IS_ACTIVE, UPDATE_USER)
VALUES (newResourceId, newAppId, 'jdbc/AUTHDS-JDBC', 'javax.sql.DataSource',
	'Container', NULL, NULL, NULL, NULL, 1, updateUser);
--
-- AUTHENTICATION DATASOURCES properties
--
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'maxActive', db2PoolSize, 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'maxIdle', db2PoolSize, 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'poolPreparedStatements', 'true', 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'maxWait', '15000', 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'removeAbandoned', 'true', 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'removeAbandonedTimeout', '60', 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'validationQuery', 'select 1 from SYSIBM.SYSDUMMY1', 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'username', blobAUTHDsUser, 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'password', blobAUTHDsPass, 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'driverClassName', 'com.ibm.as400.access.AS400JDBCDriver', 1, updateUser);
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'url', blobAUTHDsUrl, 1, updateUser);
        
        
--
--Logging Datasource
--

newResourceId := JNDIADM.SEQ_RESOURCES.NEXTVAL;
INSERT INTO JNDIADM.RESOURCES (RESOURCE_ID, APPLICATION_ID, RESOURCE_NAME, RESOURCE_TYPE,
	RESOURCE_AUTH, RESOURCE_CLOSE_METHOD, RESOURCE_DESCRIPTION, RESOURCE_SCOPE, RESOURCE_SINGLETON, IS_ACTIVE, UPDATE_USER)
VALUES (newResourceId, newAppId, 'jdbc/LoggingDataSource', 'javax.sql.DataSource',
	'Container', NULL, NULL, NULL, NULL, 1, updateUser);

--
--Logging Datasource Properties
--
INSERT INTO JNDIADM.RESOURCE_PROPERTIES (PROPERTY_ID, RESOURCE_ID, PROPERTY_NAME, PROPERTY_VALUE, IS_ACTIVE, UPDATE_USER)
        VALUES	(JNDIADM.SEQ_RESOURCE_PROPERTIES.NEXTVAL, newResourceId, 'global', 'jdbc/LoggingDataSource', 1, updateUser);
        
        

-- Environmental Entries

INSERT INTO JNDIADM.ENVIRONMENT_ENTRIES (ENTRY_ID,APPLICATION_ID,ENTRY_NAME,ENTRY_TYPE,ENTRY_DESCRIPTION,ENTRY_OVERRIDE,IS_ACTIVE,UPDATE_USER,ENTRY_VALUE) 
	VALUES (JNDIADM.SEQ_ENVIRONMENT_ENTRIES.NEXTVAL,newAppId,'connectionType','java.lang.String','Connection Type file/blobdata required for Blob Manager','false',1,updateUser,connectionType);   

END;
/