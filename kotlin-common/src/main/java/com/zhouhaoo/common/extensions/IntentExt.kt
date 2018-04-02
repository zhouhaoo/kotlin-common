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

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment

/**
 * Created by zhou on 2018/3/30.
 */
inline fun <reified T : Activity> intentFor(context: Context): Intent = Intent(context, T::class.java)

inline fun Intent.start(context: Context) = context.startActivity(this)

inline fun Intent.startForResult(activity: Activity, requestCode: Int) = activity.startActivityForResult(this, requestCode)

inline fun Intent.startForResult(fragment: Fragment, requestCode: Int) = fragment.startActivityForResult(this, requestCode)
