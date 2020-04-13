package team.star.blog.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import team.star.blog.service.ContentService;
import team.star.blog.service.MetaService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mystic
 */
@WebFluxTest(MetaController.class)
@ExtendWith(RestDocumentationExtension.class)
class MetaControllerTest {

    @Autowired
    private ApplicationContext context;
    private WebTestClient client;

    @MockBean
    private MetaService metaService;

    @BeforeEach
    void setUp(RestDocumentationContextProvider provider) {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createMeta() {
    }

    @Test
    void updateMeta() {
    }

    @Test
    void findMetaById() {
    }

    @Test
    void findAllMetas() {
    }
}