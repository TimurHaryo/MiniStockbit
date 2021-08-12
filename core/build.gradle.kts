plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdkVersion(AppConfig.compileSdkVersion)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        minSdkVersion(AppConfig.minSdkVersion)
        targetSdkVersion(AppConfig.targetSdkVersion)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = AppConfig.jvmTarget
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(CoreLibs.kotlinStdlib)
    implementation(CoreLibs.coreKtx)

    implementation(WidgetLibs.appCompat)
    implementation(WidgetLibs.material)

    // Retrofit & Gson
    implementation(NetworkLibs.retrofit)

    // Kotlin Coroutines - Asynchronous
    implementation(ReactiveLibs.coroutinesCore)
    implementation(ReactiveLibs.coroutinesAndroid)

    // Lifecycle Components
    implementation(LifecycleLibs.lifecycleViewModel)
    implementation(LifecycleLibs.lifecycleRuntime)
    implementation(LifecycleLibs.lifecycleLiveData)
}