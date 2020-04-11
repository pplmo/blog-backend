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
import team.star.blog.service.CommentService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mystic
 */
@WebFluxTest(CommentController.class)
@ExtendWith(RestDocumentationExtension.class)
class CommentControllerTest {

    @Autowired
    private ApplicationContext context;
    private WebTestClient client;

    @MockBean
    private CommentService commentService;

    @BeforeEach
    void setUp(RestDocumentationContextProvider provider) {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createComment() {
    }

    @Test
    void updateComment() {
    }

    @Test
    void findCommentById() {
    }

    @Test
    void findAllComments() {
    }
}