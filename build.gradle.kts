import java.time.Year

plugins {
    `java-library`
    `maven-publish`
    alias(libs.plugins.ktlint)
    alias(libs.plugins.lombok)
    alias(libs.plugins.spotless)
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.maven.apache.org/maven2/")
}

group = "nl.martijnmuijsers"
version = "1.0.1"
description = "Martijn's string utilities"

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

val author = "Martijn Muijsers (martijnmuijsers@live.nl)"
val creationYear = "2018"
val license = "Licensed under AGPLv3."

spotless {
    java {
        importOrder()
        removeUnusedImports()
        cleanthat()
        palantirJavaFormat()
        licenseHeader("/*\n * © $author $creationYear-${Year.now()}.\n * $license\n */")
    }
}
