package com.smart.pangu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.smart.pangu_ui_lib.widget.PanguInputView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 本类的主要功能是 :   盘古输入框
 *
 * @author jiangzhengyan  2024/4/9 15:57
 */
public class PanguInputViewActivity extends AppCompatActivity {

    @Bind(R.id.piv_1)
    PanguInputView mPiv1;
    @Bind(R.id.piv_3)
    PanguInputView mPiv3;

    public static void start(Context context) {
        Intent intent = new Intent(context, PanguInputViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pangu_input_view);
        ButterKnife.bind(this);
        mPiv1.setOnRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PanguInputViewActivity.this, "点击", Toast.LENGTH_SHORT).show();
            }
        });
        mPiv3.setOnRBClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PanguInputViewActivity.this, "点击", Toast.LENGTH_SHORT).show();
            }
        });
    }

}