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

package com.zhouhaoo.sample

import android.app.Application
import android.content.Context
import android.support.v4.app.FragmentManager
import com.zhouhaoo.common.base.delegate.AppLifecycle
import com.zhouhaoo.common.injection.moudle.ConfigModule
import com.zhouhaoo.common.interfaces.AppConfig
import com.zhouhaoo.common.net.GlobalHttpHandler
import com.zhouhaoo.common.net.Level
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by zhou on 18/1/25.
 */
class AppConfigImpl : AppConfig {
    override fun applyOptions(context: Context, module: ConfigModule) {
        module.apply {
            baseUrl = "https://api.github.com/"
            gsonBuilder = { }
            retrofitBuilder = { }
            okhttpBuilder = { }
            addInterceptor(HttpLoggingInterceptor())
            globalHttpHandler = GlobalHttpHandlerImpl()
            logLevel = Level.RESPONSE
        }
    }

    override fun injectAppLifecycle(context: Context, appLifecycles: ArrayList<AppLifecycle>) {

    }

    override fun injectActivityLifecycle(context: Context, actLifecycles: ArrayList<Application.ActivityLifecycleCallbacks>) {

    }

    override fun injectFragmentLifecycle(context: Context, fragLifecycles: ArrayList<FragmentManager.FragmentLifecycleCallbacks>) {

    }
}

class GlobalHttpHandlerImpl : GlobalHttpHandler {
    override fun onHttpResultResponse(httpResult: String, chain: Interceptor.Chain, response: Response): Response {
        return response
    }

    override fun onHttpRequestBefore(chain: Interceptor.Chain, request: Request): Request {
        return request
    }
}
