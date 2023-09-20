# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.mgp.web.appmods.currency-conversion-service' is invalid and this project uses 'com.mgp.web.appmods.currencyconversionservice' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.3/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/index.html#using.devtools)
* [Config Client Quick Start](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#_client_side_usage)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/index.html#actuator)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/index.html#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Learning 

The following shows what we have learnt in this module
* We can build the micro services as tow differnt modules and execute individually, 
* Cloud config helps to do it easily
* It is efficient to use jvm optional ports to register each module to work.
* Instead of calling another service with its url in every method, we can also Proxy
* Proxy can be initialized 
* Using cloud-config-feign to create as proxy
* We need to add @EnableFeignClients in Spring boot Start App class
* By adding netflix eureka server we were able to do the discovery of application
* Introduced naming server with eureka and made the other application get discovered by adding the eureka endpoint in each application.properties
* /eureka gives the list of the application that was discoverable
* By running yet another instance of currency-exchange we could observe load balancing on frequent hit of the currency-conversion url
* If the spring.application.name is same and launched with multiple ports all those would be discoverable with the same name in eureka
