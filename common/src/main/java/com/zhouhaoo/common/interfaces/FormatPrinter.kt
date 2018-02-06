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

package com.zhouhaoo.common.interfaces

import okhttp3.MediaType
import okhttp3.Request

/**
 * Created by zhou on 18/2/5.
 */
interface FormatPrinter {

    fun printJsonRequest(request: Request, bodyString: String)

    fun printFileRequest(request: Request)

    fun printJsonResponse(chainMs: Long, isSuccessful: Boolean, code: Int, headers: String,
                          contentType: MediaType, bodyString: String, segments: List<String>,
                          message: String, responseUrl: String)

    fun printFileResponse(chainMs: Long, isSuccessful: Boolean, code: Int, headers: String,
                          segments: List<String>, message: String, responseUrl: String)
}