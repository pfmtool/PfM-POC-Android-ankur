package com.blk.PfM_Demo;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class PortfolioDetailActivity extends Activity {
    public static String SERVER_ADDR = "https://gcmdemobackend.appspot.com/";
    private Button openPositionButton;
    private EditText portfolioName;

    String portfolioString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portfolio_detail_activity);
        openPositionButton = (Button) findViewById(R.id.openPositionButton);
        portfolioName = (EditText)findViewById(R.id.portfolioName);

        openPositionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Void, String>() {

                    @Override
                    // Get history and upload it to the server.
                    protected String doInBackground(Void... arg0) {


                        // Upload the history of all entries using upload().
                        String uploadState = "";
                        //try {
                           // initialize();
                        Intent myIntent = new Intent(PortfolioDetailActivity.this, LoginActivity.class);
                        // myIntent.putExtra("key", value); //Optional parameters
                        PortfolioDetailActivity.this.startActivity(myIntent);
                           /* Map<String, String> params = new HashMap<String, String>();
                            params.put("portfolioName", portfolioString);
                            String response = ServerUtilities.post(SERVER_ADDR + "/addPortfolio.do", params);
                            if (response == "exist") {

                            } else {

                            }
                        } catch (IOException e1) {
                            uploadState = "Sync failed: " + e1.getCause();
                            Log.e("TAGG", "data posting error " + e1);
                        }*/

                        return uploadState;
                    }

                    @Override
                    protected void onPostExecute(String errString) {
                        String resultString;
                        if (errString.equals("")) {
                            resultString = " entry uploaded.";
                        } else {
                            resultString = errString;
                        }

                        Toast.makeText(getApplicationContext(), resultString,
                                Toast.LENGTH_SHORT).show();

                    }

                }.execute();
            }
        });


    }

    private void initialize(){
        portfolioString = portfolioName.getText().toString();
    }
}
