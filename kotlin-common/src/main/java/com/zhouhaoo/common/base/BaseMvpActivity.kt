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

import com.zhouhaoo.common.mvp.IPresenter
import javax.inject.Inject

/**
 * Created by zhou on 17/11/14.
 */
abstract class BaseMvpActivity<P : IPresenter> : BaseActivity() {
    @Inject
    lateinit var mPresenter: P

    override fun onDestroy() {
        super.onDestroy()
        if (::mPresenter.isInitialized) {
            mPresenter.onDestroy()
        }
    }
}