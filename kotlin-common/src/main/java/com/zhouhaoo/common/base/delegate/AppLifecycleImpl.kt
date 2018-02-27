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

package com.zhouhaoo.common.base.delegate

import android.app.Application
import android.content.Context
import com.zhouhaoo.common.base.App
import com.zhouhaoo.common.injection.component.CoreComponent
import com.zhouhaoo.common.injection.component.DaggerCoreComponent
import com.zhouhaoo.common.injection.moudle.ConfigModule
import com.zhouhaoo.common.injection.moudle.CoreModule
import com.zhouhaoo.common.injection.moudle.NetworkModule
import com.zhouhaoo.common.integration.ActLifecycleImpl
import com.zhouhaoo.common.integration.ActRxLifecycleImpl
import com.zhouhaoo.common.interfaces.AppConfig
import com.zhouhaoo.common.net.RequestInterceptor
import com.zhouhaoo.common.util.ManifestParser
import javax.inject.Inject

/**
 * 代理application的生命周期
 *
 * Created by zhou on 17/12/14.
 */
class AppLifecycleImpl(base: Context) : AppLifecycle, App {

    private lateinit var mAppComponent: CoreComponent
    private var configs: List<AppConfig> = ManifestParser(base).parse()
    private var mAppLifecycles = ArrayList<AppLifecycle>()
    private var mActivityLifecycles = ArrayList<Application.ActivityLifecycleCallbacks>()
    @Inject
    lateinit var actLifecycle: ActLifecycleImpl
    @Inject
    lateinit var actRxLifecycle: ActRxLifecycleImpl
    @Inject
    lateinit var interceptor: RequestInterceptor

    init {
        configs.forEach {
            it.injectAppLifecycle(base, mAppLifecycles)
            it.injectActivityLifecycle(base, mActivityLifecycles)
        }
    }

    override fun attachBaseContext(context: Context) {
        mAppLifecycles.forEach { it.attachBaseContext(context) }
    }

    override fun onCreate(application: Application) {
        mAppComponent = DaggerCoreComponent.builder()
                .coreModule(CoreModule(application))
                .networkModule(NetworkModule())
                .configModule(getConfigModule(application, configs))
                .build()
        mAppComponent.inject(this)

        application.registerActivityLifecycleCallbacks(actLifecycle)
        application.registerActivityLifecycleCallbacks(actRxLifecycle)

    }

    override fun getCoreComponent(): CoreComponent = mAppComponent

    private fun getConfigModule(application: Application, configs: List<AppConfig>): ConfigModule {
        var configModule = ConfigModule()
        configs.forEach { it.applyOptions(application, configModule) }
        return configModule
    }

    override fun onTerminate(application: Application) {

    }
}