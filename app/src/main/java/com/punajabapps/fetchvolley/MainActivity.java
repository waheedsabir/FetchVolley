package com.punajabapps.fetchvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String url = "https://api.androidhive.info/json/movies.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void load(View view) {

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONArray js = new JSONArray(response);
                        JSONObject obj = js.getJSONObject(i);

                        String title = obj.getString("title");
                        String image = obj.getString("image");
                        String rating = obj.getString("rating");
                        String releaseYear = obj.getString("releaseYear");

                        TextView txt = (TextView) findViewById(R.id.txt);
                        txt.append(title + "\n" + image + "\n" + rating + "\n" + releaseYear + "\n");

                        Toast.makeText(MainActivity.this, "" + title, Toast.LENGTH_SHORT).show();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue r = Volley.newRequestQueue(this);
        r.add(stringRequest);


    }
}
