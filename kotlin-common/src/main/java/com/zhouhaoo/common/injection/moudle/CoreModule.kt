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

package com.zhouhaoo.common.injection.moudle

import android.app.Application
import com.zhouhaoo.common.interfaces.IRepositoryManager
import com.zhouhaoo.common.net.RepositoryManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by zhou on 17/12/14.
 */
@Module
class CoreModule(private val application: Application) {

    @Singleton
    @Provides
    internal fun provideApplication(): Application = application

    @Singleton
    @Provides
    internal fun provideRepositoryManager(repositoryManager: RepositoryManager): IRepositoryManager {
        return repositoryManager
    }
}
