package com.smart.pangu_ui_lib.entity;

import com.smart.pangu_ui_lib.widget.wheelview.interfaces.PickerViewData;

import java.util.ArrayList;

/**
 * 本类的主要功能是 :   选项
 *
 * @author jiangzhengyan  2024/4/10 9:25
 */
public class SelectItem implements PickerViewData {

    private String id;
    private String type;
    private String name;
    private String inputContent;
    private String inputHint;
    private int imgResources;
    private boolean isCheckedDefault;
    private boolean isShowInput;
    private String value;
    private ArrayList<SelectItem> subItem;

    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public SelectItem() {
    }

    public SelectItem(String name) {
        this.name = name;
    }

    public SelectItem(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public SelectItem(String id, String name, boolean isCheckedDefault) {
        this.id = id;
        this.name = name;
        this.isCheckedDefault = isCheckedDefault;
    }


    public SelectItem(String id, String name, boolean isCheckedDefault, boolean isShowInput, String inputContent) {
        this.id = id;
        this.name = name;
        this.isCheckedDefault = isCheckedDefault;
        this.isShowInput = isShowInput;
        this.inputContent = inputContent;
    }

    public SelectItem(String id, String name, boolean isCheckedDefault, boolean isShowInput, String inputContent, String inputHint) {
        this.id = id;
        this.name = name;
        this.isCheckedDefault = isCheckedDefault;
        this.isShowInput = isShowInput;
        this.inputContent = inputContent;
        this.inputHint = inputHint;
    }

    public ArrayList<SelectItem> getSubItem() {
        if (subItem == null) {
            subItem = new ArrayList<>();
        }
        return subItem;
    }

    public void setSubItem(ArrayList<SelectItem> subItem) {
        this.subItem = subItem;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getInputHint() {
        return inputHint;
    }

    public void setInputHint(String inputHint) {
        this.inputHint = inputHint;
    }

    public String getInputContent() {
        return inputContent;
    }

    public void setInputContent(String inputContent) {
        this.inputContent = inputContent;
    }

    public boolean isShowInput() {
        return isShowInput;
    }

    public void setShowInput(boolean showInput) {
        isShowInput = showInput;
    }


    public boolean isCheckedDefault() {
        return isCheckedDefault;
    }

    public void setCheckedDefault(boolean checkedDefault) {
        isCheckedDefault = checkedDefault;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        if (name == null) {
            name = "";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getImgResources() {
        return imgResources;
    }

    public void setImgResources(int imgResources) {
        this.imgResources = imgResources;
    }


    @Override
    public String getPickerViewText() {
        return name;
    }

}
