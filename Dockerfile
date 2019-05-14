FROM maven:3-jdk-13

RUN mkdir -p app

COPY . /app

WORKDIR /app

EXPOSE 8080

CMD ["mvn","spring-boot:run"]
