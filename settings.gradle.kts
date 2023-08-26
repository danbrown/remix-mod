pluginManagement {
    repositories {
        maven("https://maven.neoforged.net/releases")
//        maven("https://maven.minecraftforge.net/")
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}