import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.jvm") version "2.0.21"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
    id("maven-publish")
}

group = "com.github.clroot"
version = "1.0-RELEASE"

java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21
java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))

repositories {
    mavenCentral()
}

val apachePoiVersion = "5.4.0"
val kotestVersion = "5.9.1"

dependencies {
    implementation("org.apache.poi:poi:$apachePoiVersion")
    implementation("org.apache.poi:poi-ooxml:$apachePoiVersion")
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.mockk:mockk:1.13.10")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testImplementation("org.assertj:assertj-core:3.6.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}

task<Jar>("sourcesJar") {
    enabled = true
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
}

tasks {
    test {
        useJUnitPlatform()
    }
    compileKotlin {
        compilerOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict", "-Xcontext-receivers", "-opt-in=kotlin.ExperimentalValueClassApi")
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/clroot/excel-download")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("publish") {
            group = "com.github.clroot"
            artifactId = project.name
            version = rootProject.version.toString()
            from(components["java"])
            artifact(tasks["sourcesJar"])
        }
    }
}