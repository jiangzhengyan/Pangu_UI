package com.smart.pangu;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {


    @Bind(R.id.btn_pangu_input_view)
    Button mBtnPanguInputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.btn_pangu_input_view)
    public void onViewClicked() {
        PanguInputViewActivity.start(this);
    }
}