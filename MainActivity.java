package com.example.weathernow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

// Support making request network
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // Main stuff
    // https://api.openweathermap.org/data/2.5/weather?q=murfreesboro,us&units=imperial&appid={apiKey}
    // https://api.openweathermap.org/data/2.5/weather?us&units=imperial&appid=0893fabe24af17768224d4c86f82ae5a&q=murfreesboro
    private String apiKey = "0893fabe24af17768224d4c86f82ae5a";
    private String urlTemp = "https://api.openweathermap.org/data/2.5/weather?us&units=imperial&appid=0893fabe24af17768224d4c86f82ae5a&q=";
    private RequestQueue  rQueue;
    private AutoCompleteTextView searchBox;
    private Button search;
    private TextView warningText;
    private ProgressBar loading;

    // Static fields allow sharing
    // To be displayed in weather status activity
    static String cityName;
    static String iconUrl;             // http://openweathermap.org/img/w/10d.png
    static String descript;
    static int temperature;            // Fahrenheit
    static int tempHigh;
    static int tempLow;
    // To be displayed in weather detail activity
    static int sunrise;                 // Unix time, UTC
    static int sunset;                  // Unix time, UTC
    static int windSpeed;               // mph
    static int humidity;                // %
    static int pressure;                // hPa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set default info-fake data
        cityName = "Murfreesboro";
        iconUrl = "http://openweathermap.org/img/w/10d.png/04d";
        descript = "overcast clouds";
        temperature = 39;
        tempHigh = 40;
        tempLow = 35;
        sunrise = 1543235597;
        sunset = 1543271564;
        windSpeed = 17;
        humidity = 75;
        pressure = 1003;

        /* Setup references */
        search = (Button) findViewById(R.id.search_button);
        searchBox = (AutoCompleteTextView) findViewById(R.id.searchBox);
        warningText = (TextView) findViewById(R.id.warningText);
        loading = (ProgressBar) findViewById(R.id.progressBar);
        loading.setVisibility(View.GONE);

        /* Set up AutoCompleteTextView */
        // Recommend city lists
        String[] cities = {"Nashville", "Murfreesboro", "Chattanooga", "Memphis", "Knoxville", "Clarksville",
                "Franklin", "Jackson", "Hendersonville", "Brentwood", "Bellevue"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, cities);
        // Set a list of suggestions to be display
        searchBox.setAdapter(adapter);
        // Threshold number of typed character before the list of suggestions appear
        searchBox.setThreshold(1);

        /* Create new request network queue on current context */
        rQueue = Volley.newRequestQueue(this);


        /* Go to second activity to display current weather status */
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear warning text
                warningText.setText("");
                // Build url address
                String url = urlTemp + searchBox.getText().toString();
                // Start loading icon
                loading.setVisibility(View.VISIBLE);
                // Retrieve weather data
                getWeatherData(url);
            }
        });


    } // end onCreate()


    /* Get weather data - calling restful API to get JSON data */
    private void getWeatherData(String url) {
        final Intent secondActivity = new Intent (this, WeatherStatus.class);
        JsonObjectRequest request = new JsonObjectRequest(JsonRequest.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Handle response - check exception
                try {
                    // normalize JSON data
                    JSONArray weatherArray = response.getJSONArray("weather");       // array of objects
                    JSONObject topObj = weatherArray.getJSONObject(0);
                    JSONObject sys = response.getJSONObject("sys");
                    JSONObject main = response.getJSONObject("main");
                    JSONObject wind = response.getJSONObject("wind");
                    // Collect data
                    cityName = response.getString("name");
                    iconUrl = "w" + topObj.getString("icon");// + ".png";
                    descript = topObj.getString("description");
                    temperature = main.getInt("temp");
                    tempHigh = main.getInt("temp_max");
                    tempLow = main.getInt("temp_min");
                    sunrise = sys.getInt("sunrise");
                    sunset = sys.getInt("sunset");
                    windSpeed = wind.getInt("speed");
                    humidity = main.getInt( "humidity");
                    pressure = main.getInt("pressure");

                    // Stop loading
                    loading.setVisibility(View.GONE);

                    // Switch to second activity
                    startActivity(secondActivity);

                } catch (JSONException e) {
                    e.printStackTrace();
                    // Handle error parsing
                    warningText.setText("Something wrong with network, Try again!");
                    // Stop loading
                    loading.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error response - check exception
                warningText.setText("Wrong city name, Try again!");
                // Stop loading
                loading.setVisibility(View.GONE);
            }
        });
        // Add to request network queue
        rQueue.add(request);
    }

}
