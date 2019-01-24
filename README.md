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

* __/graph-builder/graph__: Get graph of cities
* __/graph-builder/graph/nodes__: Get graph node set
* __/graph-builder/graph/adj__: Get graph adjacency map
* __/graph-builder/directed-graph__: Get directed graph of cities
* __/graph-builder/directed-graph/nodes__: Get directed graph node set
* __/graph-builder/directed-graph/adj__: Get directed graph adjacency map

B. city-path-finder-service

city-path-finder-controller:

* __/path-finder/connected/origin/{origin}/destination/{destination}__: Check if two cities are connected (in undirected graph)
* __/path-finder/paths-all/origin/{origin}/destination/{destination}__: Get all paths from origin to destination
* __/path-finder/paths-shortest/origin/{origin}/destination/{destination}__: Get shortest paths from origin to destination

hystrix-fallback-demo-controller:

* __/hystrix/connected/origin/{origin}/destination/{destination}__: Demonstrate Hystrix fallback for method to check city connections
* __/hystrix/paths-all/origin/{origin}/destination/{destination}__: Demonstrate Hystrix fallback for method to get all paths
* __/hystrix/paths-shortest/origin/{origin}/destination/{destination}__: Demonstrate Hystrix fallback for method to get shortest paths

### Architecture

* Service discovery using Netflix Eureka (Go to Eureka service URI)
* API gateway service using Netflix Zuul (See log for evidence of filtering of API requests to city-graph-builder-service)
* Load balancing using Netflix Ribbon (Try running multiple instances of city-graph-builder-service with different ports and check Zuul service log)
* Caching using Hazelcast (Try making same API calls to city-path-finder-service and check log)

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
