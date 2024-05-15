plugins {
    `java-library`
    `maven-publish`
    alias(libs.plugins.lombok)
}

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

group = "nl.martijnmuijsers"
version = "1.0"
description = "Martijn's string utilities"

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
