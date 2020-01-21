package team.star.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.Meta;
import team.star.blog.repository.MetaRepository;

import javax.annotation.Resource;

/**
 * @author mystic
 */
@RestController
@RequestMapping("/meta")
public class MetaController {

    @Resource
    private MetaRepository metaRepository;

    @PostMapping
    public Mono<Meta> createMeta(@RequestBody Meta meta) {
        return metaRepository.save(meta);
    }

    @PatchMapping
    public Mono<Meta> updateMeta(@RequestBody Meta meta) {
        return metaRepository.save(meta);
    }

    @GetMapping("/{id}")
    public Mono<Meta> findMetaById(@PathVariable int id) {
        return metaRepository.findById(id);
    }

    @GetMapping
    public Flux<Meta> findAllMetas() {
        return metaRepository.findAll();
    }

}
