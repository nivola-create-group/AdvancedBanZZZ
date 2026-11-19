plugins {
    `java`
    `maven-publish`
    id("com.gradleup.shadow") version "8.3.11" apply false
}

group = properties["group"] as String
version = properties["version"] as String

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

allprojects {
    repositories {
        mavenCentral()
        mavenLocal()
        maven { url = uri("https://maven.canvasmc.io/releases") }
        maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
        maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
        maven { url = uri("https://repo.md-5.net/content/repositories/snapshots/") }
        maven { url = uri("https://repo.codemc.org/repository/maven-public") }
        maven { url = uri("https://repo.cloudnetservice.eu/releases") }
        maven { url = uri("https://repo.cloudnetservice.eu/repository/releases/") }
        maven { url = uri("https://cloudnetservice.eu/repositories") }
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(25))
        }
        withJavadocJar()
        withSourcesJar()
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    tasks.withType<Javadoc> {
        options.encoding = "UTF-8"
        // Ensure doclet uses UTF-8 charset as well
        (options as? org.gradle.external.javadoc.StandardJavadocDocletOptions)?.addStringOption("charSet", "UTF-8")
    }

    tasks.withType<ProcessResources> {
        // Expand common Maven-style placeholders in plugin descriptors that use ${project.version} / ${project.groupId}
        val projectMap = mapOf("version" to project.version, "groupId" to project.group.toString())
        filesMatching("plugin.yml") {
            expand("project" to projectMap)
        }
        filesMatching("bungee.yml") {
            expand("project" to projectMap)
        }
    }

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
            }
        }
    }
}

tasks.named<Jar>("jar") {
    enabled = false
}

val copyBundleJar = tasks.register<Copy>("copyBundleJar") {
    from(project(":bundle").tasks.named("shadowJar"))
    into(layout.buildDirectory.dir("libs"))
}

tasks.named("assemble") {
    dependsOn(copyBundleJar)
}

tasks.named("build") {
    dependsOn(copyBundleJar)
}
