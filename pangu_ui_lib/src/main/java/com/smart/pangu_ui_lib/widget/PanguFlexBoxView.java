package com.smart.pangu_ui_lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.smart.pangu_ui_lib.R;
import com.smart.pangu_ui_lib.base.BaseView;
import com.smart.pangu_ui_lib.config.PanguFlexSelectMode;
import com.smart.pangu_ui_lib.entity.SelectItem;
import com.smart.pangu_ui_lib.impl.TextWatcherListener;
import com.smart.pangu_ui_lib.util.PhoneHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * 本类的主要功能是 :   自定义flexbox
 *
 * @author jiangzhengyan  2024/4/10 9:18
 */
public class PanguFlexBoxView extends BaseView {


    private FlexboxLayout mFlexRoot;
    private boolean enable = true;
    /**
     * The current value of the {@link FlexWrap}, the default value is {@link FlexWrap#NOWRAP}.
     *
     * @see FlexWrap
     */
    private int mFlexWrap;
    private int margin;//一般设置为6dp
    private int mSelectMode;// 0,单选; 1,多选;2,最多选择一个;3,至少选择一个; 默认多选
    private int mFlexDirection;

    @Override
    protected int getLayoutId() {
        return R.layout.pangu_flex_box;
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PanguFlexBoxView, defStyleAttr, 0);
        enable = typedArray.getBoolean(R.styleable.PanguFlexBoxView_flex_enable, true);
        //nowrap ：不换行
        //wrap：按正常方向换行
        //wrap_reverse：按反方向换行
        mFlexWrap = typedArray.getInt(R.styleable.PanguFlexBoxView_flex_wrap, FlexWrap.WRAP);
        //方向
        mFlexDirection = typedArray.getInt(R.styleable.PanguFlexBoxView_flex_direction, FlexDirection.ROW);
        margin = typedArray.getLayoutDimension(R.styleable.PanguFlexBoxView_flex_item_margin, PhoneHelper.dip2px(context, 6));
        // 0,单选; 1,多选;2,最多选择一个
        mSelectMode = typedArray.getInt(R.styleable.PanguFlexBoxView_flex_select_mode, PanguFlexSelectMode.MULTIPLE);
        //重心
        int gravity = typedArray.getInt(R.styleable.PanguFlexBoxView_flex_item_text_gravity, -1);
        setItemTextGravity(gravity);
        typedArray.recycle();
        init();
    }

    public PanguFlexBoxView(Context context) {
        super(context);
    }

    public PanguFlexBoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {
        mFlexRoot = findViewById(R.id.flex_root);
        //是否可选中
        setEnable(enable);
        setFlexWrap(mFlexWrap);
        //item间距
        setItemMargin(margin);
        //方向
        setFlexDirection(mFlexDirection);
    }

    /**
     * item文本的对齐方式
     *
     * @param gravity
     * @attr ref android.R.styleable#TextView_gravity
     * @see android.view.Gravity
     */
    public void setItemTextGravity(int gravity) {
        if (gravity == -1) {
            return;
        }

        for (int i = 0; i < mFlexRoot.getChildCount(); i++) {
            LinearLayout childAt = (LinearLayout) mFlexRoot.getChildAt(i);
            CheckBox cb = (CheckBox) childAt.getChildAt(0);
            cb.setGravity(gravity);
        }
    }

    /**
     * 展示某item下的输入框
     *
     * @param index  索引
     * @param isShow 是否展示
     */
    public void showInputAt(int index, boolean isShow) {
        if (index == -1) {
            return;
        }
        if (mFlexRoot == null) {
            return;
        }
        if (index >= mFlexRoot.getChildCount()) {
            return;
        }
        LinearLayout childAt = (LinearLayout) mFlexRoot.getChildAt(index);
        if (childAt == null) {
            return;
        }
        PanguInputView civi = (PanguInputView) childAt.getChildAt(1);
        if (civi == null) {
            return;
        }
        civi.setVisibility(isShow ? VISIBLE : GONE);

    }


    /**
     * 设置选中模式{@link PanguFlexSelectMode}
     *
     * @param flexSelectMode
     */
    public void setSelectMode(@PanguFlexSelectMode int flexSelectMode) {
        mSelectMode = flexSelectMode;
    }

    /**
     * 设置item间距
     *
     * @param margin
     */
    public void setItemMargin(int margin) {

        if (mFlexRoot != null) {
            int childCount = mFlexRoot.getChildCount();
            for (int i = 0; i < childCount; i++) {
                LinearLayout childAt = (LinearLayout) mFlexRoot.getChildAt(i);
                childAt.setPadding(margin, margin, margin, margin);
                childAt.requestLayout();
            }
        }
    }

    /**
     * //        nowrap ：不换行
     * //        wrap：按正常方向换行
     * //        wrap_reverse：按反方向换行
     *
     * @param flexWrap
     */
    public void setFlexWrap(@FlexWrap int flexWrap) {

        if (mFlexRoot != null) {
            mFlexRoot.setFlexWrap(flexWrap);
        }
    }

    /**
     * 方向
     *
     * @param flexDirecti
     */
    public void setFlexDirection(@FlexDirection int flexDirecti) {

        if (mFlexRoot != null) {
            mFlexRoot.setFlexDirection(flexDirecti);
        }
    }

    /**
     * 是否可选中
     *
     * @param enable
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
        if (mFlexRoot != null) {
            int childCount = mFlexRoot.getChildCount();
            for (int i = 0; i < childCount; i++) {
                LinearLayout childAt = (LinearLayout) mFlexRoot.getChildAt(i);
                CheckBox childAt1 = (CheckBox) childAt.getChildAt(0);
                childAt1.setEnabled(enable);
            }
        }
    }

    //******************Text************************//

    /**
     * 展示普通文本item,可自定义item布局,默认 R.layout.item_flex_box_text
     *
     * @param list
     */
    public void setDataText(List<SelectItem> list, OnItemClickListener listener) {
        setDataText(list, R.layout.item_flex_box_text, listener);
    }

    /**
     * 展示普通文本item,可自定义item布局,默认 R.layout.item_flex_box_text
     *
     * @param list
     */
    public void setDataText(List<SelectItem> list, int itemLayoutId, OnItemClickListener listener) {
        mFlexRoot.removeAllViews();
        if (list == null) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            SelectItem selectItem = list.get(i);
            String id = selectItem.getId();
            String name = selectItem.getName();
            View view = View.inflate(getContext(), itemLayoutId, null);

            TextView tvItem = view.findViewById(R.id.tv_item);
            tvItem.setText(name);

            int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(selectItem, finalI);
                    }
                }
            });
            mFlexRoot.addView(view);
        }
    }
    //******************CheckBox************************//

    /**
     * 设置checkBox样式的item数据,监听可以单独调取{@link #setOnItemCheckedListener(OnItemCheckedListener)}
     *
     * @param list 数据
     */
    public void setDataCb(List<SelectItem> list) {
        setDataCb(list, R.layout.item_flex_box_cb);
    }

    /**
     * 设置checkBox样式的item数据 带监听
     *
     * @param list
     * @param listener
     */
    public void setDataCb(List<SelectItem> list, OnItemCheckedListener listener) {
        this.itemCheckListener = listener;
        setDataCb(list, R.layout.item_flex_box_cb);
    }

    /**
     * 设置checkBox样式的item数据 带监听  默认item item_flex_box_cb
     *
     * @param list
     * @param listener
     */
    public void setDataCb(List<SelectItem> list, OnItemCheckedListener listener, TextWatcherListener textWatcher) {
        this.itemCheckListener = listener;
        this.textWatcher = textWatcher;
        setDataCb(list, R.layout.item_flex_box_cb);
    }

    /**
     * 设置checkBox样式的item数据,监听可以单独调取{@link #setOnItemCheckedListener(OnItemCheckedListener)}
     *
     * @param list         数据
     * @param itemLayoutId 布局
     */
    public void setDataCb(List<SelectItem> list, int itemLayoutId) {
        mFlexRoot.removeAllViews();
        if (list == null) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            SelectItem selectItem = list.get(i);
            String id = selectItem.getId();
            String name = selectItem.getName();
            boolean checked = selectItem.isCheckedDefault();
            boolean showInput = selectItem.isShowInput();
            String inputContent = selectItem.getInputContent();
            String inputHint = selectItem.getInputHint();


            LinearLayout view = (LinearLayout) View.inflate(getContext(), itemLayoutId, null);
            view.setTag(id);
            view.setPadding(margin, margin, margin, margin);
            view.requestLayout();
            CheckBox childCb = (CheckBox) view.getChildAt(0);
            PanguInputView childCiv = (PanguInputView) view.getChildAt(1);
            childCb.setChecked(checked);
            childCb.setEnabled(enable);
            childCb.setText(name);
            int finalI = i;

            childCiv.setVisibility(checked && showInput ? VISIBLE : GONE);
            //输入框的监听
            childCiv.setEditText(inputContent);
            childCiv.setHint(inputHint);
            childCiv.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    if (textWatcher != null) {
                        textWatcher.beforeTextChanged(s, start, count, after);
                    }


                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (textWatcher != null) {
                        textWatcher.onTextChanged(s, start, before, count);
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                    if (textWatcher != null) {
                        textWatcher.afterTextChanged(s);
                        textWatcher.afterTextChanged(s, selectItem, finalI, childCb.isChecked());
                    }
                }
            });


            childCb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //先相应checke,再响应点击
                    //点击后的状态
                    boolean isChecked = childCb.isChecked();

                    if (mSelectMode == PanguFlexSelectMode.SINGLE) {
                        for (int j = 0; j < list.size(); j++) {
                            LinearLayout childAt = (LinearLayout) mFlexRoot.getChildAt(j);
                            CheckBox child = (CheckBox) childAt.getChildAt(0);
                            child.setChecked(finalI == j);
                        }
                    }
                    //最多只能选中一个
                    if (mSelectMode == PanguFlexSelectMode.ONE_MOST) {

                        for (int j = 0; j < list.size(); j++) {
                            LinearLayout childAt = (LinearLayout) mFlexRoot.getChildAt(j);
                            CheckBox child = (CheckBox) childAt.getChildAt(0);
                            if (isChecked) {
                                if (finalI != j) {
                                    child.setChecked(false);
                                }
                            }
                        }
                    }
                    //至少选中一个
                    if (mSelectMode == PanguFlexSelectMode.ONE_LEAST) {
                        //之前是选中状态,剩余有选中的,此按钮正常变为为选中;剩余没有选中的,此按钮不变状态,即为选中
                        //之前是未选中状态,正常选中
                        boolean isOtherChecked = false;
                        for (int j = 0; j < list.size(); j++) {
                            LinearLayout childAt = (LinearLayout) mFlexRoot.getChildAt(j);
                            CheckBox child = (CheckBox) childAt.getChildAt(0);
                            if (finalI != j && child.isChecked()) {
                                //至少有别的一个是选中的
                                isOtherChecked = true;
                                break;
                            }
                        }
                        if (!isChecked) {
                            if (!isOtherChecked) {
                                childCb.setChecked(true);
                            }
                        }

                    }
                    //输入框的处理
                    for (int j = 0; j < list.size(); j++) {
                        LinearLayout childAt = (LinearLayout) mFlexRoot.getChildAt(j);
                        CheckBox child = (CheckBox) childAt.getChildAt(0);
                        PanguInputView childCiv = (PanguInputView) childAt.getChildAt(1);
                        childCiv.setVisibility(child.isChecked() && list.get(j).isShowInput() ? VISIBLE : GONE);
                    }
                    //最后的状态
                    if (itemCheckListener != null) {
                        itemCheckListener.onItemCheck(selectItem, finalI, childCb.isChecked());
                    }

                }
            });
            mFlexRoot.addView(view);
        }
    }


    //******************RadioButton************************//

    /**
     * 设置RadioButton样式的item数据,带监听
     *
     * @param list
     * @param listener
     * @param textWatcher
     */
    public void setDataRb(List<SelectItem> list, OnItemCheckedListener listener, TextWatcherListener textWatcher) {
        this.itemCheckListener = listener;
        this.textWatcher = textWatcher;
        setDataRb(list, R.layout.item_flex_box_rb);
    }

    /**
     * 设置RadioButton样式的item数据,带监听
     *
     * @param list
     * @param listener
     */
    public void setDataRb(List<SelectItem> list, OnItemCheckedListener listener) {
        this.itemCheckListener = listener;
        setDataRb(list, R.layout.item_flex_box_rb);
    }

    /**
     * 设置RadioButton样式的item数据
     *
     * @param list
     */
    public void setDataRb(List<SelectItem> list) {
        setDataRb(list, R.layout.item_flex_box_rb);
    }

    /**
     * 设置RadioButton样式的item数据,监听可以单独调取{@link #setOnItemCheckedListener(OnItemCheckedListener)}
     *
     * @param list
     * @param itemLayoutId
     */
    public void setDataRb(List<SelectItem> list, int itemLayoutId) {
        mFlexRoot.removeAllViews();
        if (list == null) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            SelectItem selectItem = list.get(i);
            String id = selectItem.getId();
            String name = selectItem.getName();
            boolean checked = selectItem.isCheckedDefault();
            boolean showInput = selectItem.isShowInput();
            String inputContent = selectItem.getInputContent();
            String inputHint = selectItem.getInputHint();


            LinearLayout view = (LinearLayout) View.inflate(getContext(), itemLayoutId, null);
            view.setTag(id);
            view.setPadding(margin, margin, margin, margin);
            view.requestLayout();
            RadioButton childCb = (RadioButton) view.getChildAt(0);
            PanguInputView childCiv = (PanguInputView) view.getChildAt(1);
            childCb.setChecked(checked);
            childCb.setEnabled(enable);
            childCb.setText(name);
            int finalI = i;

            childCiv.setVisibility(checked && showInput ? VISIBLE : GONE);
            //输入框的监听
            childCiv.setEditText(inputContent);
            childCiv.setHint(inputHint);
            childCiv.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    if (textWatcher != null) {
                        textWatcher.beforeTextChanged(s, start, count, after);
                    }

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (textWatcher != null) {
                        textWatcher.onTextChanged(s, start, before, count);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                    if (textWatcher != null) {
                        textWatcher.afterTextChanged(s);
                        textWatcher.afterTextChanged(s, selectItem, finalI, childCb.isChecked());
                    }
                }
            });


            childCb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //先相应checke,再响应点击
                    //点击后获取大到原来的状态
                    boolean isChecked = selectItem.isCheckedDefault();

                    if (mSelectMode == PanguFlexSelectMode.SINGLE) {
                        for (int j = 0; j < list.size(); j++) {
                            LinearLayout childAt = (LinearLayout) mFlexRoot.getChildAt(j);
                            RadioButton child = (RadioButton) childAt.getChildAt(0);
                            child.setChecked(finalI == j);
                            selectItem.setCheckedDefault(finalI == j);
                        }
                    }
                    //最多只能选中一个
                    if (mSelectMode == PanguFlexSelectMode.ONE_MOST) {
                        //原来选中了
                        //取消当前选中

                        //原来未选中
                        //当前选中,取消其他
                        if (isChecked) {
                            childCb.setChecked(false);
                            selectItem.setCheckedDefault(false);
                        } else {
                            for (int j = 0; j < list.size(); j++) {
                                LinearLayout childAt = (LinearLayout) mFlexRoot.getChildAt(j);
                                RadioButton child = (RadioButton) childAt.getChildAt(0);

                                child.setChecked(finalI == j);
                                list.get(j).setCheckedDefault(finalI == j);
                            }
                        }
                    }
                    //至少选中一个
                    if (mSelectMode == PanguFlexSelectMode.ONE_LEAST) {
                        //之前是选中状态,
                        // 1,剩余有选中的,此按钮变为未选中;
                        // 2,剩余没有选中的,此按钮不变状态,即为选中
                        //之前是未选中状态,正常选中
                        boolean isOtherChecked = false;
                        for (int j = 0; j < list.size(); j++) {
                            LinearLayout childAt = (LinearLayout) mFlexRoot.getChildAt(j);
                            RadioButton child = (RadioButton) childAt.getChildAt(0);
                            if (finalI != j && child.isChecked()) {
                                //至少有别的一个是选中的
                                isOtherChecked = true;
                                break;
                            }
                        }
                        if (isChecked) {
                            if (isOtherChecked) {
                                childCb.setChecked(false);
                                selectItem.setCheckedDefault(false);
                            }
                        } else {
                            childCb.setChecked(true);
                            selectItem.setCheckedDefault(true);
                        }
                    }
                    //多选
                    if (mSelectMode == PanguFlexSelectMode.MULTIPLE) {
                        childCb.setChecked(!isChecked);
                        selectItem.setCheckedDefault(!isChecked);
                    }

                    //输入框的处理
                    for (int j = 0; j < list.size(); j++) {
                        LinearLayout childAt = (LinearLayout) mFlexRoot.getChildAt(j);
                        RadioButton child = (RadioButton) childAt.getChildAt(0);
                        PanguInputView childCiv = (PanguInputView) childAt.getChildAt(1);
                        childCiv.setVisibility(list.get(j).isCheckedDefault() && list.get(j).isShowInput() ? VISIBLE : GONE);
                    }
                    //最后的状态
                    if (itemCheckListener != null) {
                        itemCheckListener.onItemCheck(selectItem, finalI, childCb.isChecked());
                    }

                }
            });
            mFlexRoot.addView(view);
        }
    }

    /**
     * 获取item的总条数
     *
     * @return num
     */
    public int getFlexItemCount() {
        return mFlexRoot.getFlexItemCount();
    }

    /**
     * 获取已选中的item
     *
     * @return int
     */
    public List<SelectItem> getCheckedItem() {
        List<SelectItem> items = new ArrayList<>();
        for (int i = 0; i < mFlexRoot.getFlexItemCount(); i++) {
            LinearLayout view = (LinearLayout) mFlexRoot.getChildAt(i);
            int childCount = view.getChildCount();
            String id = view.getTag() == null ? "" : String.valueOf(view.getTag());
            String name = null;
            boolean checked = false;
            if (childCount >= 2) {
                if (view.getChildAt(0) instanceof RadioButton) {
                    RadioButton childCb = (RadioButton) view.getChildAt(0);
                    checked = childCb.isChecked();
                    name = childCb.getText().toString();
                }

                if (view.getChildAt(0) instanceof CheckBox) {
                    CheckBox childCb = (CheckBox) view.getChildAt(0);
                    checked = childCb.isChecked();
                    name = childCb.getText().toString();
                }

                PanguInputView childCiv = (PanguInputView) view.getChildAt(1);
                if (checked) {
                    SelectItem selectItem = new SelectItem(id, name, true);
                    selectItem.setInputContent(childCiv.getValue());
                    items.add(selectItem);
                }
            }
        }
        return items;
    }

    private OnItemCheckedListener itemCheckListener;
    private TextWatcherListener textWatcher;

    public void setOnItemCheckedListener(OnItemCheckedListener itemCheckListener) {
        this.itemCheckListener = itemCheckListener;
    }

    public void addTextChangedListener(TextWatcherListener watcher) {
        this.textWatcher = watcher;
    }


    public interface OnItemCheckedListener {

        void onItemCheck(SelectItem item, int position, boolean currentItemChecked);
    }

    public interface OnItemClickListener {

        void onItemClick(SelectItem item, int position);
    }

}
