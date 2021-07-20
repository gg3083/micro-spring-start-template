package work.gg3083.tempate.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 *
 * @author Gimi
 * @date 2021/7/20 21:56
 *
 ***/
@Configuration
public class GatewayBeanConfig {


    //    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        //常见的demo，拦截get请求，往请求头添加一个参数，然后转到http://httpbin.org:80
        return builder.routes()
                .route(p -> p
                        .path("/get")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("http://httpbin.org:80"))
                .build();
    }
}