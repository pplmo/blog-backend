package team.star.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.Comment;
import team.star.blog.service.CommentService;

/**
 * @author mystic
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Comment> createComment(@RequestBody Comment comment) {
        return commentService.save(comment);
    }

    @PatchMapping
    public Mono<Comment> updateComment(@RequestBody Comment comment) {
        return commentService.save(comment);
    }

    @GetMapping("/{id}")
    public Mono<Comment> findCommentById(@PathVariable int id) {
        return commentService.findById(id);
    }

    @GetMapping
    public Flux<Comment> findAllComments() {
        return commentService.findAll();
    }
}
