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

public class GuideNode3a extends AppCompatActivity {

    private FrameLayout touchInterceptor;
    Context context;
    FormDetails formDetails;
    Body body;
    String def1String,def2String,appealDateString,courtCostString, emailTo;
    private EditText df1name, df2name, appealDate, courtCost, emailET;
    String baseUrl = "https://80cc6744.ngrok.io/send_email";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_node3a);

        context = GuideNode3a.this;
        touchInterceptor = findViewById(R.id.touchInterceptor);
        df1name = findViewById(R.id.dfName1_et_gn3a);
        df2name = findViewById(R.id.dfName2_et_gn3a);
        appealDate = findViewById(R.id.appealDate_et_gn3a);
        courtCost = findViewById(R.id.courtCost_et_gn3a);
        emailET = findViewById(R.id.emailTo_et_gn3a);

        touchInterceptor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (df1name.isFocused()) {
                        Rect outRect = new Rect();
                        df1name.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                            df1name.clearFocus();
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                    if (df2name.isFocused()) {
                        Rect outRect = new Rect();
                        df2name.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                            df2name.clearFocus();
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                    if (appealDate.isFocused()) {
                        Rect outRect = new Rect();
                        appealDate.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                            appealDate.clearFocus();
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                    if (courtCost.isFocused()) {
                        Rect outRect = new Rect();
                        courtCost.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                            courtCost.clearFocus();
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                }
                return false;
            }
        });




    }
    public void pressSubmit(View v){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Submit Form?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(context, "Form Submitted!", Toast.LENGTH_SHORT).show();
                        sendFormDetails();
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
    public void getFormDetails(){
        def1String = df1name.getText().toString();
        def2String = df2name.getText().toString();
        appealDateString = appealDate.getText().toString();
        courtCostString = courtCost.getText().toString();
        emailTo = emailET.getText().toString();
    }
    public void sendFormDetails(){
        getFormDetails();
        formDetails = new FormDetails();
        formDetails.setDefendant1Name(def1String);
        formDetails.setDefendant2Name(def2String);
        formDetails.setAppealDate(appealDateString);
        formDetails.setCourtCostsPaid(courtCostString);

        body = new Body();
        body.setForm(formDetails);
        body.setRecords(Transcript.getRecord().toArray(new Record[Transcript.getRecord().size()]));
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
            Transcript.clearList();
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

    public void pressBackButton(View v){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
    }

}
