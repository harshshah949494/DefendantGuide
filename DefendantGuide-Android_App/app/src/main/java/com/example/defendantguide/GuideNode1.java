package com.example.defendantguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class GuideNode1 extends AppCompatActivity {
    RadioGroup radioGroup_gn1;
    Record record;
    TextView qn;
    int request_Code = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_node1);

        qn = findViewById(R.id.guide_node_1_tv);
        radioGroup_gn1 = findViewById(R.id.radio_group_gn1);
        record = new Record();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
    }


    public void nextGN1(View v){
        int radioButtonID = radioGroup_gn1.getCheckedRadioButtonId();
        record.setApp1(qn.getText().toString());
        RadioButton rb = findViewById(radioButtonID);
        record.setUser1(rb.getText().toString());

        Transcript.addRecord(record);
        Intent i;
        if(radioButtonID == R.id.gn1_op4){
            i = new Intent(this, GuideNode2b.class);
            startActivity(i);
            overridePendingTransition  (R.anim.right_slide_in, R.anim.left_slide_out);
        }
        else if(radioButtonID == -1){
            Toast.makeText(this, "Please select an option to proceed!", Toast.LENGTH_SHORT).show();
        }
        else{
            i = new Intent(this, GuideNode2a.class);
            startActivityForResult(i,request_Code);
            overridePendingTransition  (R.anim.right_slide_in, R.anim.left_slide_out);
        }

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == request_Code) {
            if (resultCode == RESULT_OK) {
                // OR
                // String returnedResult = data.getDataString();
                finish();
            }
        }
    }
}
