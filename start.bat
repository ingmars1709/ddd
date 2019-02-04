set MAVEN_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8765
mvn -B jetty:run