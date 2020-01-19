package team.star.blog.service.impl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.Metadata;
import team.star.blog.repository.MetadataRepository;
import team.star.blog.service.MetadataService;

import javax.annotation.Resource;

/**
 * @author mystic
 */
public class MetadataServiceImpl implements MetadataService {

    @Resource
    private MetadataRepository metadataRepository;

    @Override
    public Mono<Metadata> save(Metadata metadata) {
        return null;
    }

    @Override
    public Mono<Metadata> findById(Integer integer) {
        return null;
    }

    @Override
    public Flux<Metadata> findAll() {
        return null;
    }

    @Override
    public Mono<Void> deleteById(Integer integer) {
        return null;
    }
}
