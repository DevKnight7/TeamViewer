#FROM openjdk:17
#ARG JAR_FILE=target/*.jar
#ENV POSTGRES_DB=mydatabase \
#    POSTGRES_PASSWORD=dev \
#    POSTGRES_USER=dev \
#    SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/spring \
#    SPRING_DATASOURCE_USERNAME=dev \
#    SPRING_DATASOURCE_PASSWORD=dev \
#    SPRING_JPA_HIBERNATE_DDL_AUTO=create-drop \
#    SPRING_JPA_SHOW_SQL=true \
#    SPRING_JPA_PROPERTIES_hibernate_default_schema=market_schema
#WORKDIR /app
#COPY ${JAR_FILE} app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app.jar"]