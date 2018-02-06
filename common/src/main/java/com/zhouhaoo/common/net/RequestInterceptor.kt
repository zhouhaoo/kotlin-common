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

import android.support.annotation.Nullable
import okhttp3.Interceptor
import okhttp3.Response
import java.nio.charset.Charset
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by zhou on 18/2/1.
 * 错误日志记录
 */
@Singleton
class RequestInterceptor @Inject constructor(val level: LogLevel) : Interceptor {
    @Inject
    @Nullable
    lateinit var globalHttpHandler: GlobalHttpHandler

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val logRequest = level == LogLevel.ALL || (level != LogLevel.NONE && level == LogLevel.REQUEST)
        if (logRequest) {
            if (request.body() != null) {

            } else {

            }
        }
        val response = chain.proceed(request)
        var responseBody = response.newBuilder().build().body()
        val source = responseBody!!.source().buffer()
        var charset: Charset? = Charset.forName("UTF-8")
        val contentType = responseBody!!.contentType()
        if (contentType != null) {
            charset = contentType!!.charset(charset)
        }
        globalHttpHandler?.onHttpResultResponse(source.clone().readString(charset), chain, response)
        return response
    }

}