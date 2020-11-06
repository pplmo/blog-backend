package team.star.blog.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import team.star.blog.handler.UserHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


/**
 * @author mystic
 */
@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> routes(UserHandler handler) {
        return route()
                .GET("/v1/router/user", accept(APPLICATION_JSON), handler::getAll)
                .POST("/v1/router/user", accept(APPLICATION_JSON), handler::save)
                .GET("/v1/router/user/{id}", accept(APPLICATION_JSON), handler::getById)
                .DELETE("/v1/router/user/{id}", accept(APPLICATION_JSON), handler::deleteById)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> routesV2(UserHandler handler) {
        return route()
                .path("/v2/router/user", builder -> builder
                        .GET("/", accept(APPLICATION_JSON), handler::getAll)
                        .POST("/", accept(APPLICATION_JSON), handler::save)
                        .GET("/{id}", accept(APPLICATION_JSON), handler::getById)
                        .DELETE("/{id}", accept(APPLICATION_JSON), handler::deleteById)
                ).build();
    }

    @Bean
    public RouterFunction<ServerResponse> routesV3(UserHandler handler) {
        return route()
                .path("/v3/router/user", b1 -> b1
                        .nest(accept(APPLICATION_JSON), b2 -> b2
                                .GET("/{id}", handler::getById)
                                .GET("/", handler::getAll)
                        )
                        .POST("/", accept(APPLICATION_JSON), handler::save)
                        .DELETE("/{id}", accept(APPLICATION_JSON), handler::deleteById)
                ).build();
    }
}
