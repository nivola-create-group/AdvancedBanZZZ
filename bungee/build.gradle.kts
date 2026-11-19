plugins {
    `java`
}

dependencies {
    compileOnly("net.md-5:bungeecord-api:1.20-R0.1-SNAPSHOT")
    compileOnly("eu.cloudnetservice.cloudnet:driver:4.0.0-RC5")
    compileOnly("eu.cloudnetservice.cloudnet:bridge:4.0.0-RC5")
    implementation("eu.cloudnetservice.cloudnet:driver-api:4.0.0-RC17")
    implementation("eu.cloudnetservice.cloudnet:bridge-api:4.0.0-RC17")
    compileOnly("de.dytanic.cloudnet:cloudnet-api-bridge:2.1.17")
    compileOnly("de.dytanic.cloudnet:cloudnet-core:2.1.17")
    compileOnly("de.dytanic.cloudnet:cloudnet-driver:3.4.5-RELEASE")
    compileOnly("de.dytanic.cloudnet:cloudnet-bridge:3.4.5-RELEASE")
    compileOnly("net.luckperms:api:5.2")
    compileOnly("com.imaginarycode.minecraft:RedisBungee:0.6.5")

    implementation(project(":core"))
    implementation("net.kyori:adventure-text-minimessage:4.17.0")
    implementation("net.kyori:adventure-platform-bungeecord:4.3.3")
}

repositories {
    mavenCentral()
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
}