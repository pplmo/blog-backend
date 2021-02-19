package team.star.blog.service.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.Comment;
import team.star.blog.repository.CommentRepository;
import team.star.blog.service.CommentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mystic
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentRepository commentRepository;

    @Override
    public Mono<Comment> save(Comment comment) {
        return null;
    }

    @Override
    public Mono<Comment> findById(Integer integer) {
        return null;
    }

    @Override
    public Flux<Comment> findAll() {
        return null;
    }

    @Override
    public Flux<Comment> fetchByIds(List<Integer> ids) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(Integer integer) {
        return null;
    }
}
