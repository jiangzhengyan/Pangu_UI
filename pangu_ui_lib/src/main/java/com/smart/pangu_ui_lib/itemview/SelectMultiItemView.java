package com.smart.pangu_ui_lib.itemview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smart.pangu_ui_lib.R;
import com.smart.pangu_ui_lib.base.BaseView;
import com.smart.pangu_ui_lib.entity.SelectItem;


/**
 * 本类的主要功能是 :  多选择框的item
 *
 * @author jiangzhengyan  2024/4/10 10:18
 */
public class SelectMultiItemView extends BaseView {

    private TextView mTvTitle;
    private ImageView mIvCheck;
    private LinearLayout mLlRoot;


    public SelectMultiItemView(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_pop_multi_select;
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mTvTitle = findViewById(R.id.tv_title);
        mIvCheck = findViewById(R.id.iv_check);
        mLlRoot = findViewById(R.id.ll_root);
    }

    public void setData(SelectItem bean) {
        if (bean == null) {
            return;
        }
        mTvTitle.setTextColor(bean.isSelect() ? getResources().getColor(R.color.blue_text_color) : Color.parseColor("#4E5969"));

        mLlRoot.setBackgroundColor(bean.isSelect() ? Color.parseColor("#E8F1FF") : getResources().getColor(R.color.white));
        mIvCheck.setVisibility(bean.isSelect() ? VISIBLE : GONE);
        mTvTitle.setText(bean.getName());
    }

}
