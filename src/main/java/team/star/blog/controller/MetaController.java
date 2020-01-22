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
import team.star.blog.pojo.Meta;
import team.star.blog.service.MetaService;

import javax.annotation.Resource;

/**
 * @author mystic
 */
@RestController
@RequestMapping("/meta")
public class MetaController {

    @Resource
    private MetaService metaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Meta> createMeta(@RequestBody Meta meta) {
        return metaService.save(meta);
    }

    @PatchMapping
    public Mono<Meta> updateMeta(@RequestBody Meta meta) {
        return metaService.save(meta);
    }

    @GetMapping("/{id}")
    public Mono<Meta> findMetaById(@PathVariable int id) {
        return metaService.findById(id);
    }

    @GetMapping
    public Flux<Meta> findAllMetas() {
        return metaService.findAll();
    }

}
