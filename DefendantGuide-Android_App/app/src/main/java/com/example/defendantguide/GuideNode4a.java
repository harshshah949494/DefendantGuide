package com.example.defendantguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class GuideNode4a extends AppCompatActivity {
    RadioGroup radioGroup_gn4a;
    int request_Code = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_node4a);
        radioGroup_gn4a = findViewById(R.id.radio_group_gn4a);
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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
    }

    public void pressBackButton(View v){
        onBackPressed();
    }

    public void nextGN4a(View v){
        int radioButtonID = radioGroup_gn4a.getCheckedRadioButtonId();
        Intent i;
        if(radioButtonID == R.id.gn4a_op1){
            i = new Intent(this, GuideNode5a.class);
            startActivityForResult(i,request_Code);
            overridePendingTransition  (R.anim.right_slide_in, R.anim.left_slide_out);
        }

        else if(radioButtonID == R.id.gn4a_op2){
            i = new Intent(this, GuideNode5b.class);
            startActivityForResult(i,request_Code);
            overridePendingTransition  (R.anim.right_slide_in, R.anim.left_slide_out);
        }

        else{
            Toast.makeText(this, "Please select an option to proceed!", Toast.LENGTH_SHORT).show();
        }
    }
}
