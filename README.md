# demo-microservice-city-connection-finder-full-version
Extended Version of City Connection Finder Microservice Demo Application

Go [here](https://github.com/aifoss/demo-microservice-city-connection-finder-prototype) for a (fully-functional) prototype version of the application that strictly conforms to the problem specification.

## Tools/Technologies Used

* Java 1.8
* Spring Boot
* Spring Cloud
* Netflix OSS (Eureka, Ribbon, Zuul, Hystrix)
* Hazelcast
* JUnit, Mockito 
* Spring Tool Suite
* Swagger 2

## Component Microservices

* netflix-eureka-discovery-service
* netflix-zuul-api-gateway-service
* city-graph-builder-service
* city-path-finder-service

## Extensions/Enhancements

### Input

Extended input file with 75 unique city names

### API

A. city-graph-builder-service

city-graph-builder-controller:

* /graph-builder/graph: Get graph of cities
* /graph-builder/graph/nodes: Get graph node set
* /graph-builder/graph/adj: Get graph adjacency map
* /graph-builder/directed-graph: Get directed graph of cities
* /graph-builder/directed-graph/nodes: Get directed graph node set
* /graph-builder/directed-graph/adj: Get directed graph adjacency map

B. city-path-finder-service

city-path-finder-controller:

* /path-finder/connected/origin/{origin}/destination/{destination}: Check of two cities are connected (in undirected graph)
* /path-finder/paths-all/origin/{origin}/destination/{destination}: Get all paths from origin to destination
* /path-finder/paths-shortest/origin/{origin}/destination/{destination}: Get shortest paths from origin to destination

hystrix-fallback-demo-controller:

* /hystrix/connected/origin/{origin}/destination/{destination}: Demonstrate Hystrix fallback for method to check city connections
* /hystrix/paths-all/origin/{origin}/destination/{destination}: Demonstrate Hystrix fallback for method to get all paths
* /hystrix/paths-shortest/origin/{origin}/destination/{destination}: Demonstrate Hystrix fallback for method to get shortest paths

### Architecture

* Service discovery using Netflix Eureka
* API gateway service using Netflix Zuul
* Routing and load balancing using Netflix Ribbon
* Caching using Hazelcast

## Testing Instructions

1. Clone the repository.
2. Issue the following commands in separate terminal windows in the specified order:

   java -jar netflix-eureka-discovery-service-1.jar NetflixEurekaDiscoveryServiceApplication.class
   
   java -jar netflix-zuul-api-gateway-service-1.jar NetflixZuulApiGatewayServiceApplication.class
   
   java -jar city-graph-builder-service-1.jar CityGraphBuilderServiceApplication.class
   
   java -jar city-path-finder-service-1.jar CityPathFinderServiceApplication.class

3. To test Eureka service discovery, go to http://localhost:8761
4. To test city-graph-builder-service API, go to http://localhost:8000/swagger-ui.html
5. To test city-path-finder-service API, go to http://localhost:8010/swagger-ui.html
