mvn clean package -Dmaven.test.skip=true

rm -f /E/jetty/webapps/jdonmvc_cqrs_es.war

cp ./target/jdonmvc_cqrs_es.war /E/jetty/webapps
