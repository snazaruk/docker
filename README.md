**Technologies**

- Java 8
- Maven as build tool
- Spring Boot
- Guava
- Lombok

**Building**

1. Build

        mvn clean package

2. Start application

        java -jar target/routes-1.0-SNAPSHOT.war data/example

3. Try some requests:

        http://localhost:8088/api/direct?dep_sid=0&arr_sid=4
