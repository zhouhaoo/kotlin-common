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

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.zhouhaoo.common.base.BaseMvpActivity
import com.zhouhaoo.common.extensions.*
import com.zhouhaoo.sample.Data
import com.zhouhaoo.sample.GankApi
import com.zhouhaoo.sample.R
import com.zhouhaoo.sample.mvp.contract.MainContract
import com.zhouhaoo.sample.mvp.presenter.MainPresenter
import com.zhouhaoo.sample.utils.toast
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.util.*

class MainActivity : BaseMvpActivity<MainPresenter>(), MainContract.View {

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.setting, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                start<SettingActivity>()//跳转
//                start<SettingActivity>(finishSelf = true)//跳转后关闭自己
            }
        }
        return true
    }

    override fun initData(savedInstanceState: Bundle?) {
        tvHello.setOnClickListener {
            tvContent.text = ""
            mPresenter.requestData()
        }
        var gankApi = coreComponent()
                .repositoryManager()
                .create<GankApi>()

        Timber.d("Lifecycle.Event.ON_CREATE")
        fromApi(Build.VERSION_CODES.KITKAT) {

        }
        toApi(Build.VERSION_CODES.KITKAT) {

        }
    }

    override fun gankData(list: MutableList<Data>) {
        toast("请求成功")
        val index = Random().nextInt(list.size - 1)
        tvContent.text = list[index].toString()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMessage(message: String) {
    }

}
