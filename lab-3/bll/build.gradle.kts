plugins {
    id("java")
}

group = "com.real013228"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.0.5")
    implementation("org.projectlombok:lombok:1.18.22")
    implementation(project(mapOf("path" to ":lab-3:dal")))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.5")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.0.5")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}