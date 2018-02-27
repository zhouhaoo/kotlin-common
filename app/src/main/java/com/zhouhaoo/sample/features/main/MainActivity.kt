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

package com.zhouhaoo.sample.features.main

import android.os.Bundle
import com.google.gson.Gson
import com.zhouhaoo.common.base.BaseMvpActivity
import com.zhouhaoo.sample.*
import com.zhouhaoo.sample.utils.toast
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class MainActivity : BaseMvpActivity<MainPresenter>(), MainContract.View {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMessage(message: String) {
    }

    @Inject
    lateinit var mGson: Gson

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }

    override fun initData(savedInstanceState: Bundle?) {
        tvHello.setOnClickListener {
            tvContent.text = ""
            mPresenter.requestData()
        }
        Timber.d("Lifecycle.Event.ON_CREATE")
    }

    override fun gankData(data: BaseData<MutableList<Data>>) {
        toast("请求成功")
        var list = data.data
        val index = Random().nextInt(list.size - 1)
        tvContent.text = list[index].toString()
    }
}
