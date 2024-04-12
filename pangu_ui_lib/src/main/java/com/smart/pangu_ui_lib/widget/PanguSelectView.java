package com.smart.pangu_ui_lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smart.pangu_ui_lib.R;
import com.smart.pangu_ui_lib.base.BaseView;
import com.smart.pangu_ui_lib.entity.SelectItem;
import com.smart.pangu_ui_lib.pop.PopMultiSelect;
import com.smart.pangu_ui_lib.pop.PopSelect;
import com.smart.pangu_ui_lib.util.PhoneHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * 本类的主要功能是 :   下拉选择框
 *
 * @author jiangzhengyan  2024/4/10 9:13
 */
public class PanguSelectView extends BaseView {

    //view
    private TextView mTvName;
    private TextView mTvSelect;
    private RelativeLayout mRlSelect;
    private LinearLayout mRoot;
    private TextView mTvMust;
    private ImageView mIvRight;
    private LinearLayout mLlTitle;
    private View mView;
    private View mViewLine;
    private PanguFlexBoxView mMflexMultiSelect;
    //属性
    private String title;
    private String hint;
    private boolean enable;
    private boolean must;
    private boolean showIconRight;
    private int iconRight;
    private int showTitle;//0 visiable; 1 ,invisiable; 2,gone
    private int titleColor;
    private boolean border;//展示输入框的边框
    private boolean showLine;//是否展示下面的线
    private int mOrientation;
    private int titleGravity;
    private boolean multiSelect;
    private int titleSize;//标题字体大小
    //变量
    private String key;
    private String value;
    private List<SelectItem> items;
    private SelectItem selectItem;
    private List<SelectItem> selectedItems;//多选的选中的数据

    @Override
    protected int getLayoutId() {
        return R.layout.pangu_select_view;
    }

    public PanguSelectView(Context context) {
        super(context);
    }

    public PanguSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PanguSelectView, defStyleAttr, 0);

        //名称标题
        title = typedArray.getString(R.styleable.PanguSelectView_pgsv_title);
        //提示
        hint = typedArray.getString(R.styleable.PanguSelectView_pgsv_hint);
        //是否可用
        enable = typedArray.getBoolean(R.styleable.PanguSelectView_pgsv_enable, true);
        //是否必填
        must = typedArray.getBoolean(R.styleable.PanguSelectView_pgsv_must, false);
        //展示右侧图标
        showIconRight = typedArray.getBoolean(R.styleable.PanguSelectView_pgsv_show_icon_right, true);
        //设置右侧图标
        iconRight = typedArray.getResourceId(R.styleable.PanguSelectView_pgsv_icon_right, 0);
        //是否显示标题
        showTitle = typedArray.getInt(R.styleable.PanguSelectView_pgsv_show_title, 0);
        //标题颜色
        titleColor = typedArray.getInteger(R.styleable.PanguSelectView_pgsv_title_color, getResources().getColor(R.color.main_subtitle_color));
        //标题字体大小
        titleSize = typedArray.getDimensionPixelSize(R.styleable.PanguSelectView_pgsv_title_size, PhoneHelper.sp2px(getContext(), 14));
        //输入框的边框
        border = typedArray.getBoolean(R.styleable.PanguSelectView_pgsv_border, false);
        //展示下横线
        showLine = typedArray.getBoolean(R.styleable.PanguSelectView_pgsv_show_line, true);
        //是否是多选
        multiSelect = typedArray.getBoolean(R.styleable.PanguSelectView_pgsv_multi_select, false);
        //标题和选择框的方向位置,VERTICAL|HORIZONTAL
        mOrientation = typedArray.getInt(R.styleable.PanguSelectView_pgsv_orientation, 0);
        //标题的重心
        titleGravity = typedArray.getInt(R.styleable.PanguSelectView_pgsv_title_gravity, mOrientation == LinearLayout.HORIZONTAL ? (Gravity.END | Gravity.CENTER_VERTICAL) : (Gravity.START | Gravity.CENTER_VERTICAL));

        typedArray.recycle();
        init();
    }

    public void init() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(params);

        mTvName = findViewById(R.id.tv_name);
        mTvSelect = findViewById(R.id.tv_select);
        mRlSelect = findViewById(R.id.rl_select);
        mRoot = findViewById(R.id.root);
        mTvMust = findViewById(R.id.tv_must);
        mIvRight = findViewById(R.id.iv_right);
        mLlTitle = findViewById(R.id.ll_title);
        mView = findViewById(R.id.view);
        mViewLine = findViewById(R.id.view_line);
        mMflexMultiSelect = findViewById(R.id.mflex_multi_select);

        setTitle(title);
        setHint(hint);
        //标题的大小
        setTitleTextSize(titleSize);
        setEnable(enable);
        setIsMust(must);
        setTitleColor(titleColor);
        //设置输入框的边框
        setBorder(border);
        //是否展示下面的线
        showLine(showLine);
        setLayoutOrientation(mOrientation);
        setTitleGravity(titleGravity);
        setIconRight(iconRight);
        setTitleVisibility(showTitle);
        setIconRightVisibility(showIconRight);
        setMultiSelect(multiSelect);
    }

    private void setIconRight(int iconRight) {
        if (mIvRight != null) {
            if (iconRight != 0) {
                mIvRight.setVisibility(VISIBLE);
                mIvRight.setImageResource(iconRight);
            }
        }
    }

    /**
     * 设置标题大小
     *
     * @param titleSize 标题大小 px
     */
    private void setTitleTextSize(int titleSize) {
        mTvName.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
    }

    public void setMultiSelect(boolean multiSelect) {
        this.multiSelect = multiSelect;
    }

    public void setTitleColor(int titleColor) {
        if (mTvName != null) {
            mTvName.setTextColor(titleColor);
        }
    }


    /**
     * 获取title的父布局
     *
     * @return
     */
    public LinearLayout getLlTitle() {
        return mLlTitle;
    }

    private void setIconRightVisibility(boolean showIconRight) {
        if (mIvRight != null) {
            mIvRight.setVisibility(showIconRight ? VISIBLE : GONE);
        }
    }

    /**
     * 设置输入框的边框  true :边框
     */
    public void setBorder(boolean border) {
        this.border = border;
        mRlSelect.setBackgroundResource(border ? R.drawable.selector_form : R.drawable.selector_form_transparen);
        mTvSelect.setPadding(border ? PhoneHelper.dip2px(mContext, 6) : 0, PhoneHelper.dip2px(mContext, 6), PhoneHelper.dip2px(mContext, 6), PhoneHelper.dip2px(mContext, 6));
        //块的高度
        setLineBlockHeight();
    }

    private void setLineBlockHeight() {
        ViewGroup.LayoutParams layoutParams = mView.getLayoutParams();
        layoutParams.height = mOrientation == LinearLayout.VERTICAL && border ? PhoneHelper.dip2px(mContext, 4) : 0;
    }

    /**
     * 显示下面的线
     *
     * @param showLine
     */
    public void showLine(boolean showLine) {
        if (mViewLine != null) {
            mViewLine.setVisibility(showLine ? VISIBLE : GONE);
        }
    }

    /**
     * 设置布局方向,0-水平  1-垂直
     */
    public void setLayoutOrientation(int orientation) {

        mRoot.setOrientation(orientation);
        // ViewGroup.LayoutParams layoutParams = mView.getLayoutParams();

        if (orientation == LinearLayout.HORIZONTAL) {
            mTvName.setMaxEms(6);
            mTvName.setMinEms(4);
        } else {
            mTvName.setMaxEms(30);
            mTvName.setMinEms(4);
        }
        //块的高度
        setLineBlockHeight();
    }

    public void setTitleGravity(int titleGravity) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mLlTitle.getLayoutParams();

        layoutParams.gravity = titleGravity;
        mLlTitle.setGravity(titleGravity);
    }

    /**
     * 是否显示标题
     *
     * @param visibility //0 visiable; 1 ,invisiable; 2,gone
     */
    public void setTitleVisibility(int visibility) {
        if (mTvName != null) {
            mTvName.setVisibility(visibility == 0 ? VISIBLE : visibility == 1 ? INVISIBLE : GONE);
        }
    }

    public void setIsMust(boolean must) {
        if (mTvMust != null) {
            mTvMust.setVisibility(must ? VISIBLE : GONE);
        }
    }

    public void setHint(String hint) {
        this.hint = hint;
        if (mTvSelect != null) {
            mTvSelect.setHint(hint);
        }
    }

    public String getHint() {
        return hint;
    }

    public void setTitle(String title) {
        if (mTvName != null) {
            mTvName.setText(title);
        }
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
        if (mRlSelect != null) {
            mRlSelect.setEnabled(enable);
            mRlSelect.setClickable(enable);
        }
    }

    public void setOnTitleClickListener(View.OnClickListener listener) {
        mTvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(view);
                }
            }
        });
    }

    /**
     * 点击事件,自己处理点击事件/弹窗
     *
     * @param listener retrn true:不弹窗,false:弹窗
     */
    public void setOnClickListener(OnClickListenerCus listener) {
        mRlSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    boolean click = listener.onClick(view);
                    if (!click) {
                        showPop();
                    }
                }
            }
        });
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

    private void showPop() {
        if (items == null || items.size() == 0) {
            showToast("没有选项");
            return;
        }

        if (!multiSelect) {
            PopSelect popSelect = new PopSelect(getContext());
            popSelect.setData(items);
            popSelect.setOnClickSureListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectItem = popSelect.getSelectItem();
                    mTvSelect.setText(selectItem.getName());
                    if (sureClickListener != null) {
                        sureClickListener.onClick(selectItem);
                    }

                }
            });
            popSelect.showAtCenter();
            return;
        }

        //多选弹窗
        PopMultiSelect popMultiSelect = new PopMultiSelect(getContext());
        popMultiSelect.setData(items);
        popMultiSelect.setOnClickSureListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedItems = popMultiSelect.getSelectedDatas();
                mTvSelect.setHint(selectedItems.size() == 0 ? hint : "");
                mMflexMultiSelect.setDataText(selectedItems, R.layout.item_flex_box_multi, new PanguFlexBoxView.OnItemClickListener() {
                    @Override
                    public void onItemClick(SelectItem item, int position) {
                        //删除

                        String id = item.getId();
                        //1,删除本view上的item
                        selectedItems.remove(position);
                        //2,刷新(重置)item的view
                        mMflexMultiSelect.setDataText(selectedItems, R.layout.item_flex_box_multi, this);
                        //处理hint
                        mTvSelect.setHint(selectedItems.size() == 0 ? hint : "");

                        //3,让源数据选择状态为false
                        for (int i = 0; i < items.size(); i++) {
                            SelectItem selectItem = items.get(i);
                            if (TextUtils.equals(selectItem.getId(), id)) {
                                selectItem.setSelect(false);
                            }
                        }
                        popMultiSelect.setData(items);
                    }
                });
                if (sureClickListener != null) {
                    sureClickListener.onClick(selectedItems);
                }
            }
        });
        popMultiSelect.showAtCenter();

    }

    /**
     *  设置弹窗数据(带默认值)
     * @param value
     * @param key
     * @param items
     */
    public void setData(String value, String key, List<SelectItem> items) {
        mTvSelect.setText(value);

        this.key = key;
        this.value = value;
        //
        this.selectItem = new SelectItem(key, value);
        this.items = items;

        mRlSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPop();
            }
        });
    }

    /**
     * 设置数据
     *
     * @param items
     */
    public void setData(List<SelectItem> items) {
        this.items = items;
        mRlSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPop();
            }
        });
    }

    /**
     * 设置数据/没有弹窗
     *
     * @param items
     */
    public void setDataOnly(List<SelectItem> items) {
        this.items = items;
    }

    /**
     * 获取选中的key
     *
     * @return
     */
    public String getKey() {
        if (selectItem == null) return "";
        return selectItem.getId();
    }

    /**
     * 获取选中的value
     *
     * @return
     */
    public String getValue() {
        if (selectItem == null) return "";
        return selectItem.getName();
    }

    /**
     * 重置
     */
    public void reset() {
        if (items != null)
            selectItem = items.get(0);
        mTvSelect.setText("全部");
    }

    /**
     * 清空数据
     */
    public void clear() {
        if (items != null)
            items = null;
        selectItem = null;
        mTvSelect.setText("");
    }

    /**
     * 获取items集合
     */

    public List<SelectItem> getItems() {

        return (items == null || items.size() == 0) ? new ArrayList<>() : items;
    }

    /**
     * 设置标题样式
     *
     * @param typeface Typeface.BOLD 加粗  Typeface.NORMAL 正常
     */
    public void setTitleTextStyle(int typeface) {
        if (mTvName != null) {
            mTvName.setTypeface(Typeface.defaultFromStyle(typeface));
        }
    }

    /**
     * 设置内容
     *
     * @param selectTextValue
     */
    public void setSelectText(String key, String selectTextValue) {
        selectItem = new SelectItem(key, selectTextValue);
        mTvSelect.setText(selectTextValue);
    }

    private OnSureClickListener sureClickListener;

    /**
     * 确定监听
     *
     * @param sureClickListener
     */
    public void setOnSureClickListener(OnSureClickListener sureClickListener) {
        this.sureClickListener = sureClickListener;
    }

    public static class OnSureClickListener {
        /**
         * 单选返回值
         *
         * @param selectItem
         */
        public void onClick(SelectItem selectItem) {
        }

        /**
         * 多选返回数据
         *
         * @param selectItem
         */
        public void onClick(List<SelectItem> selectItem) {
        }
    }

    public interface OnClickListenerCus {
        boolean onClick(View view);
    }

}
