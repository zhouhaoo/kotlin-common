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

import android.app.Application
import android.content.Context
import com.zhouhaoo.common.base.delegate.AppLifecycle
import com.zhouhaoo.common.base.delegate.AppLifecycleImpl
import com.zhouhaoo.common.injection.component.AppComponent
import kotlin.properties.Delegates

/**
 *
 *新建app 需要在manifest声明此类
 *
 * Created by Zhouhaoo on 17/12/11.
 */
class BaseApplication : Application(), App {

    private var appLifecycle by Delegates.notNull<AppLifecycle>()

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

    override fun getAppComponent(): AppComponent {
        return if (appLifecycle is App) {
            (appLifecycle as App).getAppComponent()
        } else {
            throw IllegalStateException("${AppLifecycleImpl::class.java} need implements${App::class.java}")
        }
    }
}