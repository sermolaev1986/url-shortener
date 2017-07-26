* to build application from sources:
````
mvn clean package
````
* to run application:
````
java -jar target/url-shortener-1.0-SNAPSHOT.jar
````
* application will be available at localhost:8080
* use REST client of your choice to play with API. Sample flow of requests:
````
curl -i -X POST -H "Content-Type:application/json" http://localhost:8080/api/v1/account -d '{"accountId" : "1"}'
curl -i -X POST -u 1:pz8q04tZ -H "Content-Type:application/json" http://localhost:8080/api/v1/url-shortener/register -d '{"url" : "https://stackoverflow.com/questions/1567929/website-safe-data-access-architecture-question?rq=1"}'
curl -i -X GET -L http://localhost:8080/b9004a44
curl -i -X GET -u 1:pz8q04tZ -H "Content-Type:application/json" http://localhost:8080/api/v1/statistic/1
````

