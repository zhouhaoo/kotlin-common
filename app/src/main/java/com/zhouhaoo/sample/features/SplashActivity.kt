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

package com.zhouhaoo.sample.features

import android.os.Bundle
import com.zhouhaoo.common.base.BaseActivity
import com.zhouhaoo.common.injection.component.AppComponent
import com.zhouhaoo.sample.*
import com.zhouhaoo.sample.injection.component.DaggerMainComponent
import com.zhouhaoo.sample.injection.module.MainModule
import com.zhouhaoo.sample.utils.toast
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*

class SplashActivity : BaseActivity<MainPresenter>(), MainContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(MainModule(this))
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_splash
    }

    override fun initData(savedInstanceState: Bundle?) {
        tvHello.setOnClickListener {
            tvContent.text = ""
            mPresenter.requestData()
        }
    }

    override fun gankData(data: BaseData<MutableList<Data>>) {
        toast("请求成功")
        var list = data.data
        val index = Random().nextInt(list.size - 1)
        tvContent.text = list[index].toString()
    }
}
