buildscript {
	repositories {
		google()
		jcenter()
	}
	dependencies {
		classpath("com.android.tools.build:gradle:3.3.2")
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION")
	}
}

tasks.register("clean",Delete::class){
	delete(rootProject.buildDir)
}
