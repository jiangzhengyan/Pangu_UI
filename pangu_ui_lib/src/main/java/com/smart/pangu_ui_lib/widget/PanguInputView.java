package com.smart.pangu_ui_lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smart.pangu_ui_lib.R;
import com.smart.pangu_ui_lib.base.BaseView;
import com.smart.pangu_ui_lib.util.KeyboardHelper;
import com.smart.pangu_ui_lib.util.PhoneHelper;

import androidx.annotation.DrawableRes;

/**
 *  本类的主要功能是 :   个性化输入框
 *
 * @author  jiangzhengyan  2024/4/9 19:06
 *
 */
public class PanguInputView extends BaseView {


    private TextView mTvMust;
    private TextView mTvName;
    private LinearLayout mLlTitle;
    private EditText mEdContent;
    private ImageView mIvRight;
    private ImageView mIvRB;
    private LinearLayout mLlEt;
    private LinearLayout mRoot;
    private View mViewLine;
    private View mView;
    private String title;
    private String hint;

    private boolean enable;
    private boolean must;//必填红星
    private int iconRight;
    private int iconRB;
    private int showTitle;//0 visiable; 1 ,invisiable; 2,gone
    private float mInputHeight;//输入框的高度
    private float mInputMinHeight;//输入框的最小高度
    private int mOrientation;
    private int titleGravity, titleLayoutGravity;

    private boolean border;//展示输入框的边框
    private boolean showLine;//是否展示下面的线
    private int titleColor;//标题颜色
    private int titleTextStyle;//title的加粗
    private int titleSize;//标题字体大小
    private int gravity;
    private int inputType;
    private int maxlength;


    @Override
    protected int getLayoutId() {
        return R.layout.pangu_input_view;
    }

    public PanguInputView(Context context) {
        this(context, null);
    }

    public PanguInputView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PanguInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PanguInputView, defStyleAttr, 0);

        //名称
        title = typedArray.getString(R.styleable.PanguInputView_pgiv_title);
        //提示
        hint = typedArray.getString(R.styleable.PanguInputView_pgiv_hint);
        //是否可用
        enable = typedArray.getBoolean(R.styleable.PanguInputView_pgiv_enable, true);
        must = typedArray.getBoolean(R.styleable.PanguInputView_pgiv_must, false);
        //展示输入框的边框
        border = typedArray.getBoolean(R.styleable.PanguInputView_pgiv_border, true);
        showLine = typedArray.getBoolean(R.styleable.PanguInputView_pgiv_show_line, false);
        //标题的颜色
        titleColor = typedArray.getInteger(R.styleable.PanguInputView_pgiv_title_color, Color.parseColor("#1C2331"));
        //标题字体大小
        titleSize = typedArray.getDimensionPixelSize(R.styleable.PanguInputView_pgiv_title_size, PhoneHelper.sp2px(getContext(), 14));
        //右侧图标
        iconRight = typedArray.getResourceId(R.styleable.PanguInputView_pgiv_icon_right, 0);
        //右下角图标
        iconRB = typedArray.getResourceId(R.styleable.PanguInputView_pgiv_icon_rb, 0);

        //是否显示标题
        showTitle = typedArray.getInt(R.styleable.PanguInputView_pgiv_show_title, View.VISIBLE);
        //title的加粗
        titleTextStyle = typedArray.getInt(R.styleable.PanguInputView_pgiv_title_text_style, Typeface.BOLD);

        //输入框的高度
        mInputHeight = typedArray.getDimension(R.styleable.PanguInputView_pgiv_input_height, PhoneHelper.dip2px(context, 0));

        //输入框的最小高度
        mInputMinHeight = typedArray.getDimension(R.styleable.PanguInputView_pgiv_input_minheight, PhoneHelper.dip2px(context, 0));
        //
        gravity = typedArray.getInt(R.styleable.PanguInputView_pgiv_gravity, -1);
        mOrientation = typedArray.getInt(R.styleable.PanguInputView_pgiv_orientation, 0);
        titleGravity = typedArray.getInt(R.styleable.PanguInputView_pgiv_title_gravity, Gravity.START | Gravity.CENTER_VERTICAL);
        titleLayoutGravity = typedArray.getInt(R.styleable.PanguInputView_pgiv_title_layout_gravity, Gravity.CENTER_VERTICAL);
        //
        inputType = typedArray.getInt(R.styleable.PanguInputView_pgiv_inputType, -1);

        //最长
        maxlength = typedArray.getInt(R.styleable.PanguInputView_pgiv_maxLength, -1);

        typedArray.recycle();
        init();
    }

    public void init() {
        mTvMust = findViewById(R.id.tv_must);
        mTvName = findViewById(R.id.tv_name);
        mLlTitle = findViewById(R.id.ll_title);
        mEdContent = findViewById(R.id.ed_content);
        mIvRight = findViewById(R.id.iv_right);
        mIvRB = findViewById(R.id.ivRB);
        mLlEt = findViewById(R.id.ll_et);
        mRoot = findViewById(R.id.root);
        mViewLine = findViewById(R.id.view_line);
        mView = findViewById(R.id.view);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(params);
        setMaxLength(maxlength);

        setGravity(gravity);
        setInputType(inputType);

        //设置标题名称
        setTitle(title);
        //设置提示
        setHint(hint);

        //设置取消输入框的边框
        setBorder(border);
        //是否展示下面的线
        showLine(showLine);
        //标题的颜色
        setTitleColor(titleColor);
        //标题的大小
        setTitleTextSize(titleSize);

        setEnabled(enable);
        setIsMust(must);
        setRightIcon(iconRight);
        setRBIcon(iconRB);
        setShowTitle(showTitle);
        //输入框的高度
        if ((int) mInputHeight != 0) {
            setInputHeight((int) mInputHeight);
        }
        //输入框的最小高度
        if ((int) mInputMinHeight != 0) {
            setInputMiniHeight((int) mInputMinHeight);
        }
        setLayoutOrientation(mOrientation);
        setTitleGravity(titleGravity);
        setTitleLayoutGravity(titleLayoutGravity);
        setTitleTextStyle(titleTextStyle);
    }

    /**
     * @param showTitle One of {@link #VISIBLE}, {@link #INVISIBLE}, or {@link #GONE}.
     */
    private void setShowTitle(int showTitle) {
        mLlTitle.setVisibility(showTitle);
    }

    /**
     * 设置标题大小
     *
     * @param titleSize 标题大小 px
     */
    private void setTitleTextSize(int titleSize) {
        mTvName.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
    }

    /**
     * 设置输入框的边框  true :设置边框
     */
    public void setBorder(boolean border) {
        mLlEt.setBackgroundResource(border ? R.drawable.selector_form : R.drawable.selector_form_transparen);
        mEdContent.setPadding(border ? PhoneHelper.dip2px(mContext, 6) : 0, PhoneHelper.dip2px(mContext, 6), PhoneHelper.dip2px(mContext, 6), PhoneHelper.dip2px(mContext, 6));
        //块的高度
        setLineBlockHeight();
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
     * 设置标题的颜色
     *
     * @param titleColor
     */
    public void setTitleColor(int titleColor) {
        if (mTvName != null) {
            mTvName.setTextColor(titleColor);
        }
    }


    /**
     * 设置布局方向,0-水平  1-垂直
     */
    public void setLayoutOrientation(int orientation) {
        this.mOrientation = orientation;

        mRoot.setOrientation(orientation);
        ViewGroup.LayoutParams layoutParams = mView.getLayoutParams();

        if (orientation == LinearLayout.HORIZONTAL) {
            mTvName.setMaxEms(6);
            mTvName.setMinEms(4);
        } else {
            mTvName.setMaxEms(30);
            mTvName.setMinEms(4);

        }
        //块的高度
        setLineBlockHeight();
        requestLayout();
    }

    private void setLineBlockHeight() {
        ViewGroup.LayoutParams layoutParams = mView.getLayoutParams();
        layoutParams.height = mOrientation == LinearLayout.VERTICAL && border ? PhoneHelper.dip2px(mContext, 4) : 0;
    }

    public void setTitleGravity(int titleGravity) {
//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mLlTitle.getLayoutParams();
//        layoutParams.gravity = titleGravity;
        if (mTvName != null) {
            mTvName.setGravity(titleGravity);
        }
    }

    public void setTitleLayoutGravity(int titleLayoutGravity) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mLlTitle.getLayoutParams();
        layoutParams.gravity = titleLayoutGravity;
//        if (mRoot != null) {
//            mRoot.setGravity(titleLayoutGravity);
//        }
    }

    /**
     * 设置最长限制
     *
     * @param type
     */
    public void setInputType(int type) {
        if (type == -1) {
            return;
        }
        if (mEdContent != null) {
            mEdContent.setInputType(type);
        }
    }

    /**
     * 设置最长限制
     *
     * @param length
     */
    public void setMaxLength(int length) {
        if (length <= 0) {
            return;
        }
        if (mEdContent != null) {
            mEdContent.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(length)}); //限制输入4位
        }
    }

    public void setKeyListener(String digists) {
        mEdContent.setKeyListener(DigitsKeyListener.getInstance(digists));
    }

    /**
     * 点击键盘搜索的动作监听
     *
     * @param l
     */
    public void setOnEditorActionListener(TextView.OnEditorActionListener l) {
        if (mEdContent == null) {
            return;
        }
        mEdContent.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {

                if (l != null) {
                    l.onEditorAction(v, actionId, event);
                }

                return true;
            }
            return false;
        });
    }

    /**
     * 输入监听
     *
     * @param watcher
     */
    public void addTextChangedListener(TextWatcher watcher) {
        mEdContent.addTextChangedListener(watcher);
    }

    public void removeTextChangedListener(TextWatcher watcher) {
        mEdContent.removeTextChangedListener(watcher);
    }

    public void setGravity(int gravity) {
        if (mEdContent != null) {
            mEdContent.setGravity(gravity);
        }
    }

    public void setEnabled(boolean enabled) {
        this.enable = enabled;
        mEdContent.setEnabled(enabled);
        mLlEt.setEnabled(enabled);
    }


    /**
     * 右侧图标设置 , 0的话就不显示了
     *
     * @param resId 资源id
     */
    public void setRightIcon(@DrawableRes int resId) {
        if (mIvRight != null) {
            if (resId != 0) {
                mIvRight.setVisibility(VISIBLE);
                mIvRight.setImageResource(resId);
            } else {
                mIvRight.setVisibility(GONE);
            }
        }
    }

    /**
     * 右下角图标设置 , 0的话就不显示了
     *
     * @param resId 资源id
     */
    public void setRBIcon(@DrawableRes int resId) {
        if (mIvRB != null) {
            if (resId != 0) {
                mIvRB.setVisibility(VISIBLE);
                mIvRB.setImageResource(resId);
            } else {
                mIvRB.setVisibility(GONE);
            }
        }
    }


    /**
     * 是否是必填
     *
     * @param must
     */
    public void setIsMust(boolean must) {
        this.must = must;
        if (mTvMust != null) {
            mTvMust.setVisibility(must ? VISIBLE : GONE);
        }
    }

    /**
     * 是否是必填
     */
    public boolean getIsMust() {
        return must;
    }

    /**
     * 输入框的最小高度
     *
     * @param height
     */
    public void setInputMiniHeight(int height) {
        if (mEdContent != null) {
            mEdContent.setMinHeight(height);
        }
    }

    /**
     * 输入框的高度
     *
     * @param height
     */
    public void setInputHeight(int height) {
        if (mEdContent != null) {
            mEdContent.setHeight(height);
        }
    }

    /**
     * 设置提示
     *
     * @param hint
     */
    public void setHint(String hint) {
        this.hint = hint;
        if (mEdContent != null) {
            mEdContent.setHint(hint);
        }
    }

    /**
     * 获取提示
     */
    public String getHint() {
        if (mEdContent != null) {
            return mEdContent.getHint().toString();
        }
        return "";
    }

    /**
     * 设置标题名称
     *
     * @param title
     */
    public void setTitle(String title) {
        if (mTvName != null) {
            mTvName.setText(title);
        }
    }

    /**
     * 获取标题名称
     */
    public String getTitle() {
        if (mTvName != null) {
            return mTvName.getText().toString();
        }
        return "";
    }

    /**
     * 点击右侧图标
     *
     * @param l
     */
    public void setOnRightClick(View.OnClickListener l) {
        if (mIvRight != null) {
            mIvRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (l != null && enable) {
                        l.onClick(v);
                    }
                }
            });
        }
    }

    /**
     * 点击右下角图标
     *
     * @param l
     */
    public void setOnRBClick(View.OnClickListener l) {
        if (mIvRB != null) {
            mIvRB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (l != null && enable) {
                        l.onClick(v);
                    }
                }
            });
        }
    }


    /**
     * 设置内容
     *
     * @param inputText
     */
    public void setEditText(String inputText) {
        mEdContent.setText(inputText);
    }

    /**
     * 获取输入框内容
     *
     * @return
     */
    public String getValue() {
        String content = "";
        if (mEdContent != null) {
            content = mEdContent.getText().toString().trim();
        }
        return content;
    }

    public void hideKeyBoard() {

        KeyboardHelper.getInstance().hideKeyBoard(mEdContent);
    }

    public void openKeyBoard() {

        KeyboardHelper.getInstance().openKeyBoard(mEdContent);
    }


    /**
     * 重置
     */
    public void reset() {
        mEdContent.setText("");
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

    public boolean hasFocusIV() {
        return mEdContent != null && mEdContent.hasFocus();
    }

    public boolean isFocusedIV() {
        return mEdContent != null && mEdContent.isFocused();
    }


    /**
     * 设置焦点监听
     *
     * @param listener
     */
    public void onFocusChange(View.OnFocusChangeListener listener) {
        if (mEdContent != null) {
            mEdContent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    listener.onFocusChange(v, hasFocus);
                }
            });
        }
    }

    public EditText getEditText() {
        return mEdContent;
    }
}
