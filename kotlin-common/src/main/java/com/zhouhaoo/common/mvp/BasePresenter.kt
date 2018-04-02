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

package com.zhouhaoo.common.mvp

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent

/**
 * Created by zhou on 18/2/5.
 */
open class BasePresenter<M : IModel, V : IView>(var mModel: M, var mView: V) :
        IPresenter, LifecycleObserver {

    init {
        onStart()
    }

    override fun onStart() {
        if (mView is LifecycleOwner) {
            (mView as LifecycleOwner).lifecycle.addObserver(this)
            if (mModel is LifecycleObserver) {
                (mView as LifecycleOwner).lifecycle.addObserver((mModel as LifecycleObserver))
            }
        }
    }

    override fun onDestroy() {
        mModel.onDestroy()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    internal fun onDestroy(owner: LifecycleOwner) {
        onDestroy()
        owner.lifecycle.removeObserver(this)
    }
}