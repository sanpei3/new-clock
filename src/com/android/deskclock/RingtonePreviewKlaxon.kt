/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.best.deskclock

import android.content.Context
import android.net.Uri

import com.best.deskclock.LogUtils.i

object RingtonePreviewKlaxon {
    private var sAsyncRingtonePlayer: AsyncRingtonePlayer? = null

    @JvmStatic
    fun stop(context: Context) {
        i("RingtonePreviewKlaxon.stop()")
        getAsyncRingtonePlayer(context).stop()
    }

    @JvmStatic
    fun start(context: Context, uri: Uri) {
        stop(context)
        i("RingtonePreviewKlaxon.start()")
        getAsyncRingtonePlayer(context).play(uri, 0)
    }

    @Synchronized
    private fun getAsyncRingtonePlayer(context: Context): AsyncRingtonePlayer {
        if (sAsyncRingtonePlayer == null) {
            sAsyncRingtonePlayer = AsyncRingtonePlayer(context.applicationContext)
        }
        return sAsyncRingtonePlayer!!
    }
}