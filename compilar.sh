#!/bin/bash
cd Entidades
javac -d ../WEB-INF/classes/ *.java -classpath /usr/local/Cellar/tomcat/7.0.47/libexec/lib/servlet-api.jar:/usr/local/Cellar/tomcat/7.0.47/libexec/lib/mysql-connector-java-5.1.27-bin.jar:/usr/local/Cellar/tomcat/7.0.47/libexec/webapps/SEng/WEB-INF/classes/
echo "entidades compilado"
cd ..
cd Controles
javac -d ../WEB-INF/classes/ *.java -classpath /usr/local/Cellar/tomcat/7.0.47/libexec/lib/servlet-api.jar:/usr/local/Cellar/tomcat/7.0.47/libexec/lib/mysql-connector-java-5.1.27-bin.jar:/usr/local/Cellar/tomcat/7.0.47/libexec/webapps/SEng/WEB-INF/classes/
echo "controles compilado"
cd ..
cd Interfaces
javac -d ../WEB-INF/classes/ *.java -classpath /usr/local/Cellar/tomcat/7.0.47/libexec/lib/servlet-api.jar:/usr/local/Cellar/tomcat/7.0.47/libexec/lib/mysql-connector-java-5.1.27-bin.jar:/usr/local/Cellar/tomcat/7.0.47/libexec/webapps/SEng/WEB-INF/classes/
echo "interfaces compilado"
