![](screenshot/logo.png)

[ ![Download](https://api.bintray.com/packages/zhouhaoo/android/kotlin-common/images/download.svg) ](https://bintray.com/zhouhaoo/android/kotlin-common/_latestVersion)
[![Build Status](https://travis-ci.org/zhouhaoo/kotlin-common.svg?branch=master)](https://travis-ci.org/zhouhaoo/kotlin-common)
[![API 19+](https://img.shields.io/badge/API-19%2B-yellowgreen.svg)](https://github.com/zhouhaoo/kotlin-common)
[![Crates.io](https://img.shields.io/crates/l/rustc-serialize.svg)](https://github.com/zhouhaoo/kotlin-common#license)
[![GitHub stars](https://img.shields.io/github/stars/badges/shields.svg?style=social&label=Stars)](https://github.com/zhouhaoo/kotlin-common)

## 说明

## 用法
1. 在项目的 build.gradle 中添加：

```
apply from: "dependencies.gradle"//将dependencies.gradle复制到根目录，并引入gradle文件
buildscript {
    ext.kotlin_version = '1.2.21'
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.1'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven { url "https://jitpack.io" }
    }

task clean(type: Delete) {
    delete rootProject.buildDir
}  
```
2. app下build.gradle添加依赖

```
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'
apply plugin: 'kotlin-kapt'//注解编译器
...
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
    implementation "com.zhouhaoo:kotlin-common:1.0.0"
    kapt annotationProcessorLibs //依赖注解
}
```

3. 用范例见：[gank.io干货集中营Android客户端 by Kotlin](https://github.com/zhouhaoo/Gank)


## 相关功能

## 感谢开源

1. [retrofit]()
2. []()
3. []()
4. []()
5. []()
6. []()
7. []()
8. []()
9. []()
10. []()


## TODO
- [x] Common包发布到jcenter()仓库
- [x] 完善文档
- [ ] android studio模板生成文件

## 声明

## License
```
   Copyright (c) 2018  zhouhaoo(https://github.com/zhouhaoo)
 
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```