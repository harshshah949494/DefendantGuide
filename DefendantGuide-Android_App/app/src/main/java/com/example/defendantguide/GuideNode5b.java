package com.example.defendantguide;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class GuideNode5b extends AppCompatActivity {
    Context context;
    private FrameLayout touchInterceptor;
    CheckList checkList;
    Body body;
    String ev1,ev2,ev3,w1,w2,w3,emailTo;
    private EditText evi1,evi2,evi3,wit1,wit2,wit3,emailToET;
    String baseUrl = "https://80cc6744.ngrok.io/send_email";
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = GuideNode5b.this;
        setContentView(R.layout.activity_guide_node5b);


        touchInterceptor = findViewById(R.id.touchInterceptor);
        evi1 = findViewById(R.id.ev1_et_gn5b);
        evi2 = findViewById(R.id.ev2_et_gn5b);
        evi3 = findViewById(R.id.ev3_et_gn5b);
        wit1 = findViewById(R.id.wit1_et_gn5b);
        wit2 = findViewById(R.id.wit2_et_gn5b);
        wit3 = findViewById(R.id.wit3_et_gn5b);
        emailToET = findViewById(R.id.emailTo_et_gn5b);

        touchInterceptor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (evi1.isFocused()) {
                        Rect outRect = new Rect();
                        evi1.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                            evi1.clearFocus();
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                    if (evi2.isFocused()) {
                        Rect outRect = new Rect();
                        evi2.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                            evi2.clearFocus();
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                    if (evi3.isFocused()) {
                        Rect outRect = new Rect();
                        evi3.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                            evi3.clearFocus();
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                    if (wit1.isFocused()) {
                        Rect outRect = new Rect();
                        wit1.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                            wit1.clearFocus();
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                    if (wit2.isFocused()) {
                        Rect outRect = new Rect();
                        wit2.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                            wit2.clearFocus();
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                    if (wit3.isFocused()) {
                        Rect outRect = new Rect();
                        wit3.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                            wit3.clearFocus();
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                }
                return false;
            }
        });



    }
    public void getCheckListDetails(){
        ev1 = evi1.getText().toString();
        ev2 = evi2.getText().toString();
        ev3 = evi3.getText().toString();
        w1 = wit1.getText().toString();
        w2 = wit2.getText().toString();
        w3 = wit3.getText().toString();
        emailTo = emailToET.getText().toString();
    }
    public void sendCheckListDetails(){
        getCheckListDetails();
        checkList = new CheckList();
        String[] evs = {ev1,ev2,ev3};
        checkList.setEvidence(evs);
        String[] wits = {w1,w2,w3};
        checkList.setWitness(wits);
        body = new Body();
        body.setChecklist(checkList);
        body.setTo(emailTo);
        // Post request
        if(checkNetworkConnection())
            new HTTPAsyncTask().execute(baseUrl);
        else
            Toast.makeText(this, "Not Connected!", Toast.LENGTH_SHORT).show();
    }

    // check network connection
    public boolean checkNetworkConnection() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean isConnected;


        isConnected = networkInfo.isConnected();
        return isConnected;
    }
    private String httpPost(String myUrl) throws IOException, JSONException {
        String result = "";

        URL url = new URL(myUrl);

        // 1. create HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json;");
        // 2. build JSON object
        //JSONObject jsonObject = buidJsonObject();
        Gson gson = new Gson();
        String jsonString = gson.toJson(body);
        Log.d("ttJSON", jsonString);

        // 3. add JSON content to POST request body
        setPostRequestContent(conn, jsonString);

        // 4. make POST request to the given URL
        conn.connect();

        // 5. return response message
        return conn.getResponseMessage()+"";

    }

    private class HTTPAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            // params comes from the execute() call: params[0] is the url.
            try {
                try {
                    Log.d("ttbody:",urls[0]);
                    return httpPost(urls[0]);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return "Error!";
                }
            } catch (IOException e) {
                Log.d("tterror:",e.toString());
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(context, "Email Delivery: "+result, Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }
    }
    private void setPostRequestContent(HttpURLConnection conn, String jsonString) throws IOException {

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(jsonString);

        writer.flush();
        writer.close();
        os.close();
    }






    //

    public void pressSubmit(View v){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Submit Form?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(context, "Form Submitted!", Toast.LENGTH_SHORT).show();
                        sendCheckListDetails();
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
    }
    public void pressBackButton(View v){
        onBackPressed();
    }
}
