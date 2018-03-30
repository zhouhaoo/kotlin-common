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

package com.zhouhaoo.common.extensions

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.Patterns

/**
 * Created by zhou on 2018/3/30.
 */

/**
 *## Activity的普通跳转
 *
 * @param finishSelf true 为跳转后关闭自己，默认不关闭
 */
inline fun <reified T : Activity> Activity.start(finishSelf: Boolean = false) {
    startActivity(intentFor<T>(this))
    if (finishSelf) finish()
}

/**
 *## Activity的带结果返回跳转
 *
 */
inline fun <reified T : Activity> Activity.startActivityForResult(
        requestCode: Int,
        options: Bundle? = null,
        action: String? = null) {
    startActivityForResult(intentFor<T>(this).setAction(action), requestCode, options)
}

inline fun webIntent(url: String): Intent =
        if (Patterns.WEB_URL.matcher(url).matches()) {
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
        } else {
            throw IllegalArgumentException("Passed url: $url does not match URL pattern.")
        }

inline fun Activity.lockCurrentScreenOrientation() {
    requestedOrientation = when (resources.configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        else -> ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
    }
}

inline fun Activity.unlockScreenOrientation() {
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
}