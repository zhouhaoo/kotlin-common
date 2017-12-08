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

package com.zhouhaoo.sample.utils

import android.util.Log
import com.zhouhaoo.sample.BuildConfig

/**
 * Created by zhou on 17/11/14.
 */
private val Any.tag: String
    get() = javaClass.simpleName

fun Any.v(msg: () -> String) {
    if (Log.isLoggable(tag, Log.VERBOSE)) v(msg())
}

fun Any.d(msg: () -> String) {
    if (Log.isLoggable(tag, Log.DEBUG)) d(msg())
}

fun Any.i(msg: () -> String) {
    if (Log.isLoggable(tag, Log.INFO)) i(msg())
}

fun Any.e(msg: () -> String) {
    if (Log.isLoggable(tag, Log.ERROR)) e(msg())
}

fun Any.wtf(msg: () -> String) = w(msg())

fun Any.v(msg: String) = v(tag, msg)

fun Any.d(msg: String) = d(tag, msg)

fun Any.i(msg: String) = i(tag, msg)

fun Any.w(msg: String) = w(tag, msg)

fun Any.e(msg: String) = e(tag, msg)

fun Any.wtf(msg: String) = wtf(tag, msg)

fun v(tag: String, msg: String) {
    if (BuildConfig.DEBUG) Log.v(tag, msg)
}

fun d(tag: String, msg: String) {
    if (BuildConfig.DEBUG) Log.d(tag, msg)
}

fun i(tag: String, msg: String) {
    if (BuildConfig.DEBUG) Log.i(tag, msg)
}

fun w(tag: String, msg: String) {
    if (BuildConfig.DEBUG) Log.w(tag, msg)
}

fun e(tag: String, msg: String) {
    if (BuildConfig.DEBUG) Log.e(tag, msg)
}

fun wtf(tag: String, msg: String) {
    if (BuildConfig.DEBUG) Log.wtf(tag, msg)
}
