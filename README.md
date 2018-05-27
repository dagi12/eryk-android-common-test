# eryk-android-common-test

## Description
This is my own library with common android stuff that I often use in new projects for testing with Robolectric and Espresso

## Initialization and requirements
- dependencies needed for main project
```groovy
dependencies {
    testImplementation 'com.google.dagger:dagger:2.14.1'
    kaptTest 'com.google.dagger:dagger-compiler:2.14.1'
    
    testImplementation 'junit:junit:4.12'
    testImplementation project(":eryk-android-common-test")
}
```
- need for robolectric
```groovy
android {
    testOptions {
        unitTests {
            includeAndroidResources = true
        }
    }
}
```
- copy paste TestComponent, TestModule, TestInjector, TestScope, TestModule