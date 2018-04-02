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

import android.Manifest
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

/**
 * Created by zhou on 2018/4/2.
 */


inline fun requestPermission(rxPermissions: RxPermissions,
                             vararg permissions: String,
                             crossinline onSuccess: () -> Unit,
                             crossinline onFailure: (permissions: List<String>) -> Unit,
                             crossinline onFailureWithNeverAsk: (permissions: List<String>) -> Unit) {
    if (permissions.isEmpty()) {
        return
    }
    val needPermissions = permissions.filter { !rxPermissions.isGranted(it) }
    if (needPermissions.isEmpty()) {
        Timber.tag("Permissions").e("requestPermission onSuccess")
        onSuccess()
    } else {
        rxPermissions.requestEach(*needPermissions.toTypedArray())
                .buffer(permissions.size)
                .subscribeBy(
                        onNext = {
                            it.forEach {
                                if (!it.granted) {
                                    if (it.shouldShowRequestPermissionRationale) {
                                        onFailure(listOf<String>(it.name))
                                        Timber.tag("Permissions").e("requestPermission onFailure")
                                        return@subscribeBy
                                    } else {
                                        onFailureWithNeverAsk(listOf<String>(it.name))
                                        Timber.tag("Permissions").e("requestPermission onFailureWithNeverAsk")
                                        return@subscribeBy
                                    }
                                }
                            }
                            onSuccess()
                        },
                        onError = {
                            Timber.tag("Permissions").e("requestPermission onError")
                        }
                )
    }
}

/**
 * 读取摄像头权限
 */
inline fun cameraPermission(rxPermissions: RxPermissions,
                            crossinline onSuccess: () -> Unit,
                            crossinline onFailure: (permissions: List<String>) -> Unit,
                            crossinline onFailureWithNeverAsk: (permissions: List<String>) -> Unit) {
    requestPermission(rxPermissions,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
            onSuccess = onSuccess,
            onFailure = onFailure,
            onFailureWithNeverAsk = onFailureWithNeverAsk)
}

/**
 * 外部存储权限
 */
inline fun externalStoragePermission(rxPermissions: RxPermissions,
                                     crossinline onSuccess: () -> Unit,
                                     crossinline onFailure: (permissions: List<String>) -> Unit,
                                     crossinline onFailureWithNeverAsk: (permissions: List<String>) -> Unit) {
    requestPermission(rxPermissions,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            onSuccess = onSuccess,
            onFailure = onFailure,
            onFailureWithNeverAsk = onFailureWithNeverAsk)
}

/**
 * 发送短信权限
 */
inline fun sendSmsPermission(rxPermissions: RxPermissions,
                             crossinline onSuccess: () -> Unit,
                             crossinline onFailure: (permissions: List<String>) -> Unit,
                             crossinline onFailureWithNeverAsk: (permissions: List<String>) -> Unit) {
    requestPermission(rxPermissions,
            Manifest.permission.SEND_SMS,
            onSuccess = onSuccess,
            onFailure = onFailure,
            onFailureWithNeverAsk = onFailureWithNeverAsk)
}

/**
 * 打电话权限
 */
inline fun callPhonePermission(rxPermissions: RxPermissions,
                               crossinline onSuccess: () -> Unit,
                               crossinline onFailure: (permissions: List<String>) -> Unit,
                               crossinline onFailureWithNeverAsk: (permissions: List<String>) -> Unit) {
    requestPermission(rxPermissions,
            Manifest.permission.CALL_PHONE,
            onSuccess = onSuccess,
            onFailure = onFailure,
            onFailureWithNeverAsk = onFailureWithNeverAsk)
}

/**
 * 读取手机状态权限
 */
inline fun readPhoneStatePermission(rxPermissions: RxPermissions,
                                    crossinline onSuccess: () -> Unit,
                                    crossinline onFailure: (permissions: List<String>) -> Unit,
                                    crossinline onFailureWithNeverAsk: (permissions: List<String>) -> Unit) {
    requestPermission(rxPermissions,
            Manifest.permission.READ_PHONE_STATE,
            onSuccess = onSuccess,
            onFailure = onFailure,
            onFailureWithNeverAsk = onFailureWithNeverAsk)
}