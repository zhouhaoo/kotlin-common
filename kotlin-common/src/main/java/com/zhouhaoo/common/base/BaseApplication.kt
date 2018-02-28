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

package com.zhouhaoo.common.base

import android.app.Activity
import android.app.Application
import android.content.Context
import com.zhouhaoo.common.base.delegate.AppLifecycle
import com.zhouhaoo.common.base.delegate.AppLifecycleImpl
import com.zhouhaoo.common.injection.component.CoreComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 *
 * Created by Zhouhaoo on 17/12/11.
 */
open class BaseApplication : Application(), App, HasActivityInjector {
    private var appLifecycle by Delegates.notNull<AppLifecycle>()
    @Inject
    lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return this.mActivityInjector
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        appLifecycle = AppLifecycleImpl(base)
        appLifecycle.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        appLifecycle.onCreate(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        appLifecycle.onTerminate(this)
    }

    override fun getCoreComponent(): CoreComponent {
        return if (appLifecycle is App) {
            (appLifecycle as App).getCoreComponent()
        } else {
            throw IllegalStateException("${AppLifecycleImpl::class.java} need implements${App::class.java}")
        }
    }
}