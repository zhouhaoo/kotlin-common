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


private val Any.TAG: String
    get() = javaClass.simpleName

fun Any.v(msg: () -> String) {
    if (Log.isLoggable(TAG, Log.VERBOSE)) v(msg())
}

fun Any.d(msg: () -> String) {
    if (Log.isLoggable(TAG, Log.DEBUG)) d(msg())
}

fun Any.i(msg: () -> String) {
    if (Log.isLoggable(TAG, Log.INFO)) i(msg())
}

fun Any.e(msg: () -> String) {
    if (Log.isLoggable(TAG, Log.ERROR)) e(msg())
}

fun Any.wtf(msg: () -> String) = w(msg())

fun Any.v(msg: String) = v(TAG, msg)

fun Any.d(msg: String) = d(TAG, msg)

fun Any.i(msg: String) = i(TAG, msg)

fun Any.w(msg: String) = w(TAG, msg)

fun Any.e(msg: String) = e(TAG, msg)

fun Any.wtf(msg: String) = wtf(TAG, msg)

inline fun v(tag: String, msg: String) {
    if (BuildConfig.DEBUG) Log.v(tag, msg)
}

inline fun d(tag: String, msg: String) {
    if (BuildConfig.DEBUG) Log.d(tag, msg)
}

inline fun i(tag: String, msg: String) {
    if (BuildConfig.DEBUG) Log.i(tag, msg)
}

inline fun w(tag: String, msg: String) {
    if (BuildConfig.DEBUG) Log.w(tag, msg)
}

inline fun e(tag: String, msg: String) {
    if (BuildConfig.DEBUG) Log.e(tag, msg)
}

inline fun wtf(tag: String, msg: String) {
    if (BuildConfig.DEBUG) Log.wtf(tag, msg)
}
