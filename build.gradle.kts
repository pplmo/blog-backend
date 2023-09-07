import com.google.cloud.tools.jib.gradle.JibExtension
import org.springdoc.openapi.gradle.plugin.OpenApiExtension

group = "team.star"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

plugins {
    java
    id("org.springframework.boot") version "3.1.3"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.graalvm.buildtools.native") version "0.9.25"
    /* for Spring Restdocs */
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    /* for OpenAPI */
    id("com.github.johnrengelman.processes") version "0.5.0"
    id("org.springdoc.openapi-gradle-plugin") version "1.6.0"
    /* generate image and then push it to DockerHub */
    id("com.google.cloud.tools.jib") version "3.3.2"
    id("org.sonarqube") version "4.2.1.3168"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(20))
    }
    sourceCompatibility = JavaVersion.VERSION_20
}

configurations {
    create("asciidoctorExt")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.1.0")
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
val snippetsDir = file("$buildDir/generated-snippets")

tasks.test {
    outputs.dir(snippetsDir)
    useJUnitPlatform()
}

tasks.asciidoctor {
    inputs.dir(snippetsDir)
    dependsOn(tasks.test)
    attributes(mapOf(
        "snippets" to snippetsDir
    ))
}

tasks.bootJar {
    dependsOn(tasks.asciidoctor)
}

sonar {
    properties {
        property("sonar.projectKey", "pplmo_blog-backend")
        property("sonar.organization", "pplmo")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

/* ====== specify DockerHub repo By Google JIB framework ====== */
// if logged on your local docker, you don't need to configure the following two global variable
// Or, you need to configure them in gradle.properties and use them as follows.
configure<JibExtension> {
    from.image = "eclipse-temurin:${java.sourceCompatibility}"

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
    outputDir.set(file("$buildDir/docs"))
    outputFileName.set("openapi.yml")
    waitTimeInSeconds.set(10)
}

// TODO: this is a workaround, remove it later
rootProject.afterEvaluate {
    val forkedSpringBootRun = project.tasks.named("forkedSpringBootRun")
    forkedSpringBootRun.configure {
        doNotTrackState("See https://github.com/springdoc/springdoc-openapi-gradle-plugin/issues/102")
    }
}
