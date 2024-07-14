/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

group = "com.wire"
version = "1.3.2"
description = "Helium"
java.sourceCompatibility = JavaVersion.VERSION_1_9

plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    implementation(libs.com.wire.xenon)

    api(libs.jakarta.ws.rs.jakarta.ws.rs.api)
    api(libs.org.glassfish.tyrus.bundles.tyrus.standalone.client)
    api(libs.org.glassfish.jersey.core.jersey.client)
    api(libs.org.flywaydb.flyway.core)
    api(libs.org.glassfish.jersey.inject.jersey.hk2)
    api(libs.com.fasterxml.jackson.jaxrs.jackson.jaxrs.json.provider)

    testImplementation(libs.org.slf4j.slf4j.api)
    testImplementation(libs.org.slf4j.slf4j.log4j12)
    testImplementation(libs.javax.activation.activation)
    testImplementation(libs.org.postgresql.postgresql)
    testImplementation(libs.org.junit.jupiter.junit.jupiter)
    testImplementation(libs.org.junit.jupiter.junit.jupiter.engine)
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
