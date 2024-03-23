import com.vaadin.gradle.getBooleanProperty
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.9.23"
    id("com.vaadin") version "24.2.12"
    application

}

defaultTasks("clean", "build")

repositories {
    mavenCentral()
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        // to see the exceptions of failed tests in Travis-CI console.
        exceptionFormat = TestExceptionFormat.FULL
    }
}

dependencies {
    val VAADIN_VERSION = "24.2.12"
    val VOK_VERSION = "0.16.0"
    // Vaadin
    implementation("eu.vaadinonkotlin:vok-framework-vokdb:${VOK_VERSION}")
    implementation("com.vaadin:vaadin-core:${VAADIN_VERSION}") {
        afterEvaluate {
            if (vaadin.productionMode) {
                exclude(module = "vaadin-dev")
            }
        }
    }
    implementation("com.github.mvysny.vaadin-boot:vaadin-boot:12.0")
    implementation("com.github.mvysny.vaadin-simple-security:vaadin-simple-security:0.2")

    implementation("com.xdev-software:vaadin-maps-leaflet-flow:4.0.1")
    implementation(files("libs/mediaquery-5.0.3.jar"))


    implementation("org.slf4j:slf4j-simple:2.0.6")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

application {
    mainClass = "com.example.karibudsl.MainKt"
}
