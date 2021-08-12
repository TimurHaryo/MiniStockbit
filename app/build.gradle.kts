plugins {
    id("com.android.application")
    id("kotlin-android")
    id("androidx.navigation.safeargs")
}

android {
    compileSdkVersion(AppConfig.compileSdkVersion)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdkVersion(AppConfig.minSdkVersion)
        targetSdkVersion(AppConfig.targetSdkVersion)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.testInstrumentationRunner
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
    buildTypes.forEach { type ->
        type.buildConfigField(
            "String",
            "BASE_URL",
            "\"https://min-api.cryptocompare.com/\""
        )
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
    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.widget))

    implementation(CoreLibs.kotlinStdlib)
    implementation(CoreLibs.coreKtx)

    implementation(WidgetLibs.appCompat)
    implementation(WidgetLibs.material)
    implementation(WidgetLibs.constraintLayout)
    implementation(WidgetLibs.swipeRefreshLayout)

    // Koin
    implementation(KoinLibs.koinCore)
    implementation(KoinLibs.koinAndroid)

    // Navigation Component
    implementation(NavigationLibs.navigationFragmentKtx)
    implementation(NavigationLibs.navigationUIKtx)

    // OkHttp Interceptor
    implementation(NetworkLibs.okhttp3Logging)

    // Retrofit & Gson
    implementation(NetworkLibs.retrofit)
    implementation(NetworkLibs.gsonConverter)

    // Kotlin Coroutines - Asynchronous
    implementation(ReactiveLibs.coroutinesCore)
    implementation(ReactiveLibs.coroutinesAndroid)

    // Lifecycle Components
    implementation(LifecycleLibs.lifecycleViewModel)
    implementation(LifecycleLibs.lifecycleRuntime)
    implementation(LifecycleLibs.lifecycleLiveData)

    // Logger
    debugImplementation(LibsDebug.timber)

    // Testing
    testImplementation(LibsTest.mockk)
    testImplementation(LibsTest.kotestRunner)
    testImplementation(LibsTest.kotestAssertions)
    testImplementation(LibsTest.kotestProperty)
}