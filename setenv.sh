# Set all the variables need to start up the jvm and Tomcat correctly for this web service

# WS_ENV equals one of these values:  DEV, QA, STAGE, or PROD
export WS_ENV=DEV

export WS_NAME=BlobServerWebService
export WS_ABBR=blob-service
export WS_INSTANCE=1

if [ "$WS_ENV" = "DEV" ]
 then
    WS_BASE=webservices.developer.imanheim.com
    WS_ROOT=/export/webservices
elif [ "$WS_ENV" = "QA" ]
 then
    WS_BASE=webservices.imanheim.com
    WS_ROOT=/export/webservices
elif [ "$WS_ENV" = "STAGE" ]
 then
    WS_BASE=webservices.developer.manheim.com
    WS_ROOT=/export/$WS_BASE
elif [ "$WS_ENV" = "PROD" ]
 then
    WS_BASE=webservices.manheim.com
    WS_ROOT=/export/$WS_BASE
fi

export WS_BASE WS_ROOT

export JAVA_HOME=/usr/local/java1.5/jre
export JRE_HOME=/usr/local/java1.5/jre

export CATALINA_BASE=$WS_ROOT/$WS_NAME/`hostname`/$WS_INSTANCE

# Configure jvm start up options
CATALINA_OPTS="$CATALINA_OPTS -Xms256m -Xmx512m"
CATALINA_OPTS="$CATALINA_OPTS -Duser.timezone=GMT"
CATALINA_OPTS="$CATALINA_OPTS -Dlog.output.dir=$CATALINA_BASE/logs"
CATALINA_OPTS="$CATALINA_OPTS -Dsystem.environment=$WS_ENV"
CATALINA_OPTS="$CATALINA_OPTS -DUNIQUE-ID=$WS_ABBR-$WS_ENV-`hostname`-$WS_INSTANCE"
CATALINA_OPTS="$CATALINA_OPTS -DNLS_COMP=LINGUISTIC"
CATALINA_OPTS="$CATALINA_OPTS -DNLS_SORT=BINARY_CI"

# Uncomment for file system based approach
#CATALINA_OPTS="$CATALINA_OPTS -Dsystem.filepath=../webapps/filepath"

# Uncomment to monitor/debug memory use/clean up
#CATALINA_OPTS="$CATALINA_OPTS -verbose:gc"

# Uncomment to enable jconsole monitoring
#CATALINA_OPTS="$CATALINA_OPTS -Djava.rmi.server.hostname='hostname'"
#CATALINA_OPTS="$CATALINA_OPTS -Dcom.sun.management.jmxremote.port=1970"
#CATALINA_OPTS="$CATALINA_OPTS -Dcom.sun.management.jmxremote.ssl=false"
#CATALINA_OPTS="$CATALINA_OPTS -Dcom.sun.management.jmxremote.authenticate=false"

export CATALINA_OPTS

echo "WS_ENV=$WS_ENV"
echo "WS_ROOT=$WS_ROOT"
echo "CATALINA_OPTS=$CATALINA_OPTS"

