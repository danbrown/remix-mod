import org.spongepowered.asm.gradle.plugins.MixinExtension
import org.spongepowered.asm.gradle.plugins.struct.DynamicProperties
import java.text.SimpleDateFormat
import java.util.*

buildscript {
  repositories {
    mavenCentral()
    maven {
      url = uri("https://plugins.gradle.org/m2/")
    }
  }
  dependencies {
    val kotlinVersion = "1.9.0"
    classpath(kotlin("gradle-plugin", version = kotlinVersion))
    classpath(kotlin("serialization", version = kotlinVersion))
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    classpath("org.spongepowered:mixingradle:0.7.+")
  }
}

apply(plugin = "kotlin")
apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
apply(plugin = "org.spongepowered.mixin")

plugins {
  eclipse
  `maven-publish`
  id("net.neoforged.gradle") version "[6.0.13,6.2)"
//  id("org.jetbrains.kotlin.jvm") version "1.9.0"
//  id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
  kotlin("jvm") version "1.9.0"
  kotlin("plugin.serialization") version "1.9.0"
}

// get from gradle.properties
val minecraft_version: String by project
val minecraft_version_range: String by project
val forge_version: String by project
val forge_version_range: String by project
val loader_version_range: String by project
val mapping_channel: String by project
val mapping_version: String by project

val mod_id: String by project
val mod_name: String by project
val mod_license: String by project
val mod_version: String by project
val mod_group_id: String by project
val mod_authors: String by project
val mod_description: String by project
val mod_url: String by project

// set common properties
version = mod_version
group = mod_group_id

// set java version, default is 17
val javaVersion = 17

java.toolchain.languageVersion.set(JavaLanguageVersion.of(javaVersion))

println(
  "Java: " + System.getProperty("java.version") + " JVM: " + System.getProperty("java.vm.version") + "(" + System.getProperty("java.vendor") + ") Arch: " + System.getProperty("os.arch")
)

kotlin {
  jvmToolchain(javaVersion)
}

minecraft {
  mappings(mapping_channel, mapping_version)

  // This property allows configuring Gradle's ProcessResources task(s) to run on IDE output locations before launching the game.
  // It is REQUIRED to be set to true for this template to function.
  // See https://docs.gradle.org/current/dsl/org.gradle.language.jvm.tasks.ProcessResources.html
  copyIdeResources.set(true)

  accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))

  runs.all {
    mods {
      workingDirectory(project.file("run"))
      property("forge.logging.markers", "REGISTRIES")
      property("forge.logging.console.level", "debug")
      property("forge.enabledGameTestNamespaces", mod_id)
      property("terminal.jline", "true")
      mods {
        create(mod_id) {
          source(sourceSets.main.get())
        }
      }
    }
  }

  runs.run {
    create("client") {
      property("log4j.configurationFile", "log4j2.xml")

      // for hotswap https://forge.gemwire.uk/wiki/Hotswap
      // jvmArg("-XX:+AllowEnhancedClassRedefinition")

      args(
        "--username", "DanBrown_",
      )
    }

    create("server") {}

    create("gameTestServer") {}

    create("data") {
      workingDirectory(project.file("run"))
      args(
        "--mod",
        mod_id,
        "--all",
        "--output",
        file("src/generated/resources/"),
        "--existing",
        file("src/main/resources")
      )
    }

  }
}

sourceSets.main.configure { resources.srcDirs("src/generated/resources/") }

configurations {
  minecraftLibrary {
    exclude("org.jetbrains", "annotations")
  }
}

repositories {
  mavenCentral()
  maven {
    name = "Kotlin for Forge"
    setUrl("https://thedarkcolour.github.io/KotlinForForge/")
  }
}

dependencies {
  minecraft("net.neoforged:forge:${minecraft_version}-${forge_version}")
  annotationProcessor("org.spongepowered:mixin:0.8.5:processor")
  implementation("thedarkcolour:kotlinforforge:4.3.0")
}

val Project.mixin: MixinExtension get() = extensions.getByType()

mixin.run {
  add(sourceSets.main.get(), "${mod_id}.mixins.refmap.json")
  config("${mod_id}.mixins.json")
  val debug = this.debug as DynamicProperties
  debug.setProperty("verbose", true)
  debug.setProperty("export", true)
  setDebug(debug)
}


// This block of code expands all declared replace properties in the specified resource targets.
// A missing property will result in an error. Properties are expanded using ${} Groovy notation.
// When "copyIdeResources" is enabled, this will also run before the game launches in IDE environments.
// See https://docs.gradle.org/current/dsl/org.gradle.language.jvm.tasks.ProcessResources.html
tasks.named("processResources", ProcessResources::class.java).configure {
  val replaceProperties = mapOf(
    "minecraft_version" to minecraft_version,
    "minecraft_version_range" to minecraft_version_range,
    "forge_version" to forge_version,
    "forge_version_range" to forge_version_range,
    "loader_version_range" to loader_version_range,
    "mod_id" to mod_id,
    "mod_name" to mod_name,
    "mod_license" to mod_license,
    "mod_version" to mod_version,
    "mod_authors" to mod_authors,
    "mod_description" to mod_description
//    "project" to project
  )
  inputs.properties(replaceProperties)

  filesMatching(listOf("META-INF/mods.toml", "pack.mcmeta")) {
    expand(replaceProperties + mapOf("project" to project))
  }
}

tasks.withType<Jar> {
  archiveBaseName.set(mod_id)
  manifest {
    val map = HashMap<String, String>()
    map["Specification-Title"] = mod_id
    map["Specification-Vendor"] = mod_authors
    map["Specification-Version"] = "1"
    map["Implementation-Title"] = mod_name
    map["Implementation-Version"] = mod_version
    map["Implementation-Vendor"] = mod_authors
    map["Implementation-License"] = mod_license
    map["Implementation-URL"] = mod_url
    map["Implementation-Description"] = mod_description
    map["Implementation-Timestamp"] = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(Date())
    attributes(map)
  }
  finalizedBy("reobfJar")
}

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      from(components["java"])
    }
  }
  repositories {
    maven {
      url = uri("file://${project.projectDir}/mcmodsrepo")
    }
  }
}

tasks.withType<JavaCompile>().configureEach {
  options.encoding = "UTF-8"
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  kotlinOptions {
    jvmTarget = "${javaVersion}"
  }
}


tasks {
  val runDataClient by registering {
    dependsOn(named("runClient"), named("runData"))

    doLast {
      // This block will run after both runData and runClient tasks are complete
      println("Custom task runDataClient completed.")
    }
  }
}