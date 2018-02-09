![](screenshot/logo.png)

[ ![Download](https://api.bintray.com/packages/zhouhaoo/android/kotlin-common/images/download.svg) ](https://bintray.com/zhouhaoo/android/kotlin-common/_latestVersion)
[![Build Status](https://travis-ci.org/zhouhaoo/kotlin-common.svg?branch=master)](https://travis-ci.org/zhouhaoo/kotlin-common)
[![API 19+](https://img.shields.io/badge/API-19%2B-yellowgreen.svg)](https://github.com/zhouhaoo/kotlin-common)
[![Crates.io](https://img.shields.io/crates/l/rustc-serialize.svg)](https://github.com/zhouhaoo/kotlin-common#license)
[![GitHub stars](https://img.shields.io/github/stars/badges/shields.svg?style=social&label=Stars)](https://github.com/zhouhaoo/kotlin-common)

## 说明
最近学习了kotlin，第一是熟悉一下kotlin的语法和编程方式，第二想做一个common包，已备后续使用。此外，正在准备开发练习的[*`Gank app`*](https://github.com/zhouhaoo/Gank)，使用此common依赖。
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

## 相关功能
1. 继承
2. 

>
详情见sample代码

## 感谢开源

1. [`MVPArms`](https://github.com/JessYanCoding/MVPArms)
2. [`Retrofit`](http://square.github.io/retrofit/)
3. [`Okhttp`](http://square.github.io/okhttp/)
4. [`Glide`](https://github.com/bumptech/glide)
5. [`Gson`](https://github.com/google/gson)
6. [`Timber`](https://github.com/JakeWharton/timber)
7. [`dagger2`](https://github.com/google/dagger)
8. [`rxjava2`](https://github.com/ReactiveX/RxJava)
9. [`rxandroid`](https://github.com/ReactiveX/RxAndroid)
10. [`rxkotlin`](https://github.com/ReactiveX/RxKotlin)
11. [`rxlifecycle`](https://github.com/trello/RxLifecycle) 
12. [`rxpermissions`](https://github.com/tbruyelle/RxPermissions) 
13. [`LoggingInterceptor`](https://github.com/ihsanbal/LoggingInterceptor)

...
## TODO
- [x] Common包发布到jcenter()仓库
- [x] 完善文档
- [ ] android studio模板生成文件

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