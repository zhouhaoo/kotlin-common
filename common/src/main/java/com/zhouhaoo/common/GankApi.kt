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

package com.zhouhaoo.common

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by zhou on 18/2/6.
 */
interface GankApi {
    @GET("data/{category}/{pageCount}/{page}")
    fun getGank(@Path("category") category: String,
                @Path("pageCount") pageCount: Int,
                @Path("page") page: Int): Observable<BaseData<MutableList<Data>>>
}

open class BaseData<T>(var error: Boolean, @SerializedName("results") var data: T)

data class Data(var desc: String, var type: String, var url: String)