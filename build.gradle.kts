import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    application
}

allprojects {
    apply(plugin = "java")
    group = "org.pdpano"
    version = "1.0-SNAPSHOT"

    task("hello").doLast {
        println("I'm ${project.name}")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
    //  https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients
        implementation("org.apache.kafka:kafka-clients:3.2.1")
    //  https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
        testImplementation("org.slf4j:slf4j-simple:1.7.36")
    //  https://mvnrepository.com/artifact/com.google.code.gson/gson
        implementation("com.google.code.gson:gson:2.9.1")

        testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    }
}

subprojects {
    apply {
        plugin("kotlin")
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}