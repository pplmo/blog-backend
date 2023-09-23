package team.star.blog.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.User;
import team.star.blog.repository.UserRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;


/**
 * @author mystic
 */
@Component
public class UserHandler {

    private final UserRepository repo;

    @Autowired
    public UserHandler(UserRepository repo) {
        this.repo = repo;
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        return repo.findById(id)
            .flatMap(user -> ok().contentType(APPLICATION_JSON).bodyValue(user))
            .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ok()
            .contentType(APPLICATION_JSON)
            .body(repo.findAll(), User.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<User> user = request.bodyToMono(User.class);
        return ok()
            .contentType(APPLICATION_JSON)
            .body(fromPublisher(user.flatMap(repo::save), User.class));
    }

    public Mono<ServerResponse> deleteById(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        return ok()
            .contentType(APPLICATION_JSON)
            .body(repo.deleteById(id), Void.class);
    }
}
