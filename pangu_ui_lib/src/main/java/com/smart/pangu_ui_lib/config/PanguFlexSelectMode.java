/*
 * Copyright 2016 Google Inc. All rights reserved.
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

package com.smart.pangu_ui_lib.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 *  本类的主要功能是 :  自定义flex选中模式
 *
 * @author  jiangzhengyan  2024/4/10 9:19
 *
 */
@IntDef({PanguFlexSelectMode.SINGLE, PanguFlexSelectMode.MULTIPLE, PanguFlexSelectMode.ONE_MOST, PanguFlexSelectMode.ONE_LEAST})
@Retention(RetentionPolicy.SOURCE)
public @interface PanguFlexSelectMode {


    int SINGLE = 0;//单选

    int MULTIPLE = 1;//多选

    int ONE_MOST = 2;//最多选择一个

    int ONE_LEAST = 3;//至少选择一个
}
