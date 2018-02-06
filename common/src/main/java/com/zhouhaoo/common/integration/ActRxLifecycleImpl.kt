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

package com.zhouhaoo.common.integration

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.trello.rxlifecycle2.android.ActivityEvent
import com.zhouhaoo.common.integration.lifecycle.ActivityLifecycleable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by zhou on 18/1/30.
 */
@Singleton
class ActRxLifecycleImpl @Inject constructor() : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        if (activity is ActivityLifecycleable) {
            injectSubject(activity, ActivityEvent.CREATE)
        }
    }

    override fun onActivityStarted(activity: Activity) {
        if (activity is ActivityLifecycleable) {
            injectSubject(activity, ActivityEvent.START)
        }
    }

    override fun onActivityPaused(activity: Activity) {
        if (activity is ActivityLifecycleable) {
            injectSubject(activity, ActivityEvent.PAUSE)
        }
    }

    override fun onActivityResumed(activity: Activity) {
        if (activity is ActivityLifecycleable) {
            injectSubject(activity, ActivityEvent.RESUME)
        }
    }

    override fun onActivityStopped(activity: Activity) {
        if (activity is ActivityLifecycleable) {
            injectSubject(activity, ActivityEvent.STOP)
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (activity is ActivityLifecycleable) {
            injectSubject(activity, ActivityEvent.DESTROY)
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, savedInstanceState: Bundle?) {
    }

    private fun injectSubject(activity: ActivityLifecycleable, event: ActivityEvent) {
        activity.provideLifecycleSubject().onNext(event)
    }

}