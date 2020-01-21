package team.star.blog.service.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.Meta;
import team.star.blog.repository.MetaRepository;
import team.star.blog.service.MetaService;

import javax.annotation.Resource;

/**
 * @author mystic
 */
@Service
public class MetaServiceImpl implements MetaService {

    @Resource
    private MetaRepository metadataRepository;

    @Override
    public Mono<Meta> save(Meta meta) {
        return null;
    }

    @Override
    public Mono<Meta> findById(Integer integer) {
        return null;
    }

    @Override
    public Flux<Meta> findAll() {
        return null;
    }

    @Override
    public Mono<Void> deleteById(Integer integer) {
        return null;
    }
}
