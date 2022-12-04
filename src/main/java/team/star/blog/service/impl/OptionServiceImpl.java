package team.star.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.Option;
import team.star.blog.repository.OptionRepository;
import team.star.blog.service.OptionService;

import java.util.List;

/**
 * @author mystic
 */
@Service
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;

    @Autowired
    public OptionServiceImpl(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    @Override
    public Mono<Option> save(Option option) {
        return null;
    }

    @Override
    public Mono<Option> findById(Integer integer) {
        return null;
    }

    @Override
    public Flux<Option> findAll() {
        return null;
    }

    @Override
    public Flux<Option> fetchByIds(List<Integer> ids) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(Integer integer) {
        return null;
    }
}
