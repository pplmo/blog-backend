package team.star.blog.controller;

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

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

/**
 * @author mystic
 */
@WebFluxTest(ContentController.class)
@ExtendWith(RestDocumentationExtension.class)
class ContentControllerTest {

    @Autowired
    private ApplicationContext context;
    private WebTestClient client;

    @MockBean
    private ContentService contentService;

    @BeforeEach
    void setUp(RestDocumentationContextProvider provider) {
        client = WebTestClient.bindToApplicationContext(context)
                .configureClient()
                .filter(
                        documentationConfiguration(provider)
                                .operationPreprocessors()
                                .withRequestDefaults(prettyPrint())
                                .withResponseDefaults(prettyPrint())
                )
                .build();
    }

    @Test
    void createContent() {
    }

    @Test
    void updateContent() {
    }

    @Test
    void findContentById() {
    }

    @Test
    void findAllContents() {
    }
}