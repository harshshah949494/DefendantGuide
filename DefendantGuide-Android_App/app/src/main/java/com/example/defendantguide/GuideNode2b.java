package com.example.defendantguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GuideNode2b extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_node2b);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
    }

    public void pressBackButton(View v){
        onBackPressed();
    }
}
