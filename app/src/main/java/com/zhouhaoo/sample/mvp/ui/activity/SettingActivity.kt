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

package com.zhouhaoo.sample.mvp.ui.activity

import android.os.Bundle
import com.zhouhaoo.common.base.BaseActivity
import com.zhouhaoo.sample.R
import com.zhouhaoo.sample.mvp.ui.fragment.SettingFragment

/**
 * Created by zhou on 18/2/28.
 */
class SettingActivity : BaseActivity() {

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_setting
    }

    override fun initData(savedInstanceState: Bundle?) {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragContent, SettingFragment())
                .commit()
    }


    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMessage(message: String) {
    }

    override fun useInject(): Boolean = false
}