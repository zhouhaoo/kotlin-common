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

package com.zhouhaoo.common.util

import android.app.Application
import android.os.Environment
import java.io.File

/**
 * Created by zhou on 2018/3/27.
 */
class DataHelper {
    companion object {
        fun getCacheFile(context: Application): File {
            return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                var file: File
                file = context.externalCacheDir//获取系统管理的sd卡缓存文件
                if (file == null) {//如果获取的文件为空,就使用自己定义的缓存文件夹做缓存路径
                    file = File(getCacheFilePath(context))
                    makeDirs(file)
                }
                return file
            } else {
                context.cacheDir
            }
        }

          fun makeDirs(file: File): File {
            return if (!file.exists()) {
                file.mkdirs()
                file
            } else file
        }

        private fun getCacheFilePath(context: Application): String {
            return "/mnt/sdcard/${context.packageName}"
        }
    }

}