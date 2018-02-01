/*
 * Copyright (c) 2018  zhouhaoo
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

import android.app.Application
import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by zhou on 18/1/30.
 */
interface RetrofitConfiguration {
    fun configRetrofit(context: Context, builder: Retrofit.Builder)
}

interface OkhttpConfiguration {
    fun configOkhttp(context: Context, builder: OkHttpClient.Builder)
}

interface GsonConfiguration {
    open fun configGson(context: Context, builder: GsonBuilder)
}

inline fun Retrofit.Builder.config(application: Application, retrofitConfiguration:
RetrofitConfiguration?): Retrofit.Builder {
    retrofitConfiguration?.configRetrofit(application, this)
    return this
}
