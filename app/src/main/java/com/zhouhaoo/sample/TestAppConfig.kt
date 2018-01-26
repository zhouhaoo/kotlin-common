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
import com.zhouhaoo.common.interfaces.AppConfig
import com.zhouhaoo.common.base.delegate.AppLifecycle
import com.zhouhaoo.common.injection.moudle.ConfigModule

/**
 * Created by zhou on 18/1/25.
 */
class TestAppConfig : AppConfig {
    override fun applyOptions(context: Context, configModule: ConfigModule) {
        configModule.apply {
            //            httpUrl =
        }
    }

    override fun injectAppLifecycle(context: Context, appLifecycles: List<AppLifecycle>) {

    }

    override fun injectActivityLifecycle(context: Context, actLifecycles: List<Application.ActivityLifecycleCallbacks>) {

    }

    override fun injectFragmentLifecycle(context: Context, fragLifecycles: List<FragmentManager.FragmentLifecycleCallbacks>) {

    }

}