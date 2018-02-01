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
import com.zhouhaoo.common.net.RetrofitConfiguration
import com.zhouhaoo.common.net.config
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

/**
 * Created by zhou on 17/12/15.
 */
@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideRetrofitBuilder() = Retrofit.Builder()

    @Singleton
    @Provides
    internal fun provideRetrofit(application: Application, builder: Retrofit.Builder,
                                 retrofitConfiguration: RetrofitConfiguration?, baseUrl: HttpUrl,
                                 okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return builder.baseUrl(baseUrl)
                .client(okHttpClient)
                .config(application, retrofitConfiguration)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Singleton
    @Provides
    internal fun provideClientBuilder() = OkHttpClient.Builder()

    @Singleton
    @Provides
    internal fun provideOkHttpClient(builder: OkHttpClient.Builder,
                                     httpLoggingInterceptor: HttpLoggingInterceptor)
            : OkHttpClient {
        builder.addInterceptor(httpLoggingInterceptor)
        return builder.build()
    }

    @Singleton
    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message ->
            Timber.d(message)
        }.setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}