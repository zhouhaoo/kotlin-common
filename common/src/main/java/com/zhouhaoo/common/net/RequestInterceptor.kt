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
import com.zhouhaoo.common.util.ZipHelper
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import timber.log.Timber
import java.nio.charset.Charset
import java.util.zip.DataFormatException
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by zhou on 18/2/1.
 * 错误日志记录
 */
@Singleton
class RequestInterceptor @Inject constructor() : Interceptor {
    @Inject
    @Nullable
    lateinit var globalHttpHandler: GlobalHttpHandler

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var text = isText(null)
        var response: Response
        try {
            response = chain.proceed(request)
        } catch (e: Exception) {
            Timber.w("Http Error: ${e.toString()}")
            throw e
        }
        var bodyString: String? = null
        var responseBody = response.newBuilder().build().body()
        if (response != null && isParseable(responseBody?.contentType())) {
            bodyString = getbodyString(response)
        }
        globalHttpHandler?.onHttpResultResponse(bodyString, chain, response)
        return response
    }

    private fun getbodyString(response: Response): String {
        try {
            var responseBody = response.newBuilder().build().body()
            var source = responseBody
                    ?.let {
                        it.source()
                    }
            source?.request(Long.MAX_VALUE)
            var buffer = source!!.buffer()
            var encoding = response.headers().get("Content-Encoding")
            var clone = buffer.clone()!!
            var bodyString = parseContent(responseBody, encoding, clone)
            return bodyString ?: throw DataFormatException()
        } catch (e: Exception) {
            e.printStackTrace()
            return "{\"error\": \"" + e.message + "\"}"
        }
    }

    private fun parseContent(responseBody: ResponseBody?, encoding: String?, clone: Buffer): String? {
        var charset: Charset = Charsets.UTF_8
        val contentType = responseBody?.contentType()
        if (contentType != null) {
            charset = contentType.charset(charset)!!
        }
        return if (encoding != null && encoding.equals("gzip", true)) {
            ZipHelper.decompressForGzip(clone.readByteArray(), charset)
        } else if (encoding != null && encoding.equals("zlib", true)) {
            ZipHelper.decompressToStringForZlib(clone.readByteArray(), charset)
        } else {
            clone.readString(charset)
        }
    }

    private fun isParseable(mediaType: MediaType?): Boolean {
        return (isText(mediaType) || isPlain(mediaType)
                || isJson(mediaType) || isForm(mediaType)
                || isHtml(mediaType) || isXml(mediaType))
    }

    private fun isText(mediaType: MediaType?): Boolean {
        return if (mediaType?.type() == null) false else mediaType.type() == "text"
    }

    private fun isPlain(mediaType: MediaType?): Boolean {
        return if (mediaType == null || mediaType.subtype() == null) false else mediaType.subtype().toLowerCase().contains("plain")
    }

    private fun isJson(mediaType: MediaType?): Boolean {
        return if (mediaType == null || mediaType.subtype() == null) false else mediaType.subtype().toLowerCase().contains("json")
    }

    private fun isXml(mediaType: MediaType?): Boolean {
        return if (mediaType == null || mediaType.subtype() == null) false else mediaType.subtype().toLowerCase().contains("xml")
    }

    private fun isHtml(mediaType: MediaType?): Boolean {
        return if (mediaType == null || mediaType.subtype() == null) false else mediaType.subtype().toLowerCase().contains("html")
    }

    private fun isForm(mediaType: MediaType?): Boolean {
        return if (mediaType == null || mediaType.subtype() == null) false else mediaType.subtype().toLowerCase().contains("x-www-form-urlencoded")
    }

}