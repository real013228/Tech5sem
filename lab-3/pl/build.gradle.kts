plugins {
    id("java")
    id("org.springframework.boot") version "3.0.5"
}

group = "com.real013228"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-web:6.0.7")
    implementation("org.springframework.boot:spring-boot-starter-web:3.0.5")
    implementation("org.projectlombok:lombok:1.18.22")
    implementation(project(mapOf("path" to ":lab-3:bll")))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.5")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}