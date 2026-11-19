plugins {
    `java`
}

dependencies {
    compileOnly("io.canvasmc.canvas:canvas-api:26.3.build.1034-stable")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7.1")
    implementation(project(":core"))
    implementation("net.kyori:adventure-text-minimessage:4.17.0")
    implementation("net.kyori:adventure-platform-bukkit:4.3.4")
}
