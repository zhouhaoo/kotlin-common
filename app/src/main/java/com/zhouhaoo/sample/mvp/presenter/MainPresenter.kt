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

package com.zhouhaoo.sample.mvp.presenter

import android.Manifest
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.tbruyelle.rxpermissions2.RxPermissions
import com.zhouhaoo.common.extensions.requestPermission
import com.zhouhaoo.common.injection.ActivityScope
import com.zhouhaoo.common.mvp.BasePresenter
import com.zhouhaoo.sample.mvp.contract.MainContract
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by zhou on 18/2/5.
 */
@ActivityScope
class MainPresenter @Inject constructor(model: MainContract.Model, view: MainContract.View)
    : BasePresenter<MainContract.Model, MainContract.View>(model, view) {
    @Inject
    lateinit var rxPermissions: RxPermissions

    fun requestData() {
//        mModel.getData("Android", 10, 1)
//                .execute(mView) {
//                    mView.gankData(it)
//                }
        requestPermission(rxPermissions, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
                onSuccess = {
                    Timber.e("requestPermission onSuccess")
                },
                onFailure = {
                    it.forEach {
                        Timber.e("requestPermission onFailure$it")
                    }
                },
                onFailureWithNeverAsk = {
                    it.forEach {
                        Timber.e("requestPermission onFailureWithNeverAsk$it")
                    }
                })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Timber.d("Lifecycle.Event.ON_PAUSE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Timber.d("Lifecycle.Event.ON_CREATE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart1() {
        Timber.d("Lifecycle.Event.ON_START")
    }
}