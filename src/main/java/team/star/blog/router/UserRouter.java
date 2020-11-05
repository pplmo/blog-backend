package team.star.blog.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import team.star.blog.handler.UserHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;


/**
 * @author mystic
 */
@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> routes(UserHandler handler) {
        return RouterFunctions.route(GET("/router/user"), handler::getAll);
    }
}
