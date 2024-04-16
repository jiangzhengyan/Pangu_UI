package com.smart.pangu.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.smart.pangu.R;
import com.smart.pangu_ui_lib.widget.PanguNavBar;

import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;

/**
 * 本类的主要功能是 : 基类Activity
 *
 * @author jiangzhengyan  2024/4/9 19:23
 */
public abstract class BaseActivity extends FragmentActivity {
    protected PanguNavBar mPanguNavBar;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        beforeSetContentView();
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        initBase();
        initView(savedInstanceState);
        loadData();

    }

    // 初始化base
    private void initBase() {
        //标题栏
        mPanguNavBar = findViewById(R.id.pg_nav_bar);
        if (mPanguNavBar != null) {
            mPanguNavBar.setOnLelftClickListener(this::onBackClick);
            mPanguNavBar.setOnRightClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRightClick(v, 0);
                }
            });
            mPanguNavBar.setOnRightIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRightClick(v, 1);
                }
            });
            mPanguNavBar.setOnRightIcon2ClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRightClick(v, 2);
                }
            });
        }
    }

    /**************************************************************************************/
    // 左侧标题点击
    protected void onBackClick(View view) {
        finish();
    }

    /**
     * 右侧包体图标点击
     *
     * @param view
     * @param type 0,右侧标题; 1,右侧第一个icon; 2,右侧第二个icon
     */
    protected void onRightClick(View view, int type) {
    }


    protected void beforeSetContentView() {

    }

    // 吐司
    public void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            onBackClick(null);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //加载布局文件
    protected abstract int getContentViewId();

    //初始化Views
    protected abstract void initView(Bundle savedInstanceState);

    //加载数据
    protected abstract void loadData();

    //设置状态栏颜色
    protected void setStatusColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(color);
        }
    }

    protected Activity getActivity() {
        return this;
    }


}
