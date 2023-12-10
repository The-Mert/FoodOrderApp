// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.3" apply false
}

buildscript{
    dependencies{
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.44")
    }
}