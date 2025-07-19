plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.skie)
}

kotlin {
    jvmToolchain(17)

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    androidTarget()

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)

            implementation(project.dependencies.platform(libs.firebase.bom))
            implementation(libs.firebase.vertexai)
        }
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization)
            implementation(libs.kotlinx.serialization.json)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.markdown.renderer)

            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)

            implementation(libs.lifecyle.runtime)

            implementation(libs.coil.compose)
        }
    }
}

android {
    namespace = "dev.johnoreilly.vertexai"
    compileSdk = 36

    defaultConfig {
        applicationId = "dev.johnoreilly.vertexai"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    debugImplementation(libs.androidx.compose.ui.tooling)
}


