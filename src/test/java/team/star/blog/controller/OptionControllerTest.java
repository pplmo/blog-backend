package team.star.blog.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import team.star.blog.service.ContentService;
import team.star.blog.service.OptionService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mystic
 */
@WebFluxTest(OptionController.class)
@ExtendWith(RestDocumentationExtension.class)
class OptionControllerTest {

    @Autowired
    private ApplicationContext context;
    private WebTestClient client;

    private OptionService optionService;

    @BeforeEach
    void setUp(RestDocumentationContextProvider provider) {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createOption() {
    }

    @Test
    void updateOption() {
    }

    @Test
    void findOptionById() {
    }

    @Test
    void findAllOptions() {
    }
}