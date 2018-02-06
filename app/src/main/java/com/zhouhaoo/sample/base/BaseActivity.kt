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

package com.zhouhaoo.sample.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zhouhaoo.common.mvp.IPresenter
import com.zhouhaoo.common.mvp.IView
import javax.inject.Inject

/**
 * Created by zhou on 17/11/14.
 */
abstract class BaseActivity<P : IPresenter> : AppCompatActivity(), IView {

    @Inject
    lateinit var mPresenter: P
    /**
     * 初始化布局
     */
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = layoutId
        setContentView(layoutId)
        initData(savedInstanceState)
    }

    abstract fun initData(savedInstanceState: Bundle?)

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMessage(message: String) {
    }
}