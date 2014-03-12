#!/bin/sh

# begin install script
echo Starting Installation

# cd to catalina_base
#cd /export/webservices.manheim.com/BlobServerWebService/pronixwsc0101/1/

# 1. shut down tomcat container
echo shutting down Tomcat container
./bin/shutdown.sh
echo done

# 2. archive current war file, remove current exploded directory
echo archiving war file
mv webapps/BlobServerWebService-1.1.1.war webapps/BlobServerWebService-1.1.1.bak
rm -r webapps/BlobServerWebService-1.1.1/
echo done

# 3. archive log files
echo archiving log files
mkdir logs/archive1.0.0
mv logs/*.out logs/archive1.1.1
mv logs/*.log logs/archive1.1.1
touch logs/catalina.out
echo done

# 4. delete temp/* work/* conf/Catalina/localhost/*
echo removing temporary files
rm -r temp/* work/* conf/Catalina/localhost/*
echo done

# 5. archive conf/context.xml
echo archiving container context.xml file
mv conf/context.xml conf/context.bak
echo done

# 6. wget new context.xml into conf/
echo downloading new container context.xml file
wget -P conf/ http://devnixkitty01.imanheim.com/ejndi/configs/context-prod.xml
mv conf/context-prod.xml conf/context.xml
chmod 755 conf/context.xml
echo done


# 7. wget manheim-enterprise-jndi.jar to lib/
echo downloading manheim enterprise jndi library
wget -P lib/ http://cp-artifactory.imanheim.com/repo/com/manheim/webservices/manheim-enterprise-jndi/1.0.0/manheim-enterprise-jndi-1.0.0.jar
chmod 755 lib/manheim-enterprise-jndi-1.0.0.jar
echo done

# 8. wget PreferredLendingOptionsWebService-1.0.1.war to webapps/
echo downloading new PreferredLendingOptionsWebService war file
wget -P webapps/ http://cp-artifactory/simple/manheim-release/com/manheim/webservices/BlobServerWebService/1.1.2/BlobServerWebService-1.1.2.war
chmod 755 webapps/BlobServerWebService-1.1.2.war
echo done

# 10. start tomcat
echo starting Tomcat container
./bin/startup.sh
echo done

#install script finished
echo installation complete
