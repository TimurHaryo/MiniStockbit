internal object LibsVersion {
    const val kotlinVersion = "1.5.10"
    const val buildGradleVersion = "4.2.1"
    const val coreKtxVersion = "1.6.0"
    const val appCompatVersion = "1.3.1"
    const val materialVersion = "1.4.0"
    const val constraintVersion = "2.1.0"
    const val swipeRefreshVersion = "1.1.0"
    const val navigationVersion = "2.3.5"
    const val okhttp3Version = "4.9.0"
    const val retrofitVersion = "2.9.0"
    const val coroutinesVersion = "1.4.3"
    const val lifecycleKtxVersion = "2.3.1"
    const val koinVersion = "3.1.2"
}

object PluginLibs {
    const val classpathBuildGradle = "com.android.tools.build:gradle:${LibsVersion.buildGradleVersion}"
    const val classpathKotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${LibsVersion.kotlinVersion}"
    const val classpathNavigation = "androidx.navigation:navigation-safe-args-gradle-plugin:${LibsVersion.navigationVersion}"
}

object CoreLibs {
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${LibsVersion.kotlinVersion}"
    const val coreKtx = "androidx.core:core-ktx:${LibsVersion.coreKtxVersion}"
}

object WidgetLibs {
    const val appCompat = "androidx.appcompat:appcompat:${LibsVersion.appCompatVersion}"
    const val material = "com.google.android.material:material:${LibsVersion.materialVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${LibsVersion.constraintVersion}"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${LibsVersion.swipeRefreshVersion}"
}

object NavigationLibs {
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${LibsVersion.navigationVersion}"
    const val navigationUIKtx = "androidx.navigation:navigation-ui-ktx:${LibsVersion.navigationVersion}"
}

object KoinLibs {
    const val koinCore = "io.insert-koin:koin-core:${LibsVersion.koinVersion}"
    const val koinAndroid = "io.insert-koin:koin-android:${LibsVersion.koinVersion}"
}

object NetworkLibs {
    const val okhttp3Logging = "com.squareup.okhttp3:logging-interceptor:${LibsVersion.okhttp3Version}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${LibsVersion.retrofitVersion}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${LibsVersion.retrofitVersion}"
}

object ReactiveLibs {
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${LibsVersion.coroutinesVersion}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${LibsVersion.coroutinesVersion}"
}

object LifecycleLibs {
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${LibsVersion.lifecycleKtxVersion}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibsVersion.lifecycleKtxVersion}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${LibsVersion.lifecycleKtxVersion}"
}