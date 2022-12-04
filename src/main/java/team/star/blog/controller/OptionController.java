package team.star.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import team.star.blog.pojo.Option;
import team.star.blog.service.OptionService;

/**
 * @author mystic
 */
@RestController
@RequestMapping("/option")
public class OptionController {

    private final OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

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
