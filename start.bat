set MAVEN_OPTS=-XX:MaxPermSize=700m -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8765
mvn -B jetty:run