plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.11.0"
//    id("org.jetbrains.grammarkit") version "2022.3"
}



group = "dev.grassi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// Include the generated files in the source set
sourceSets {
    main {
        java {
            srcDirs("src/main/gen")
        }
    }
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2022.1.4")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf("com.intellij.java"))
}

//grammarKit {
//    jflexRelease.set("1.7.0-1")
//    grammarKitRelease.set("2022.3")
//}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    patchPluginXml {
        sinceBuild.set("221")
        untilBuild.set("231.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
