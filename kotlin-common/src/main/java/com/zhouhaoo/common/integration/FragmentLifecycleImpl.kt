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

package com.zhouhaoo.common.integration

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import com.zhouhaoo.common.base.delegate.IFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by zhou on 18/2/27.
 */
@Singleton
class FragmentLifecycleImpl @Inject constructor() : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
        if (f is IFragment) {
            if (f.useInject()) {
                AndroidSupportInjection.inject(f)
            }
        }
    }

    override fun onFragmentViewCreated(fm: FragmentManager?, f: Fragment?, v: View?,
                                       savedInstanceState: Bundle?) {
    }

    override fun onFragmentStopped(fm: FragmentManager?, f: Fragment?) {
    }

    override fun onFragmentCreated(fm: FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {
    }

    override fun onFragmentResumed(fm: FragmentManager?, f: Fragment?) {
    }

    override fun onFragmentPreAttached(fm: FragmentManager?, f: Fragment?, context: Context?) {
    }

    override fun onFragmentDestroyed(fm: FragmentManager?, f: Fragment?) {
    }

    override fun onFragmentSaveInstanceState(fm: FragmentManager?, f: Fragment?, outState: Bundle?) {
    }

    override fun onFragmentStarted(fm: FragmentManager?, f: Fragment?) {
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager?, f: Fragment?) {
    }

    override fun onFragmentPreCreated(fm: FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {
    }

    override fun onFragmentActivityCreated(fm: FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {
    }

    override fun onFragmentPaused(fm: FragmentManager?, f: Fragment?) {
    }

    override fun onFragmentDetached(fm: FragmentManager?, f: Fragment?) {
    }
}