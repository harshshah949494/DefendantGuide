package com.example.defendantguide;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private Button mButton;
    private EditText mEditText;
    private Button guideButton;
    Context context;
    Map<String,String> map;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_search);
                    mEditText.setVisibility(View.VISIBLE);
                    mButton.setVisibility(View.VISIBLE);
                    guideButton.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    mEditText.setVisibility(View.INVISIBLE);
                    guideButton.setVisibility(View.VISIBLE);
                    mButton.setVisibility(View.GONE);

                    return true;
            }
            return false;
        }
    };


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = MainActivity.this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        mButton = findViewById(R.id.search_button);
        guideButton = findViewById(R.id.guide_button);
        mEditText = findViewById(R.id.search_et);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        map = new HashMap<>();
        map.put("Legal Custody".toLowerCase(),"The parent(s) or person(s) who make the major decisions in the child's life, (such as decisions about health/healthcare, education, and religious upbringing) have \" legal custody \"");
        map.put("Notice of Lease Violation".toLowerCase(),"The Notice of Lease Violation (also known as a \"Cure or Quit Notice\") warns the tenants that they need to correct the listed violation(s) in order to bring their lease back into good standing. It is a serious looking document that identifies the tenants with social security numbers, the premises and the violation(s). Don't hesitate to notify your tenants that they are in violation of their agreement. Small problems can turn into big ones if we don't tend to them early enough.");
        map.put("defendant","A person in a court of law who is accused of having done something wrong");

        // Touch Interceptor to lose focus from Edit Text
        FrameLayout touchInterceptor = findViewById(R.id.touchInterceptor);
        touchInterceptor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (mEditText.isFocused()) {
                        Rect outRect = new Rect();
                        mEditText.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                            mEditText.clearFocus();
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                }
                return false;
            }

        });

    }

    public void pressSearch(View v){
        String key = mEditText.getText().toString().trim().toLowerCase();
        android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(context);
        if(map.get(key)==null){
            builder1.setMessage("Nothing found");
        }
        else {
            builder1.setMessage(map.get(key));
        }
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Toast.makeText(context, "Form Submitted!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });



        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void startGuide(View v){
        Intent i = new Intent(this, GuideNode1.class);
        startActivity(i);
        overridePendingTransition  (R.anim.right_slide_in, R.anim.left_slide_out);
    }

}
