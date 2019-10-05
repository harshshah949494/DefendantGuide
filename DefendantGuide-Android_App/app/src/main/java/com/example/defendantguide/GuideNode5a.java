package com.example.defendantguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GuideNode5a extends AppCompatActivity {
    int request_Code = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_node5a);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == request_Code) {
            if (resultCode == RESULT_OK) {
                // OR
                // String returnedResult = data.getDataString();
                setResult(RESULT_OK);
                finish();
            }
        }
    }
    public void onHomePressed(View v){
        setResult(RESULT_OK);
        finish();
    }
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
    }
    public void pressBackButton(View v){
        onBackPressed();
    }
}
