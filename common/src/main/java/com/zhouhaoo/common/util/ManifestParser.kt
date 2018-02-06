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

import android.content.Context
import android.content.pm.PackageManager
import com.zhouhaoo.common.interfaces.AppConfig

/**
 * Created by zhou on 18/1/25.
 */
class ManifestParser constructor(private var context: Context) {
    private val commonConfig = "CommonConfig"

    fun parse(): List<AppConfig> {
        var modules = ArrayList<AppConfig>()
        try {
            var appInfo = context.packageManager
                    .getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
            var metaData = appInfo.metaData
            if (metaData != null) {
                metaData.keySet().forEach {
                    if (commonConfig == metaData.get(it)) {
                        modules.add(parseConfig(it))
                    }
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            throw RuntimeException("Unable to find metadata to parse CommonConfig", e)
        }
        return modules
    }


    private fun parseConfig(className: String): AppConfig {
        var clazz: Class<*>?
        try {
            clazz = Class.forName(className)
        } catch (e: ClassNotFoundException) {
            throw IllegalArgumentException("Unable to find AppConfig implementation", e)
        }
        var config: Any
        try {
            config = clazz.newInstance()
        } catch (e: InstantiationException) {
            throw  RuntimeException("Unable to instantiate AppConfig implementation for $clazz", e);
        } catch (e: IllegalAccessException) {
            throw  RuntimeException("Unable to instantiate AppConfig implementation for $clazz", e);
        }
        if (config !is AppConfig)
            throw RuntimeException("Expected instanceof AppConfig, but found: $config")
        else
            return config
    }
}