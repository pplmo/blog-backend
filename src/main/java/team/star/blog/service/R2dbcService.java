package team.star.blog.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author mystic
 */
public interface R2dbcService<T, ID> {
    /**
     * create T
     *
     * @param t T
     * @return Mono<T>
     */
    Mono<T> create(T t);

    /**
     * update T
     * @param t T
     * @return Mono<T>
     */
    Mono<T> update(T t);

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
     * delete T by id
     *
     * @param id id
     * @return Mono<Void>
     */
    Mono<Void> deleteById(ID id);
}
