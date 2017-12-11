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

/**
 * <pre>
 *新建app 需要在manifest声明此类
 * </pre>
 * Created by Zhouhaoo on 17/12/11.
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}