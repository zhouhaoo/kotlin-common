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
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by zhou on 18/2/27.
 */
interface IFragment {

    /**
     * 初始化布局
     */
    fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int

    /**
     * 初始化数据
     */
    fun initData(savedInstanceState: Bundle?)

    /**
     * 是否使用eventbus
     */
    fun useEventBus(): Boolean

    /**
     * 是否使用dagger.android的注入
     * @see com.zhouhaoo.common.integration.ActLifecycleImpl
     */
    fun useInject(): Boolean
}