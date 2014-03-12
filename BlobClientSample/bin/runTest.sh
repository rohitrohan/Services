#!/bin/bash

# check java home
if [ -z "$JAVA_HOME" ] 
    then
    echo "JAVA_HOME environment variable is NOT defined"
    echo "This variable is needed to run this program"
fi

# classpath
unset CLASSPATH
CLASSPATH=.
CLASSPATH=$CLASSPATH:target/classes
for file in $( ls lib/*.jar )
 do
    CLASSPATH=$CLASSPATH:$file
 done

# run

$JAVA_HOME/bin/java -classpath $CLASSPATH TestServiceClient



