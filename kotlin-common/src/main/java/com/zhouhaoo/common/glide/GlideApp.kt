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

package com.zhouhaoo.common.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.zhouhaoo.common.util.DataHelper
import com.zhouhaoo.common.extensions.coreComponent
import java.io.InputStream

/**
 * ## Glide基本配置
 *
 * Created by zhou on 2018/3/27.
 */
@GlideModule(glideName = "GlideApp")
class GlideConfiguration : AppGlideModule() {
    /**
     * 图片磁盘最大缓存大小
     */
    private val imageDiskCacheMaxSize = 100 * 1024 * 1024L

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val coreComponent = context.coreComponent()
        builder.apply {
            setDiskCache {
                DiskLruCacheWrapper.create(DataHelper.makeDirs(coreComponent.cacheFile()), imageDiskCacheMaxSize)
            }
            val calculator = MemorySizeCalculator.Builder(context).build()
            val defaultMemoryCacheSize = calculator.memoryCacheSize
            val defaultBitmapPoolSize = calculator.bitmapPoolSize
            val customMemoryCacheSize = (1.2 * defaultMemoryCacheSize).toLong()
            val customBitmapPoolSize = (1.2 * defaultBitmapPoolSize).toLong()
            setMemoryCache(LruResourceCache(customMemoryCacheSize))
            setBitmapPool(LruBitmapPool(customBitmapPoolSize))
        }
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.replace(GlideUrl::class.java, InputStream::class.java,
                OkHttpUrlLoader.Factory(context.coreComponent().okhttpClient()))
    }

    override fun isManifestParsingEnabled() = false
}