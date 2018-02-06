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

import android.text.TextUtils
import com.zhouhaoo.common.interfaces.FormatPrinter
import okhttp3.MediaType
import okhttp3.Request
import timber.log.Timber

/**
 * Created by zhou on 18/2/5.
 */
class DefaultFormatPrinter : FormatPrinter {
    private val TAG = "ArmsHttpLog"
    private val LINE_SEPARATOR = System.getProperty("line.separator")
    private val DOUBLE_SEPARATOR = LINE_SEPARATOR + LINE_SEPARATOR

    private val OMITTED_RESPONSE = arrayOf(LINE_SEPARATOR, "Omitted response body")
    private val OMITTED_REQUEST = arrayOf(LINE_SEPARATOR, "Omitted request body")

    private val N = "\n"
    private val T = "\t"
    private val REQUEST_UP_LINE = "┌────── Request ────────────────────────────────────────────────────────────────────────"
    private val END_LINE = "└───────────────────────────────────────────────────────────────────────────────────────"
    private val RESPONSE_UP_LINE = "┌────── Response ───────────────────────────────────────────────────────────────────────"
    private val BODY_TAG = "Body:"
    private val URL_TAG = "URL: "
    private val METHOD_TAG = "Method: @"
    private val HEADERS_TAG = "Headers:"
    private val STATUS_CODE_TAG = "Status Code: "
    private val RECEIVED_TAG = "Received in: "
    private val CORNER_UP = "┌ "
    private val CORNER_BOTTOM = "└ "
    private val CENTER_LINE = "├ "
    private val DEFAULT_LINE = "│ "


    override fun printJsonRequest(request: Request, bodyString: String) {
        val requestBody = "$LINE_SEPARATOR$BODY_TAG$LINE_SEPARATOR$bodyString"
        var tag = getTag(true)
        Timber.tag(tag).i(REQUEST_UP_LINE)
        logLines(tag, arrayOf(URL_TAG + request.url()), false)
        logLines(tag, getRequest(request), true)
        logLines(tag, requestBody.split(LINE_SEPARATOR.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray(), true)
        Timber.tag(tag).i(END_LINE)
    }

    override fun printFileRequest(request: Request) {
    }

    override fun printJsonResponse(chainMs: Long, isSuccessful: Boolean, code: Int, headers: String, contentType: MediaType, bodyString: String, segments: List<String>, message: String, responseUrl: String) {
    }

    override fun printFileResponse(chainMs: Long, isSuccessful: Boolean, code: Int, headers: String, segments: List<String>, message: String, responseUrl: String) {
    }

    private fun isEmpty(line: String): Boolean {
        return TextUtils.isEmpty(line) || N == line || T == line || TextUtils.isEmpty(line.trim())
    }

    private fun getTag(isRequest: Boolean): String {
        return if (isRequest) {
            "$TAG-Request"
        } else {
            "$TAG-Response"
        }
    }

    private fun logLines(tag: String, lines: Array<String>, withLineSize: Boolean) {
        for (line in lines) {
            val lineLength = line.length
            val maxLongSize = if (withLineSize) 110 else lineLength
            for (i in 0..lineLength / maxLongSize) {
                val start = i * maxLongSize
                var end = (i + 1) * maxLongSize
                end = if (end > line.length) line.length else end
                Timber.tag(tag).i(DEFAULT_LINE + line.substring(start, end))
            }
        }
    }

    private fun getRequest(request: Request): Array<String> {
        val log: String
        val header = request.headers().toString()
        log = METHOD_TAG + request.method() + DOUBLE_SEPARATOR +
                if (isEmpty(header)) "" else HEADERS_TAG + LINE_SEPARATOR + dotHeaders(header)
        return log.split(LINE_SEPARATOR).dropLastWhile { it.isEmpty() }.toTypedArray()
    }

    private fun dotHeaders(header: String): String {
        val headers = header.split(LINE_SEPARATOR.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val builder = StringBuilder()
        var tag = "─ "
        if (headers.size > 1) {
            for (i in headers.indices) {
                if (i == 0) {
                    tag = CORNER_UP
                } else if (i == headers.size - 1) {
                    tag = CORNER_BOTTOM
                } else {
                    tag = CENTER_LINE
                }
                builder.append(tag).append(headers[i]).append("\n")
            }
        } else {
            for (item in headers) {
                builder.append(tag).append(item).append("\n")
            }
        }
        return builder.toString()
    }


}