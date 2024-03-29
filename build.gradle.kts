buildscript {
    val kotlinVersion = "1.8.20"
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}


plugins {
    id ("com.android.application") version "8.0.1" apply false
    id ("org.jetbrains.kotlin.android") version "1.8.20" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}