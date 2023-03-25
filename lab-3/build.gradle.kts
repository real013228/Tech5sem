plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.springframework:spring-core:6.0.7")
    implementation("org.springframework:spring-beans:6.0.7")
    implementation("org.springframework:spring-context:6.0.7")
    implementation("org.springframework:spring-webmvc:6.0.7")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("org.springframework.boot:spring-boot-starter-web:3.0.5")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}