import com.android.build.api.dsl.LibraryExtension

plugins {
	id("com.android.library")
	kotlin("android")
}

configure<LibraryExtension> {
	compileSdk = 31

	defaultConfig {
		minSdk = 24
		targetSdk = 31

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
}
