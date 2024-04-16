package com.smart.pangu;

import com.smart.pangu_ui_lib.entity.SelectItem;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {

    public static List<SelectItem> getSelectItems() {
        ArrayList<SelectItem> selectItems = new ArrayList<>();
        selectItems.add(new SelectItem("0", "PanguUI框架"));
        SelectItem android = new SelectItem("1", "Android");
        android.setShowInput(true);
        android.setInputContent("这是安卓....");
        selectItems.add(android);
        selectItems.add(new SelectItem("2", "Java"));
        selectItems.add(new SelectItem("3", "Php"));
        selectItems.add(new SelectItem("4", ".net",true));
        selectItems.add(new SelectItem("5", "H5"));
        selectItems.add(new SelectItem("6", "C++"));
        selectItems.add(new SelectItem("7", "JavaScript"));
        selectItems.add(new SelectItem("8", "Python"));
        return selectItems;
    }
}
