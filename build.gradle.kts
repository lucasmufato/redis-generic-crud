import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
    id("java")
    application
}

group = "ar.com.mufato"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib")
    implementation ("io.ktor:ktor-server-core:1.6.7")
    implementation ("io.ktor:ktor-server-netty:1.6.7")
    implementation ("io.ktor:ktor-serialization:1.6.7")
    implementation ("ch.qos.logback:logback-classic:1.2.10")

    testImplementation(kotlin("test"))
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("MainKt")
}