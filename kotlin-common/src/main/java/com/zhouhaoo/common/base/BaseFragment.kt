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

package com.zhouhaoo.common.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhouhaoo.common.base.delegate.IFragment

/**
 * Created by zhou on 18/2/27.
 */
abstract class BaseFragment : Fragment(), IFragment {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layoutId = initView(inflater, container, savedInstanceState)
        return if (layoutId == 0) inflater.inflate(layoutId, container, false)
        else super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun useEventBus(): Boolean = true

    override fun useInject(): Boolean = false
}