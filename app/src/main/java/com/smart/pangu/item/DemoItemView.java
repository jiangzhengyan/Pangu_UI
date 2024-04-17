package com.smart.pangu.item;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smart.pangu.R;
import com.smart.pangu_ui_lib.base.BaseView;
import com.smart.pangu_ui_lib.entity.SelectItem;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 本类的主要功能是 :   BaseRecyclerViewAdapter适配器演示
 *
 * @author jiangzhengyan  2024/4/17 20:50
 */
public class DemoItemView extends BaseView {


    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.iv_check)
    ImageView mIvCheck;
    @Bind(R.id.ll_root)
    LinearLayout mLlRoot;

    public DemoItemView(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_demo;
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        ButterKnife.bind(this);
    }

    /**
     * 设置数据的业务逻辑全在这
     *
     * @param bean
     */
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
