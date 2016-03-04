package com.blk.PfM_Demo;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class PortfolioDetailActivity extends Activity {
    public static String SERVER_ADDR = "https://1-dot-pfm-demo-backend.appspot.com/";
    private static final String OPENDATETAGE ="opendate";
    private static final String PORTFOLIOTAG = "portfolio";
    private static final String OPENPOSITIONRESPONSE= "openPositionResponse";
    private Button openPositionButton;
    private EditText portfolioName;
    private Spinner opendate;

    String portfolioString;
    String opendateString ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portfolio_detail_activity);
        openPositionButton = (Button) findViewById(R.id.openPositionButton);
        portfolioName = (EditText)findViewById(R.id.portfolioName);
        opendate= (Spinner)findViewById(R.id.openDate);
        openPositionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Void, String>() {

                    @Override
                    // Get history and upload it to the server.
                    protected String doInBackground(Void... arg0) {


                        // Upload the history of all entries using upload().
                        String uploadState = "";
                        try {
                            initialize();

                           Map<String, String> params = new HashMap<String, String>();
                            params.put(OPENDATETAGE, opendateString);
                            params.put(PORTFOLIOTAG, portfolioString);
                            String response = ServerUtilities.post(SERVER_ADDR + "/openPosition.do", params);
                            JSONObject filedata = new JSONObject(response);
                            JSONObject jsonObject = new JSONObject();
                            JSONArray jsonArray = jsonObject.getJSONArray((String) filedata.get(OPENPOSITIONRESPONSE));


                            if (jsonArray.length()>0) {
                                ArrayList<Portfolio> listPortfolio = new ArrayList<Portfolio>();
                                for(int i=0;i<jsonArray.length();i++)
                                {
                                    JSONObject curr = jsonArray.getJSONObject(i);

                                    Portfolio portfolio = new Portfolio(curr.getString(Portfolio.FIELD_NAME_NAME),
                                            curr.getString(Portfolio.FIELD_NAME_CUSIP),
                                            curr.getString(Portfolio.FIELD_NAME_CODE),
                                            curr.getString(Portfolio.FIELD_NAME_OPENDATE),
                                            curr.getString(Portfolio.FIELD_NAME_EXPDATE),
                                            curr.getString(Portfolio.FIELD_NAME_DESC));
                                    listPortfolio.add(portfolio);
                                }
                                Intent myIntent = new Intent(PortfolioDetailActivity.this, LoginActivity.class);
                                myIntent.putExtra(PORTFOLIOTAG, portfolioString);
                                PortfolioDetailActivity.this.startActivity(myIntent);
                            } else {

                            }
                        } catch (IOException e1) {
                            uploadState = "Sync failed: " + e1.getCause();
                            Log.e("TAGG", "data posting error " + e1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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
        portfolioString = portfolioName.getText().toString().trim();
        opendateString = String.valueOf(opendate.getSelectedItem());
    }
}
