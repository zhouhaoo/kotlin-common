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

package com.zhouhaoo.sample.utils

import com.zhouhaoo.common.mvp.IView
import com.zhouhaoo.sample.BaseData
import com.zhouhaoo.sample.app.NetErrorListenerImpl
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers

/**
 * rxkotlin 默认参数
 */
private val onErrorStub: (Throwable) -> Unit = { RxJavaPlugins.onError(OnErrorNotImplementedException(it)) }

/**
 * ## 扩展RxJava的subscribe，有两种模式
 *
 * * 切换请求线程，处理错误信息，进度加载
 * * 切换请求线程，处理错误信息
 *
 *  @param iView view层
 *  @param onNext 处理过后的数据
 *  @param onError 错误处理
 * @param loadingStatus 执行状态 true为带进度加载，false 只处理错误toast ，默认为true
 *
 * ## Created by zhou on 2018/3/28.
 */
fun <T : Any> Observable<BaseData<T>>.execute(
        iView: IView,
        onComplete: () -> Unit = {},
        onError: (Throwable) -> Unit = NetErrorListenerImpl(iView.proContext())::errorMessage,
        loadingStatus: Boolean = true,
        onNext: (T) -> Unit
): Disposable = subscribeOn(Schedulers.io())
        .doOnSubscribe { iView.showLoading() }//显示加载
        .map {
            //转换数据
            it.data
        }
        .observeOn(AndroidSchedulers.mainThread())
        .doAfterTerminate(iView::showLoading)//隐藏加载
        .subscribe(onNext, if (loadingStatus) onError else onErrorStub, onComplete)

/**
 * 创建成功的数据 Observable
 */
private fun <T> createData(data: T): Observable<T> {
    return Observable.create {
        try {
            it.onNext(data)
            it.onComplete()
        } catch (e: Exception) {
            it.onError(e)
        }
    }
}

