import java.time.Year

plugins {
    `java-library`
    `maven-publish`
    alias(libs.plugins.ktlint)
    alias(libs.plugins.lombok)
    alias(libs.plugins.spotless)
    alias(libs.plugins.yaml.validator)
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.maven.apache.org/maven2/")
}

group = "nl.martijnmuijsers"
version = "1.0.1"
description = "Martijn's string utilities"

object FileHeader {
    const val AUTHOR = "Martijn Muijsers <martijnmuijsers@live.nl>"
    const val CREATION_YEAR = "2018"
    const val LICENSE_TEXT = "Licensed under AGPLv3."
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(libs.versions.java.get())
    }
}

tasks.withType<JavaCompile> {
    options.encoding = Charsets.UTF_8.name()
    options.release = libs.versions.java.get().toInt()
}

tasks.withType<Javadoc> {
    options.encoding = Charsets.UTF_8.name()
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

spotless {
    java {
        importOrder()
        removeUnusedImports()
        cleanthat()
        palantirJavaFormat()
        licenseHeader(
            """
            /*
             * © ${FileHeader.AUTHOR} ${FileHeader.CREATION_YEAR}-${Year.now()}.
             * ${FileHeader.LICENSE_TEXT}
             */
            """.trimIndent(),
        )
    }
}

yamlValidator {
    searchPaths = listOf(".github", ".github/workflows/")
}
