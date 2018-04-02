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

package com.zhouhaoo.sample.app

import android.content.Context
import android.net.ParseException
import android.widget.Toast
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.zhouhaoo.common.interfaces.NetErrorListener
import org.json.JSONException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by zhou on 18/2/2.
 */
class NetErrorListenerImpl(private var context: Context) : NetErrorListener {

    override fun errorMessage(throwable: Throwable) {
        Toast.makeText(context, throwable.message, Toast.LENGTH_LONG).show()
        throwable.printStackTrace()
        when (throwable) {
            is UnknownHostException -> {

            }
            is SocketTimeoutException -> {

            }
            is HttpException -> {

            }
            is JsonParseException -> {

            }
            is ParseException -> {

            }
            is JSONException -> {

            }
            is JsonIOException -> {

            }
            else -> {

            }
        }
    }
}