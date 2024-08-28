import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    jvm("desktop")
    
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

    val osName = System.getProperty("os.name")
    val targetOs = when {
        osName == "Mac OS X" -> "macos"
        osName.startsWith("Win") -> "windows"
        osName.startsWith("Linux") -> "linux"
        else -> error("Unsupported OS: $osName")
    }

    val targetArch = when (val osArch = System.getProperty("os.arch")) {
        "x86_64", "amd64" -> "x64"
        "aarch64" -> "arm64"
        else -> error("Unsupported arch: $osArch")
    }

    val version = "0.8.10"
    val target = "${targetOs}-${targetArch}"
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.activity.compose)
            implementation(libs.lifecycle.runtime.compose)

            implementation(libs.utils.koin.android)
            implementation(libs.utils.koin.androidx.compose)
        }
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.lifecycle.common)
            implementation(libs.lifecycle.viewmodel)

            api(libs.utils.koin.core)
            implementation(libs.utils.koin.compose)
            implementation(libs.utils.koin.compose.viewmodel)

            implementation(libs.voyager.navigator)
            implementation(libs.voyager.bottomSheetNavigator)
            implementation(libs.voyager.tabNavigator)
            implementation(libs.voyager.transitions)
            implementation(libs.voyager.koin)

            implementation(libs.utils.coil.compose.core)
            implementation(libs.utils.coil.compose)
            implementation(libs.utils.coil.mp)
        }
        desktopMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)

            // This is a workaround for org.jetbrains.skiko.LibraryLoadException: Cannot find libskiko-macos-arm64.dylib
            //noinspection UseTomlInstead
            implementation("org.jetbrains.skiko:skiko-awt-runtime-$target:$version")
            implementation(compose.desktop.currentOs)
            implementation(libs.lifecycle.runtime.compose)
        }
    }
}

//dependencies {
//    add("kspCommonMainMetadata", libs.plugins.ksp) // Run KSP on [commonMain] code
//    add("kspAndroid", libs.plugins.ksp)
//    add("kspIosX64", libs.plugins.ksp)
//    add("kspIosArm64", libs.plugins.ksp)
//    add("kspIosSimulatorArm64", libs.plugins.ksp)
//}

android {
    namespace = "com.mr.nemo.dragonfly"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.mr.nemo.dragonfly"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        coreLibraryDesugaring(libs.desugaring)

        debugImplementation(compose.uiTooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.mr.nemo.dragonfly"
            packageVersion = "1.0.0"
        }
    }
}
