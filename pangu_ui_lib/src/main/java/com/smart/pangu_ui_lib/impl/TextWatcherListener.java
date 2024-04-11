package com.smart.pangu_ui_lib.impl;

import android.text.Editable;
import android.text.TextWatcher;

import com.smart.pangu_ui_lib.entity.SelectItem;


/**
 *  本类的主要功能是 :  文本框输入监听
 *
 * @author  jiang_zheng_yan  2021/5/18 19:44
 *
 */
public class TextWatcherListener implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void afterTextChanged(Editable intputContent, SelectItem currentItem, int position, boolean currentItemChecked) {

    }
}
