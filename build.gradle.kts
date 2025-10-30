plugins {
    id("java")
    id("io.qameta.allure") version "2.11.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Test framework
    testImplementation("org.testng:testng:7.9.0")

    // UI automation
    testImplementation("com.codeborne:selenide:6.16.1")

    // Allure TestNG adapter
    testImplementation("io.qameta.allure:allure-testng:2.20.1")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

allure {
    version.set("2.20.1")
    adapter {
        autoconfigure.set(true)
        aspectjWeaver.set(true)
    }

}

tasks.register("cleanAllure") {
//  Clean folders for allure to not be a tiny whiny plugin
//    delete("build/reports/allure-report")
    delete("build/allure-results")
    delete("build/reports")
}

tasks.named("allureReport") {
//    Specify that task 'cleanAllure needs to be run' before the report generation
    dependsOn("cleanAllure")
}

tasks.test {
//    Main test runner. TestNG is responsible for discovery and run
//    useTestNG()
    useTestNG {
        // Tell Gradle to use your testng.xml file
        suites("testng.xml")
    }
    outputs.upToDateWhen { false } // Force tests to always be not up to date so it always runs.
    finalizedBy("allureReport") // runs after tests finish
}

// Task 1: run tests in src/test/java/Task1
val testTask1 = tasks.register<Test>("testTask1") {
    useTestNG()
    include("Task1/**") // Only run tests in Task1 package
    outputs.upToDateWhen { false }
    finalizedBy("allureReport")
}

// Task 2: run tests in src/test/java/Task2
val testTask2 = tasks.register<Test>("testTask2") {
    useTestNG()
    include("Task2/**") // Only run tests in Task2 package
    outputs.upToDateWhen { false }
    finalizedBy("allureReport")
}