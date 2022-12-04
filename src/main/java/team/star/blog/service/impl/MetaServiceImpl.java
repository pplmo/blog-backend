package team.star.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.Meta;
import team.star.blog.repository.MetaRepository;
import team.star.blog.service.MetaService;

import java.util.List;

/**
 * @author mystic
 */
@Service
public class MetaServiceImpl implements MetaService {

    private MetaRepository metadataRepository;

    @Autowired
    public MetaServiceImpl(MetaRepository metadataRepository) {
        this.metadataRepository = metadataRepository;
    }

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
    public Flux<Meta> fetchByIds(List<Integer> ids) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(Integer integer) {
        return null;
    }
}
