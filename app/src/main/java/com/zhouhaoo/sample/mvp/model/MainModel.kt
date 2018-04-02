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

package com.zhouhaoo.sample.mvp.model

import com.zhouhaoo.common.extensions.create
import com.zhouhaoo.common.injection.ActivityScope
import com.zhouhaoo.common.mvp.BaseModel
import com.zhouhaoo.sample.BaseData
import com.zhouhaoo.sample.Data
import com.zhouhaoo.sample.GankApi
import com.zhouhaoo.sample.mvp.contract.MainContract
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by zhou on 18/2/6.
 */
@ActivityScope
class MainModel @Inject constructor() : BaseModel(), MainContract.Model {

    override fun getData(category: String, pageCount: Int, page: Int):
            Observable<BaseData<MutableList<Data>>> {
        return repositoryManager.create<GankApi>().getGank(category, pageCount, page)
    }

}