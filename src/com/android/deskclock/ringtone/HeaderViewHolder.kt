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

package com.best.deskclock.ringtone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.best.deskclock.ItemAdapter.ItemViewHolder
import com.best.deskclock.R

internal class HeaderViewHolder private constructor(itemView: View)
    : ItemViewHolder<HeaderHolder>(itemView) {
    private val mItemHeader: TextView =
            itemView.findViewById<View>(R.id.ringtone_item_header) as TextView

    override fun onBindItemView(itemHolder: HeaderHolder) {
        mItemHeader.setText(itemHolder.textResId)
    }

    class Factory internal constructor(private val mInflater: LayoutInflater)
        : ItemViewHolder.Factory {
        override fun createViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<*> {
            return HeaderViewHolder(mInflater.inflate(viewType, parent, false))
        }
    }

    companion object {
        const val VIEW_TYPE_ITEM_HEADER = R.layout.ringtone_item_header
    }
}