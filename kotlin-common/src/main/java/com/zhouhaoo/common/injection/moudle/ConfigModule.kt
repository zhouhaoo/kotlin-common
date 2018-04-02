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

package com.zhouhaoo.common.injection.moudle

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.LoggingInterceptor
import com.zhouhaoo.common.net.GlobalHttpHandler
import com.zhouhaoo.common.util.DataHelper
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.io.File
import javax.inject.Singleton

/**
 * Created by zhou on 17/12/15.
 */
@Module
class ConfigModule {
    /**
     * retrofit请求BaseUrl
     */
    var baseUrl: String? = null
    /**
     *全局http处理类
     */
    var globalHttpHandler: GlobalHttpHandler? = null
    /**
     * 额外拦截器
     */
    private var mInterceptors: ArrayList<Interceptor> = ArrayList()
    /**
     * retrofit配置
     */
    var retrofitBuilder: Retrofit.Builder.() -> Unit = {}
    /**
     * ohhttp配置
     */
    var okhttpBuilder: OkHttpClient.Builder.() -> Unit = {}
    /**
     * 网络请求日志配置
     */
    var httplogBuilder: LoggingInterceptor.Builder.() -> Unit = {}
    /**
     * json解析配置
     */
    var gsonBuilder: GsonBuilder.() -> Unit = {}

    var mCacheFile: File? = null

    @Singleton
    @Provides
    internal fun provideBaseUrl(): HttpUrl {
        return if (baseUrl != null) {
            HttpUrl.parse(baseUrl)
                    ?: throw IllegalArgumentException("$baseUrl isn't a well-formed HTTP or HTTPS url")
        } else {
            throw NullPointerException("BaseUrl can not be empty")
        }
    }

    @Singleton
    @Provides
    internal fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder().apply(retrofitBuilder)

    @Singleton
    @Provides
    internal fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder().apply(okhttpBuilder)

    @Singleton
    @Provides
    internal fun provideLoggingInterceptorBuilder(): LoggingInterceptor.Builder {
        return LoggingInterceptor.Builder().apply(httplogBuilder)
    }

    @Singleton
    @Provides
    internal fun provideGlobalHttpHandler(): GlobalHttpHandler? {
        return globalHttpHandler
    }

    @Singleton
    @Provides
    internal fun provideGson(): Gson = GsonBuilder().apply(gsonBuilder).create()

    @Singleton
    @Provides
    internal fun provideInterceptors(): ArrayList<Interceptor> {
        return mInterceptors
    }

    /**
     * 提供缓存文件
     */
    @Singleton
    @Provides
    internal fun provideCacheFile(application: Application): File {
        return if (mCacheFile != null) mCacheFile!! else DataHelper.getCacheFile(application)
    }

    /**
     * 添加拦截器
     */
    fun addInterceptor(interceptor: Interceptor) {
        mInterceptors.add(interceptor)
    }
}