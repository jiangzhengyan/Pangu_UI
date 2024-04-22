package com.smart.pangu_ui_lib.pop;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smart.pangu_ui_lib.R;
import com.smart.pangu_ui_lib.base.PanguBasePop;
import com.smart.pangu_ui_lib.util.DateUtil;
import com.smart.pangu_ui_lib.widget.wheelview.adapter.BaseWheelAdapter;
import com.smart.pangu_ui_lib.widget.wheelview.interfaces.PickerViewData;
import com.smart.pangu_ui_lib.widget.wheelview.listener.OnItemSelectedListener;
import com.smart.pangu_ui_lib.widget.wheelview.view.WheelView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * 本类的主要功能是 :  时间弹窗
 *
 * @author jiang_zheng_yan  2020/4/7 17:27
 */
public class PopSelectDate extends PanguBasePop {


    private static final String TAG = "PopWheelViewDemo";
    private LinearLayout mLlContainer;
    private WheelView mWvHour;
    private WheelView mWvMin;
    private TextView mTvYear;
    private TextView mTvMonth;
    private TextView mTvDay;
    private TextView mTvHour;
    private TextView mTvMin;
    private TextView mTvSecond;
    private TextView mTvCancel;
    private TextView mTvSure;
    private LinearLayout mLlPopRoot;
    private WheelView mWvYear;
    private WheelView mWvMonth;
    private WheelView mWvDay;
    private WheelView mWvSecond;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int min;
    private int second;
    private static int startyear = 1900;
    private static int endyear = 2100;

    private int date_select_mode = 2;//0,年; 1,年月日; 2,年月日时分


    public PopSelectDate(Context context) {
        super(context);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.pop_select_date;
    }

    @Override
    public void initData(View v, Context context) {
        mLlContainer = v.findViewById(R.id.ll_container);
        mWvHour = v.findViewById(R.id.wv_hour);
        mWvMin = v.findViewById(R.id.wv_min);
        mTvYear = v.findViewById(R.id.tv_year);
        mTvMonth = v.findViewById(R.id.tv_month);
        mTvDay = v.findViewById(R.id.tv_day);
        mTvHour = v.findViewById(R.id.tv_hour);
        mTvMin = v.findViewById(R.id.tv_min);
        mTvSecond = v.findViewById(R.id.tv_second);
        mTvCancel = v.findViewById(R.id.tv_cancel);
        mTvSure = v.findViewById(R.id.tv_sure);
        mLlPopRoot = v.findViewById(R.id.ll_pop_root);
        mWvYear = v.findViewById(R.id.wv_year);
        mWvMonth = v.findViewById(R.id.wv_month);
        mWvDay = v.findViewById(R.id.wv_day);
        mWvSecond = v.findViewById(R.id.wv_second);


        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        initListener();
        //动画
        setAnimaType(AnimaType.BOTTOM_IN_OUT);
        //设置日期数据
        setDateData();
    }

    private void initListener() {
        mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (onClickSureListener != null) {
                    onClickSureListener.onClick(view);
                }
                Log.e(TAG, "onViewClicked: " + getSelectDate("yyyy*MM*dd"));
            }
        });

        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (onClickCancelListener != null) {
                    onClickCancelListener.onClick(view);
                }
            }
        });
    }

    private void setDateData() {
        //年
        ArrayList<WheelData> years = new ArrayList<>();
        //月
        ArrayList<String> months = new ArrayList<>();
        //时
        ArrayList<String> hours = new ArrayList<>();
        //分
        ArrayList<String> mins = new ArrayList<>();
        //秒
        ArrayList<String> seconds = new ArrayList<>();
        for (int i = startyear; i <= endyear; i++) {
            WheelData wheelData = new WheelData();
            wheelData.setAge(i);
            wheelData.setName(i + "");
            years.add(wheelData);
        }
        for (int i = 1; i <= 12; i++) {
            months.add((i + "").length() == 1 ? "0" + i : i + "");
        }
        for (int i = 0; i < 24; i++) {
            hours.add((i + "").length() == 1 ? "0" + i : i + "");
        }
        for (int i = 0; i < 60; i++) {
            mins.add((i + "").length() == 1 ? "0" + i : i + "");
        }
        for (int i = 0; i < 60; i++) {
            seconds.add((i + "").length() == 1 ? "0" + i : i + "");
        }

        //年
        mWvYear.setAdapter(new BaseWheelAdapter<WheelData>(years));
        mWvYear.setCurrentItem(year - startyear);
        mWvYear.setCyclic(false);
        mWvYear.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index, Object item) {
                year = Integer.parseInt(((WheelData) item).getPickerViewText());
                mWvDay.setAdapter(new BaseWheelAdapter<String>(DateUtil.getDaysList(year, month)));

            }

        });
        //月
        mWvMonth.setAdapter(new BaseWheelAdapter<String>(months));
        mWvMonth.setCurrentItem(month - 1);
        mWvMonth.setCyclic(false);
        mWvMonth.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index, Object item) {
                month = index + 1;
                int preItemIndex = mWvDay.getCurrentItemIndex();
                ArrayList<String> daysList = DateUtil.getDaysList(year, month);
                if (preItemIndex + 1 > daysList.size()) {
                    preItemIndex = daysList.size() - 1;
                }
                mWvDay.setAdapter(new BaseWheelAdapter<String>(daysList));
                //修复问题
                mWvDay.setCurrentItem(preItemIndex);
            }

        });
        //日
        mWvDay.setAdapter(new BaseWheelAdapter<String>(DateUtil.getDaysList(year, month)));
        mWvDay.setCurrentItem(day - 1);
        mWvDay.setCyclic(false);
        mWvDay.cancelFuture();


        //小时
        mWvHour.setAdapter(new BaseWheelAdapter<String>(hours));
        mWvHour.setCurrentItem(hour);
        mWvHour.setCyclic(false);
        mWvHour.cancelFuture();

        //分
        mWvMin.setAdapter(new BaseWheelAdapter<String>(mins));
        mWvMin.setCurrentItem(min);
        mWvMin.setCyclic(false);
        mWvMin.cancelFuture();

        //秒
        mWvSecond.setAdapter(new BaseWheelAdapter<String>(seconds));
        mWvSecond.setCurrentItem(second);
        mWvSecond.setCyclic(false);
        mWvSecond.cancelFuture();
    }

    /**
     * 获取选择的日期时间
     *
     * @return
     */
    public String getSelectDate(String format) {
        String originFormat = "yyyy-MM-dd HH:mm";
//     0,年; 1,年月日; 2,年月日时分 3,月日 4,时分 5,年月日时分秒
        String dateStr = year + "-" + month + "-" + mWvDay.getCurrentItem() + " " + mWvHour.getCurrentItem() + ":" + mWvMin.getCurrentItem() + ":" + mWvSecond.getCurrentItem();
        switch (date_select_mode) {
            case 0:
                dateStr = year + "";
                originFormat = "yyyy";
                break;
            case 1:
                dateStr = year + "-" + month + "-" + mWvDay.getCurrentItem();
                originFormat = "yyyy-MM-dd";
                break;
            case 2:
                dateStr = year + "-" + month + "-" + mWvDay.getCurrentItem() + " " + mWvHour.getCurrentItem() + ":" + mWvMin.getCurrentItem();
                originFormat = "yyyy-MM-dd HH:mm";
                break;
            case 3:
                dateStr = month + "-" + mWvDay.getCurrentItem();
                originFormat = "MM-dd";
                break;
            case 4:
                dateStr = mWvHour.getCurrentItem() + ":" + mWvMin.getCurrentItem();
                originFormat = "HH:mm";
                break;
            case 5:
                dateStr = year + "-" + month + "-" + mWvDay.getCurrentItem() + " " + mWvHour.getCurrentItem() + ":" + mWvMin.getCurrentItem() + ":" + mWvSecond.getCurrentItem();
                originFormat = "yyyy-MM-dd HH:mm:ss";
                break;
        }

        return DateUtil.translateDateStr(dateStr, originFormat, format);
    }

    /**
     * 日期选择形式
     *
     * @param mode 0,年; 1,年月日; 2,年月日时分 3,月日 4,时分 5,年月日时分秒
     */
    public void setDateSelectMode(int mode) {
        date_select_mode = mode;

        //显示年
        //显示月
        //显示日
        //显示时
        //显示分
        boolean showYear = mode == 0 || mode == 1 || mode == 2 || mode == 5;
        boolean showMonth = mode == 1 || mode == 2 || mode == 3 || mode == 5;
        boolean showDay = mode == 1 || mode == 2 || mode == 3 || mode == 5;
        boolean showHour = mode == 2 || mode == 4 || mode == 5;
        boolean showMin = mode == 2 || mode == 4 || mode == 5;
        boolean showSecond = mode == 5;

        mTvYear.setVisibility(showYear ? View.VISIBLE : View.GONE);
        mTvMonth.setVisibility(showMonth ? View.VISIBLE : View.GONE);
        mTvDay.setVisibility(showDay ? View.VISIBLE : View.GONE);
        mTvHour.setVisibility(showHour ? View.VISIBLE : View.GONE);
        mTvMin.setVisibility(showMin ? View.VISIBLE : View.GONE);
        mTvSecond.setVisibility(showSecond ? View.VISIBLE : View.GONE);

        mWvYear.setVisibility(showYear ? View.VISIBLE : View.GONE);
        mWvMonth.setVisibility(showMonth ? View.VISIBLE : View.GONE);
        mWvDay.setVisibility(showDay ? View.VISIBLE : View.GONE);
        mWvHour.setVisibility(showHour ? View.VISIBLE : View.GONE);
        mWvMin.setVisibility(showMin ? View.VISIBLE : View.GONE);
        mWvSecond.setVisibility(showSecond ? View.VISIBLE : View.GONE);

    }

    /**
     * 是否循环
     *
     * @param isCylic
     */
    public void setIsCylic(boolean isCylic) {
        mWvYear.setCyclic(isCylic);
        mWvMonth.setCyclic(isCylic);
        mWvDay.setCyclic(isCylic);
        mWvHour.setCyclic(isCylic);
        mWvMin.setCyclic(isCylic);
    }


    /**
     * 设置当前选中的日期
     *
     * @param dateStr 日期
     * @param format  格式 yyyy-MM-dd HH:mm:ss
     */
    public void setCurrentDate(String dateStr, String format) {
        if (TextUtils.isEmpty(format)) {
            return;
        }
        try {
            long dateTimeMillis = DateUtil.getDateTimeMillis(dateStr, format);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateTimeMillis == 0 ? new Date() : new Date(dateTimeMillis));
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DAY_OF_MONTH);
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            min = calendar.get(Calendar.MINUTE);
            second = calendar.get(Calendar.SECOND);
            setDateData();
        } catch (Exception e) {
            Log.e(TAG, "setCurrentDate: 设置当前日期异常");
        }
    }

    // 设置wheelview 的数据实体(除 string int)需要实现 IPickerViewData
    static class WheelData implements PickerViewData {
        private String name;
        private int age;

        @Override
        public String getPickerViewText() {
            return name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}