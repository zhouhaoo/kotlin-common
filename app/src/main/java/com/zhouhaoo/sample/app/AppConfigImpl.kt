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

package com.zhouhaoo.sample.app

import android.app.Application
import android.content.Context
import android.support.v4.app.FragmentManager
import com.ihsanbal.logging.Level
import com.zhouhaoo.common.base.delegate.AppLifecycle
import com.zhouhaoo.common.injection.moudle.ConfigModule
import com.zhouhaoo.common.interfaces.AppConfig
import com.zhouhaoo.sample.BuildConfig
import okhttp3.internal.platform.Platform

/**
 * ## 全局配置
 *
 * * 12345
 * * qwert
 *
 * Created by zhou on 18/1/25.
 */
class AppConfigImpl : AppConfig {
    override fun applyOptions(context: Context, module: ConfigModule) {
        module.apply {
            baseUrl = "http://gank.io/api/"
            gsonBuilder = { }
            retrofitBuilder = { }
            okhttpBuilder = { }
            //            glideBuilder ={ }
            httplogBuilder = {
                loggable(BuildConfig.DEBUG).setLevel(Level.BODY).log(Platform.INFO)
                        .request("Request").response("Response")
            }
            globalHttpHandler = GlobalHttpHandlerImpl(context)
//            addInterceptor()
//            glideBuilder
        }
    }

    override fun injectAppLifecycle(context: Context, appLifecycles: ArrayList<AppLifecycle>) {
        appLifecycles.add(AppLifecycleImpl())
    }

    override fun injectActivityLifecycle(context: Context, actLifecycles: ArrayList<Application.ActivityLifecycleCallbacks>) {
        actLifecycles.add(ActivityLifecycle())
    }

    override fun injectFragmentLifecycle(context: Context, fragLifecycles: ArrayList<FragmentManager.FragmentLifecycleCallbacks>) {

    }
}


