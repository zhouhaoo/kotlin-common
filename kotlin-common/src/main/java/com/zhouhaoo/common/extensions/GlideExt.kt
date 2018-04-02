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

package com.zhouhaoo.common.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.zhouhaoo.common.glide.GlideApp

/**
 * ## 加载图片url扩展
 *
 * 用Glide实现
 *
 * Created by zhou on 2018/3/29.
 */
inline fun ImageView.load(url: String) {
    GlideApp.with(this).load(url).into(this)
}

inline fun ImageView.load(drawable: Drawable) {
    GlideApp.with(this).load(drawable).into(this)
}

inline fun ImageView.load(resourceID: Int) {
    GlideApp.with(this).load(resourceID).into(this)
}