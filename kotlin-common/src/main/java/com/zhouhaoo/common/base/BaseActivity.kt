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

package com.zhouhaoo.common.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.trello.rxlifecycle2.android.ActivityEvent
import com.zhouhaoo.common.base.delegate.IActivity
import com.zhouhaoo.common.extensions.coreComponent
import com.zhouhaoo.common.integration.lifecycle.ActivityLifecycleable
import com.zhouhaoo.common.mvp.IView
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

/**
 * Created by zhou on 17/11/14.
 */
abstract class BaseActivity : AppCompatActivity(), ActivityLifecycleable,
        IActivity, IView {

    private val mLifecycleSubject = BehaviorSubject.create<ActivityEvent>()

    override fun provideLifecycleSubject(): Subject<ActivityEvent> = mLifecycleSubject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = initView(savedInstanceState)
        setContentView(layoutId)
        initData(savedInstanceState)
    }

    override fun proContext(): Context {
        return this.coreComponent().application()
    }

    override fun useFragment(): Boolean = true

    override fun useEventBus(): Boolean = true

    override fun useInject(): Boolean = true
}