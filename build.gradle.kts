// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply{
        val ktorVersion by extra("2.3.4")
        val koinVersion by extra("3.4.0")
    }
}

plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("org.jetbrains.kotlin.multiplatform") version "1.8.10" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10" apply false
}