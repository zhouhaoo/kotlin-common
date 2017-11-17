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

package com.zhouhaoo.common.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Created by zhou on 17/11/16.
 */
object RetrofitManager {
    /**
     * okHttp配置
     */
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
                .writeTimeout(30_000, TimeUnit.MILLISECONDS)
                .readTimeout(10_000, TimeUnit.MILLISECONDS)
                .connectTimeout(15_000, TimeUnit.MILLISECONDS)
                .build()
    }

    /**
     * 获取retrofit对象
     */
    fun getRetrofit(baseUrl: String) =
            Retrofit.Builder().client(okHttpClient).baseUrl(baseUrl).build()!!
}