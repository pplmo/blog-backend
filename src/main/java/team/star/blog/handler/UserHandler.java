package team.star.blog.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.User;
import team.star.blog.repository.UserRepository;

import javax.annotation.Resource;


/**
 * @author mystic
 */
@Component
public class UserHandler {

    @Resource
    private UserRepository repo;

    public Mono<ServerResponse> getById(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(repo.findById(id), User.class);
    }

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(repo.findAll(), User.class);
    }
}
