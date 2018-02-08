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

package com.zhouhaoo.common.interfaces

import android.app.Application
import android.content.Context
import android.support.v4.app.FragmentManager
import com.zhouhaoo.common.base.delegate.AppLifecycle
import com.zhouhaoo.common.injection.moudle.ConfigModule

/**
 * Created by zhou on 18/1/25.
 */
interface AppConfig {
    /**
     * 全局配置信息
     */
    fun applyOptions(context: Context, module: ConfigModule)

    /**
     * application生命周期
     */
    fun injectAppLifecycle(context: Context, appLifecycles: ArrayList<AppLifecycle>)

    /**
     * activity生命周期
     */
    fun injectActivityLifecycle(context: Context, actLifecycles: ArrayList<Application.ActivityLifecycleCallbacks>)

    /**
     * fragment生命周期
     */
    fun injectFragmentLifecycle(context: Context, fragLifecycles: ArrayList<FragmentManager.FragmentLifecycleCallbacks>)
}
