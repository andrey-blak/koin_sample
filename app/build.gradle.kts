buildscript {
	repositories {
		google()
		jcenter()
	}
	dependencies {
		classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.1.0-alpha05")
	}
}

plugins {
	id("com.android.application")
	id("kotlin-android")
	id("kotlin-android-extensions")
}
apply (plugin = "androidx.navigation.safeargs.kotlin")

repositories {
	google()
	jcenter()
	maven(url = "https://jitpack.io")
}

android {
	compileSdkVersion(28)
	defaultConfig {
		applicationId = "com.example.pdp"
		minSdkVersion(16)
		targetSdkVersion(28)
		versionCode = 1
		versionName = "1.0"
		multiDexEnabled = true

		testInstrumentationRunner = "com.example.pdp.DexOpenerRunner"
	}
	buildTypes {
		getByName("debug") {
			isMinifyEnabled = false
			proguardFiles( getDefaultProguardFile ("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
		getByName("release") {
			isMinifyEnabled = false
			proguardFiles (getDefaultProguardFile ("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	dataBinding.isEnabled = true
}

val koinVersion = "2.0.1"
val pagingVersion = "2.1.0"
val navigationVersion = "2.1.0-alpha05"

dependencies {
	implementation( fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${KOTLIN_VERSION}")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.0-M1")

	// Android
	implementation("androidx.appcompat:appcompat:1.1.0-beta01")
	implementation("androidx.constraintlayout:constraintlayout:1.1.3")
	implementation("androidx.core:core-ktx:1.0.2")
	implementation("androidx.fragment:fragment-ktx:1.1.0-beta01")
	implementation("androidx.collection:collection-ktx:1.1.0")
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.0.0")
	implementation("androidx.lifecycle:lifecycle-extensions:2.0.0")
	implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:1.0.0-alpha01")

	// Android Jetpack Navigation
	implementation("androidx.navigation:navigation-runtime-ktx:$navigationVersion")
	implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
	implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")

	// Android Jetpack Paging
	implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")

	// Koin
	implementation("org.koin:koin-androidx-ext:$koinVersion")
	implementation("org.koin:koin-android-viewmodel:$koinVersion")

	// multidex
	implementation("androidx.multidex:multidex:2.0.1")

	// Unit test
	testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.0-M1")
	testImplementation("io.mockk:mockk:1.9.2")

	// Android test
	debugImplementation("androidx.fragment:fragment-testing:1.1.0-beta01")
	androidTestImplementation("androidx.test.ext:junit:1.1.0")
	androidTestImplementation("androidx.test:rules:1.1.0")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.1.1")
	androidTestImplementation("org.koin:koin-test:2.0.1")
	androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.0-M1")
	androidTestImplementation("android.arch.core:core-testing:1.1.1")

	// Android test mock
	androidTestImplementation("com.github.tmurakami:dexopener:2.0.2")
	androidTestImplementation("io.mockk:mockk-android:1.9.2")
}
