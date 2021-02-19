package team.star.blog.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author mystic
 */
public interface R2dbcService<T, ID> {
    /**
     * create or update T
     *
     * @param t T
     * @return Mono<T>
     */
    Mono<T> save(T t);

    /**
     * find T by id
     *
     * @param id id
     * @return Mono<T>
     */
    Mono<T> findById(ID id);

    /**
     * find all T
     *
     * @return Flux<T>
     */
    Flux<T> findAll();

    /**
     * fetch multiple entities by id list
     * @param ids multiple id in a list
     * @return Flux<T>
     */
    Flux<T> fetchByIds(List<ID> ids);

    /**
     * delete T by id
     *
     * @param id id
     * @return Mono<Void>
     */
    Mono<Void> deleteById(ID id);
}
