/*
 * Copyright (c) 2017  zhouhaoo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.zhouhaoo.common.injection.component

import android.app.Application
import com.google.gson.Gson
import com.zhouhaoo.common.base.delegate.AppLifecycleImpl
import com.zhouhaoo.common.injection.moudle.ConfigModule
import com.zhouhaoo.common.injection.moudle.CoreModule
import com.zhouhaoo.common.injection.moudle.NetworkModule
import com.zhouhaoo.common.interfaces.IRepositoryManager
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import okhttp3.OkHttpClient
import java.io.File
import javax.inject.Singleton

/**
 * Created by zhou on 17/12/14.
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, CoreModule::class,
    NetworkModule::class, ConfigModule::class])
interface CoreComponent {
    /**
     * ## 全局 application
     */
    fun application(): Application
    /**
     * ## okhttp
     */
    fun okhttpClient(): OkHttpClient
    /**
     * ## 数据
     */
    fun repositoryManager(): IRepositoryManager
    /**
     * ## json解析
     */
    fun gson(): Gson
    /**
     * ## 缓存文件
     */
    fun cacheFile(): File

    fun inject(appLifecycle: AppLifecycleImpl)
}