plugins {
    id("java")
}

group = "com.real013228"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.5")
    implementation("org.projectlombok:lombok:1.18.22")
    implementation("org.postgresql:postgresql:42.6.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}