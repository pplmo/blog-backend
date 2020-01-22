package team.star.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.Content;
import team.star.blog.service.ContentService;

import javax.annotation.Resource;

/**
 * @author mystic
 */
@RestController
@RequestMapping("/content")
public class ContentController {
    @Resource
    private ContentService contentService;

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
