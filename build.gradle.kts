import com.google.cloud.tools.jib.gradle.JibExtension
import org.springdoc.openapi.gradle.plugin.OpenApiExtension

group = "team.star"
version = "0.0.1-SNAPSHOT"

val mavenUrls = listOf(
    "https://maven.aliyun.com/repository/public",
    "https://maven.aliyun.com/repository/spring",
    "https://maven.aliyun.com/repository/gradle-plugin",
    "https://maven.aliyun.com/repository/spring-plugin"
)

repositories {
    mavenUrls.forEach { url ->
        maven {
            this.url = uri(url)
        }
    }
    mavenLocal()
    mavenCentral()
}

plugins {
    java
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.asciidoctor.jvm.convert") version "4.0.2"
    /* for OpenAPI */
    id("com.github.johnrengelman.processes") version "0.5.0"
    id("org.springdoc.openapi-gradle-plugin") version "1.8.0"
    /* generate image and then push it to DockerHub */
    id("com.google.cloud.tools.jib") version "3.4.3"
    id("org.sonarqube") version "5.0.0.4638"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    create("asciidoctorExt")
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.5.0")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("org.postgresql:r2dbc-postgresql")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    /* for rest docs */
    testImplementation("org.springframework.restdocs:spring-restdocs-webtestclient")
    "asciidoctorExt"("org.springframework.restdocs:spring-restdocs-asciidoctor")
}

/* for rest docs */
val snippetsDir = file("${layout.buildDirectory.get().asFile}/generated-snippets")

tasks.test {
    outputs.dir(snippetsDir)
    useJUnitPlatform()
}

tasks.named("asciidoctor").configure {
    inputs.dir(snippetsDir)
    dependsOn(tasks.test)
}

tasks.named("bootJar").configure {
}

// TODO this is a workaround, remove it upon the issue is fixed
tasks.named("forkedSpringBootRun").configure {
    doNotTrackState("See https://github.com/springdoc/springdoc-openapi-gradle-plugin/issues/102")
}

sonar {
    properties {
        property("sonar.projectKey", project.findProperty("sonarProjectKey") ?: "pplmo_blog-backend")
        property("sonar.organization", project.findProperty("sonarOrganization") ?: "pplmo")
        property("sonar.host.url", project.findProperty("sonarHostUrl") ?: "https://sonarcloud.io")
    }
}

/* ====== specify DockerHub repo By Google JIB framework ====== */
// if logged on your local docker, you don't need to configure the following two global variable
// Or, you need to configure them in gradle.properties and use them as follows.
configure<JibExtension> {
    from.image = "amazoncorretto:${java.sourceCompatibility}"

    if (!hasProperty("DOCKER_HUB_USERNAME") || !hasProperty("DOCKER_HUB_PASSWORD")) {
        to.image = "pplmx/blog"
    } else {
        to.image = "${project.property("DOCKER_HUB_USERNAME")}/blog"
        to.auth.username = project.property("DOCKER_HUB_USERNAME") as String
        to.auth.password = project.property("DOCKER_HUB_PASSWORD") as String
    }
}

configure<OpenApiExtension> {
    apiDocsUrl.set(uri("http://localhost:8080/api/docs").toString())
    outputDir.set(file("${layout.buildDirectory.get().asFile}/docs"))
    outputFileName.set("openapi.yml")
    waitTimeInSeconds.set(10)
}
