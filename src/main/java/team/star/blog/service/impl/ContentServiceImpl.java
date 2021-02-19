package team.star.blog.service.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.Content;
import team.star.blog.repository.ContentRepository;
import team.star.blog.service.ContentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mystic
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Resource
    private ContentRepository contentRepository;

    @Override
    public Mono<Content> save(Content content) {
        return null;
    }

    @Override
    public Mono<Content> findById(Integer integer) {
        return null;
    }

    @Override
    public Flux<Content> findAll() {
        return null;
    }

    @Override
    public Flux<Content> fetchByIds(List<Integer> ids) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(Integer integer) {
        return null;
    }
}
