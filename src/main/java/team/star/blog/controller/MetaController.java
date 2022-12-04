package team.star.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.Meta;
import team.star.blog.service.MetaService;

/**
 * @author mystic
 */
@RestController
@RequestMapping("/meta")
public class MetaController {

    private final MetaService metaService;

    @Autowired
    public MetaController(MetaService metaService) {
        this.metaService = metaService;
    }

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
