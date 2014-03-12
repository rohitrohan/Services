@echo off
rem ---------------------------------------------------------------------------
rem Run sample client app.
rem
rem 
rem ---------------------------------------------------------------------------

if not "%JAVA_HOME%" == "" goto gotJavaHome
echo The JAVA_HOME environment variable is not defined
echo This environment variable is needed to run this program
goto exit

:gotJavaHome
if not exist "%JAVA_HOME%\bin\java.exe" goto noJavaHome
goto okJavaHome

:noJavaHome
echo The JAVA_HOME environment variable is not defined correctly
echo This environment variable is needed to run this program
goto exit

:okJavaHome
rem CLASSPATH
SET CLASSPATH=.
SET CLASSPATH=%CLASSPATH%;target\classes
SET CLASSPATH=%CLASSPATH%;lib\activation-1.0.2.jar
SET CLASSPATH=%CLASSPATH%;lib\axis-1.4.jar
SET CLASSPATH=%CLASSPATH%;lib\axis-jaxrpc-1.2.1.jar
SET CLASSPATH=%CLASSPATH%;lib\axis-saaj-1.2.1.jar
SET CLASSPATH=%CLASSPATH%;lib\axis-wsdl4j-1.5.1.jar
SET CLASSPATH=%CLASSPATH%;lib\commons-digester-1.5.jar
SET CLASSPATH=%CLASSPATH%;lib\commons-discovery-0.2.jar
SET CLASSPATH=%CLASSPATH%;lib\commons-io-1.1.jar
SET CLASSPATH=%CLASSPATH%;lib\commons-lang-2.1.jar
SET CLASSPATH=%CLASSPATH%;lib\commons-logging-1.0.4.jar
SET CLASSPATH=%CLASSPATH%;lib\javamail-1.3.jar
echo CLASSPATH = %CLASSPATH%


rem run (quote JAVA_HOME as may contain spaces)
echo.
%JAVA_HOME%\bin\java.exe -classpath %CLASSPATH% TestServiceClient
goto end

:exit
exit /b 1

:end
