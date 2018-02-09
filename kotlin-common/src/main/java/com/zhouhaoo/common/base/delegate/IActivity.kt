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

package com.zhouhaoo.common.base.delegate

import android.os.Bundle
import com.zhouhaoo.common.injection.component.AppComponent

/**
 * Created by zhou on 18/2/6.
 */
interface IActivity {

    fun setupActivityComponent(appComponent: AppComponent)

    fun initView(savedInstanceState: Bundle?): Int

    fun initData(savedInstanceState: Bundle?)

    fun useEventBus(): Boolean
}