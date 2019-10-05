package com.example.defendantguide;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class GuideNode2a extends AppCompatActivity {
    RadioGroup radioGroup_gn2a;
    Record record;
    TextView qn;
    int request_Code = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_node2a);
        qn = findViewById(R.id.guide_node_2a_tv);
        radioGroup_gn2a = findViewById(R.id.radio_group_gn2a);
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
    }
    public void pressBackButton(View v){
        onBackPressed();
    }

    public void nextGN2a(View v){
        int radioButtonID = radioGroup_gn2a.getCheckedRadioButtonId();
        Intent i;
        record = new Record();
        RadioButton rb = findViewById(radioButtonID);
        record.setApp1(qn.getText().toString());
        record.setUser1(rb.getText().toString());
        Transcript.addRecord(record);

        List<Record> rrr = Transcript.getRecord();
        for(int j=0;j<rrr.size();j++){
            Log.d("ttest",Transcript.getRecord().get(j).getApp1()+", "+Transcript.getRecord().get(j).getUser1());
        }

        if(radioButtonID == R.id.gn2a_op1){
            i = new Intent(this, GuideNode3a.class);
            startActivityForResult(i,request_Code);
            overridePendingTransition  (R.anim.right_slide_in, R.anim.left_slide_out);
        }

        else if(radioButtonID == R.id.gn2a_op2){
            i = new Intent(this, GuideNode3b.class);
            startActivityForResult(i,request_Code);
            overridePendingTransition  (R.anim.right_slide_in, R.anim.left_slide_out);
        }
        else if(radioButtonID == R.id.gn2a_op3){
            i = new Intent(this, GuideNode3c.class);
            startActivityForResult(i,request_Code);
            overridePendingTransition  (R.anim.right_slide_in, R.anim.left_slide_out);
        }
        else{
            Toast.makeText(this, "Please select an option to proceed!", Toast.LENGTH_SHORT).show();
        }

    }
}
