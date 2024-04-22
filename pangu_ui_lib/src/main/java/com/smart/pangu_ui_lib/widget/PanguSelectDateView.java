package com.smart.pangu_ui_lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.smart.pangu_ui_lib.R;
import com.smart.pangu_ui_lib.base.BaseView;
import com.smart.pangu_ui_lib.pop.PopSelectDate;
import com.smart.pangu_ui_lib.util.DateUtil;


/**
 * 本类的主要功能是 :时间控件
 *
 * @author jiangzhengyan  2024/4/19 15:00
 */
public class PanguSelectDateView extends BaseView {

    private LinearLayout mRoot;
    private PanguSelectView mCsvStartDate;
    private ImageView mIvArrow;
    private PanguSelectView mCsvEndDate;


    private String keyStart;
    private String keyEnd;

    private int csdate_select_mode;
    private int csdate_show_mode;

    private String title;
    private String hintStart;
    private String hintEnd;
    private boolean enable;
    private boolean must;
    private int showTitle;
    private int titleColor;
    private boolean border;

    public PanguSelectDateView(Context context) {
        super(context);
    }

    public PanguSelectDateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.pangu_select_date_view;
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mRoot = findViewById(R.id.root);
        mCsvStartDate = findViewById(R.id.csv_start_date);
        mIvArrow = findViewById(R.id.iv_arrow);
        mCsvEndDate = findViewById(R.id.csv_end_date);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PanguSelectDateView, defStyleAttr, 0);
        //名称
        title = typedArray.getString(R.styleable.PanguSelectDateView_pgsdate_title);
        //开始时间的提示
        hintStart = typedArray.getString(R.styleable.PanguSelectDateView_pgsdate_hint_start);
        //结束时间的提示
        hintEnd = typedArray.getString(R.styleable.PanguSelectDateView_pgsdate_hint_end);
        //是否可用
        enable = typedArray.getBoolean(R.styleable.PanguSelectDateView_pgsdate_enable, true);
        //必选
        must = typedArray.getBoolean(R.styleable.PanguSelectDateView_pgsdate_must, false);
        //显示日期样式
        //0,年; 1,年月日; 2,年月日时分;3,月日;4,时分;5年月日时分秒
        csdate_select_mode = typedArray.getInt(R.styleable.PanguSelectDateView_pgsdate_select_mode, 1);
        //1,显示一个日期 ; 2,显示两个日期
        csdate_show_mode = typedArray.getInt(R.styleable.PanguSelectDateView_pgsdate_show_mode, 2);
        //是否显示标题
        showTitle = typedArray.getInt(R.styleable.PanguSelectDateView_pgsdate_show_title, 0);
        //标题颜色
        titleColor = typedArray.getInteger(R.styleable.PanguSelectDateView_pgsdate_title_color, getResources().getColor(R.color.main_subtitle_color));

        //边框
        border = typedArray.getBoolean(R.styleable.PanguSelectDateView_pgsdate_border, true);
        typedArray.recycle();

        init();
    }


    public void init() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(params);

        mCsvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker(0, csdate_select_mode);
            }
        });

        mCsvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker(1, csdate_select_mode);
            }
        });

        setDateShowMode(csdate_show_mode);
        setDateSelectMode(csdate_select_mode);
        setIsMust(must);
        setHintStart(hintStart);
        setHintEnd(hintEnd);
        setTitle(title);
        setEnable(enable);
        setTitleVisibility(showTitle);
        setTitleColor(titleColor);
        setBorder(border);

    }
    public void setTitleColor(int titleColor) {
        mCsvStartDate.setTitleColor(titleColor);
    }
    /**
     * 设置输入框的边框  true :设置边框
     */
    private void setBorder(boolean border) {
        this.border = border;
        mCsvStartDate.setBorder(border);
        mCsvEndDate.setBorder(border);
    }
    /**
     * 设置布局宽高
     *
     * @param width
     * @param height
     */
    public void setSize(int width, int height) {
        ViewGroup.LayoutParams layoutParams = mRoot.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        mRoot.setLayoutParams(layoutParams);
    }

    public void setIsMust(boolean must) {
        this.must = must;
        mCsvStartDate.setIsMust(must);
    }

    private void setHintEnd(String hintEnd) {
        mCsvEndDate.setHint(hintEnd);
    }


    public void setHintStart(String hintStart) {
        mCsvStartDate.setHint(hintStart);
    }

    /**
     * 是否显示标题
     *
     * @param visibility {@link View#VISIBLE}   {@link View#INVISIBLE}   {@link View#GONE}
     */
    public void setTitleVisibility(int visibility) {
        if (mCsvStartDate != null) {
            mCsvStartDate.setTitleVisibility(visibility);
        }
        if (mCsvEndDate != null) {
            mCsvEndDate.setTitleVisibility(visibility);
        }
    }

    /**
     * 日期显示形式
     *
     * @param mode 1,显示一个日期 ; 2,显示两个日期
     */
    public void setDateShowMode(int mode) {
        this.csdate_show_mode = mode;
        mIvArrow.setVisibility(mode == 1 ? GONE : VISIBLE);
        mCsvEndDate.setVisibility(mode == 1 ? GONE : VISIBLE);
    }

    /**
     * 日期选择形式
     *
     * @param mode 0,年; 1,年月日; 2,年月日时分;3,月日;4,时分;5年月日时分秒
     */
    public void setDateSelectMode(int mode) {
        csdate_select_mode = mode;
        switch (csdate_select_mode) {
            case 0:
                transFormat = "yyyy";
                break;
            case 1:
                transFormat = "yyyy-MM-dd";
                break;
            case 2:
                transFormat = "yyyy-MM-dd HH:mm";
                break;
            case 3:
                transFormat = "MM-dd";
                break;
            case 4:
                transFormat = "HH:mm";
                break;
            case 5:
                transFormat = "yyyy-MM-dd HH:mm:ss";
                break;
        }
    }

    /**
     * 日期选择形式
     *
     * @return {@link #setDateSelectMode(int)}
     */
    public int getDateSelectMode() {
        return csdate_select_mode;
    }

    /**
     * 获取时间转换格式
     *
     * @return 时间转换格式 {@link #setDateSelectMode(int)}
     */
    public String getDateTransFormat() {
        return transFormat;
    }


    //设置搜索条件名称和输入提示
    public void setData(String name, String keyStart, String keyEnd) {
        mCsvStartDate.setTitle(name);
        mCsvStartDate.setSelectText("", keyStart);
        mCsvEndDate.setSelectText("", keyEnd);

        this.keyStart = keyStart;
        this.keyEnd = keyEnd;
    }

    private String transFormat = "yyyy-MM-dd";

    /**
     * 显示时间选择
     *
     * @param type 0,开始时间; 1,结束时间
     */
    private void showTimePicker(int type, int csdate_select_mode) {
        PopSelectDate timePicker = new PopSelectDate(getContext());
        timePicker.setDateSelectMode(csdate_select_mode);


        //回显时间滚轮时间
        timePicker.setCurrentDate(type == 0 ? getStartTime() : getEndTime(), transFormat);

        timePicker.setOnClickSureListener(view -> {

            String selctTime = timePicker.getSelectDate(transFormat);
            if (onDateSureListener != null) {
                //true的话代表自己处理
                long dateTimeMillis = DateUtil.getDateTimeMillis(selctTime, transFormat);

                boolean sureClick = onDateSureListener.onSureClick(type, type == 0 ? mCsvStartDate : mCsvEndDate, selctTime, dateTimeMillis);
                if (sureClick) {
                    return;
                }
            }
            if (type == 0) {
                // 开始时间
                setStartTime(selctTime);
            } else {
                // 结束时间
                setEndTime(selctTime);
            }

            //timePicker.dismiss();
        });
        timePicker.showAtCenter();
    }

    /**
     * 设置开始时间
     *
     * @param t
     */
    public void setStartTime(String t) {
        if (mCsvStartDate != null)
            mCsvStartDate.setSelectText("", t);
    }

    /**
     * 设置结束时间
     *
     * @param t
     */
    public void setEndTime(String t) {
        if (mCsvEndDate != null)
            mCsvEndDate.setSelectText("", t);
    }

    /**
     * @return
     */
    public String getYear() {
        if (csdate_select_mode == 0 || csdate_select_mode == 1 || csdate_select_mode == 2 || csdate_select_mode == 5) {
            String startTime = getStartTime();
            if (!TextUtils.isEmpty(startTime)) {
                return startTime.substring(0, 4);
            }
        }
        return "";
    }

    /**
     * 获取开始时间
     *
     * @return
     */
    public String getStartTime() {
        String start = mCsvStartDate.getValue();
        if (TextUtils.isEmpty(start)) {
            start = "";
        }
        return start;
    }

    /**
     * 获取结束时间
     *
     * @return
     */
    public String getEndTime() {
        String end = mCsvEndDate.getValue();
        if (TextUtils.isEmpty(end)) {
            end = "";
        }
        return end;
    }

    /**
     * 获取开始时间
     *
     * @return
     */
    public long getStartTimeMillis() {
        String start = mCsvStartDate.getValue();
        if (TextUtils.isEmpty(start)) {
            start = "";
        }

        String originFormat = "";
        switch (csdate_select_mode) {
            case 0:
                originFormat = "yyyy";
                break;
            case 1:
                originFormat = "yyyy-MM-dd";
                break;
            case 2:
                originFormat = "yyyy-MM-dd HH:mm";
                break;
            case 3:
                originFormat = "MM-dd";
                break;
            case 4:
                originFormat = "HH:mm";
                break;
            case 5:
                originFormat = "yyyy-MM-dd HH:mm:ss";
                break;
        }


        return DateUtil.getDateTimeMillis(start, originFormat);
    }

    /**
     * 获取结束时间
     *
     * @return
     */
    public long getEndTimeMillis() {
        String end = mCsvEndDate.getValue();
        if (TextUtils.isEmpty(end)) {
            end = "";
        }
        String originFormat = "";
        switch (csdate_select_mode) {
            case 0:
                originFormat = "yyyy";
                break;
            case 1:
                originFormat = "yyyy-MM-dd";
                break;
            case 2:
                originFormat = "yyyy-MM-dd HH:mm";
                break;
            case 3:
                originFormat = "MM-dd";
                break;
            case 4:
                originFormat = "HH:mm";
                break;
            case 5:
                originFormat = "yyyy-MM-dd HH:mm:ss";
                break;
        }


        return DateUtil.getDateTimeMillis(end, originFormat);
    }

    /**
     * 重置
     */
    public void reset() {
        mCsvStartDate.setSelectText("", "");
        mCsvEndDate.setSelectText("", "");
    }

    public void setTitle(String title) {
        if (mCsvStartDate != null) {
            mCsvStartDate.setTitle(title);
        }
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
        mCsvStartDate.setEnable(enable);
        mCsvEndDate.setEnable(enable);
    }

    private OnDateSureListener onDateSureListener;

    //确定
    public void setOnDateSureListener(OnDateSureListener onDateSureListener) {
        this.onDateSureListener = onDateSureListener;
    }

    //日期选中点击确定
    public interface OnDateSureListener {
        /**
         * 确定
         *
         * @param type                    0,开始时间; 1,结束时间
         * @param view                    选择控件
         * @param currentSelectDate       当前选择的日期
         * @param currentSelectDateMillis 当前选择的日期时间戳
         * @return
         */
        boolean onSureClick(int type, PanguSelectView view, String currentSelectDate, long currentSelectDateMillis);
    }
}
