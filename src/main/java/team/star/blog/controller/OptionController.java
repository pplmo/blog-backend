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
import team.star.blog.pojo.Option;
import team.star.blog.service.OptionService;

import javax.annotation.Resource;

/**
 * @author mystic
 */
@RestController
@RequestMapping("/option")
public class OptionController {
    @Resource
    private OptionService optionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Option> createOption(@RequestBody Option option) {
        return optionService.save(option);
    }

    @PatchMapping
    public Mono<Option> updateOption(@RequestBody Option option) {
        return optionService.save(option);
    }

    @GetMapping("/{id}")
    public Mono<Option> findOptionById(@PathVariable int id) {
        return optionService.findById(id);
    }

    @GetMapping
    public Flux<Option> findAllOptions() {
        return optionService.findAll();
    }
}
