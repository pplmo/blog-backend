package team.star.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.Content;
import team.star.blog.service.ContentService;

/**
 * @author mystic
 */
@RestController
@RequestMapping("/content")
public class ContentController {

    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Content> createContent(@RequestBody Content content) {
        return contentService.save(content);
    }

    @PatchMapping
    public Mono<Content> updateContent(@RequestBody Content content) {
        return contentService.save(content);
    }

    @GetMapping("/{id}")
    public Mono<Content> findContentById(@PathVariable int id) {
        return contentService.findById(id);
    }

    @GetMapping
    public Flux<Content> findAllContents() {
        return contentService.findAll();
    }
}
