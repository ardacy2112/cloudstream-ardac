plugins {
    kotlin("jvm") version "1.9.22"
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("com.github.recloudstream:cloudstream:4.5.1")
}

tasks.withType<Jar> {
    archiveBaseName.set("ArdacRepo")
}
