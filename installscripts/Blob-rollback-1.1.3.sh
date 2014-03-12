#!/bin/sh
echo starting rollback

# cd to catalina_base
#cd /export/webservices.manheim.com/BlobServerWebService/pronixwsc0101/1/

# 1. shut down tomcat
echo shutting down tomcat container
./bin/shutdown.sh
echo done

# 2. delete new war file and new exploded dir
echo removing BlobServerWebService-1.1.2
rm -r webapps/BlobServerWebService-1.1.2*
echo done

# 3. restore backup war
echo restoring BlobServerWebService-1.1.1
mv webapps/BlobServerWebService-1.1.1.bak webapps/BlobServerWebService-1.1.1.war
echo done

# 4. remove new context.xml
echo removing enterprise jndi context.xml file
rm conf/context.xml
echo done

# 5. restore old context.xml
echo restoring context.xml file
mv conf/context.bak conf/context.xml
echo done

# 6. remove manheim-enterprise-jndi lib
echo removing enterprise jndi library
rm lib/manheim-enterprise-jndi-1.0.0.jar
echo done

# 7. remove temp files
echo removing temporary files
rm temp/* work/* conf/Catalina/localhost/*
echo done

# 8. archive log files
echo archiving log files
mkdir logs/archive1.0.1
mv logs/*.out logs/archive1.0.1
mv logs/*.log logs/archive1.0.1
touch logs/catalina.out
echo done

# 9. start tomcat
echo starting tomcat
./bin/startup.sh
echo done

# 10. done
echo rollback script completed


 