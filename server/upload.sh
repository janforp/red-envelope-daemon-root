#!/bin/sh
myPath="mobile-webapp"
if [ -e "$myPath" ]; then
  rm $myPath -rf
fi 

myPath="mobile-webapp.tar.gz"
if [ -e "$myPath" ]; then
  rm $myPath -rf
fi

mkdir mobile-webapp &&
 cp ../target/ant-finance-mobile-webapp.war mobile-webapp &&
cd mobile-webapp &&
jar xvf ant-finance-mobile-webapp.war &&
rm ant-finance-mobile-webapp.war &&

cd - &&

rm mobile-webapp/WEB-INF/classes/application.properties && echo "rm mobile-webapp/WEB-INF/classes/application.properties"

rm mobile-webapp/WEB-INF/classes/logback.xml && echo "rm mobile-webapp/WEB-INF/classes/logback.xml"

#if [ -e "mobile-webapp/WEB-INF/classes/application-development.properties" ]; then
#  rm mobile-webapp/WEB-INF/classes/application-development.properties && echo "rm mobile-webapp/WEB-INF/classes/application-development.properties"
#fi
#if [ -e "mobile-webapp/WEB-INF/classes/application-production.properties" ]; then
#  rm mobile-webapp/WEB-INF/classes/application-production.properties && echo "rm mobile-webapp/WEB-INF/classes/application-production.properties"
#fi
#if [ -e "mobile-webapp/WEB-INF/classes/logback.xml" ]; then
#  rm mobile-webapp/WEB-INF/classes/logback.xml && echo "rm mobile-webapp/WEB-INF/classes/logback.xml"
#fi

if [ -e "mobile-webapp/tmp/" ]; then
  rm -rf mobile-webapp/tmp/ && echo "rm -rf mobile-webapp/tmp/"
fi


find mobile-webapp/WEB-INF/ | grep jar$ | grep maven | xargs rm
find mobile-webapp/WEB-INF/ | grep jar$ | grep plexus- | xargs rm

tar zcvf mobile-webapp.tar.gz mobile-webapp/ &&
scp mobile-webapp.tar.gz root@114.55.6.214:/server/ &&
rm -rf mobile-webapp*