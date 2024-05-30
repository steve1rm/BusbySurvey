plugins {
    alias(libs.plugins.jetbrainsKotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.koin.core)

    testImplementation("com.google.truth:truth:1.4.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.3.1")
    testImplementation(libs.coroutinesTest)
}