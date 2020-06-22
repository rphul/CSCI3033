package com.example.weathernow;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        // Setup references

        /*Toast.makeText(WeatherDetail.this, MainActivity.iconUrl,
                Toast.LENGTH_LONG).show();*/

        TextView cityName = (TextView) findViewById(R.id.cityName);
        cityName.setText(String.valueOf(MainActivity.cityName));

        String weather_icon = MainActivity.iconUrl;

        ImageView weatherIcon = (ImageView) findViewById(R.id.weatherIcon);
        weatherIcon.setImageResource(getResources().getIdentifier(weather_icon, "drawable", getPackageName()));

        /*TextView iconUrl = (TextView) findViewById(R.id.iconUrl);
        iconUrl.setText(String.valueOf(MainActivity.iconUrl));*/

        TextView description = (TextView) findViewById(R.id.description);
        description.setText(String.valueOf(MainActivity.descript));

        TextView tempCurr = (TextView) findViewById(R.id.tempCurr);
        tempCurr.setText(String.valueOf(MainActivity.temperature + "°F"));

        TextView tempRange = (TextView) findViewById(R.id.tempRange);
        tempRange.setText(String.valueOf("H: " + MainActivity.tempHigh + "°F / " + "L: " + MainActivity.tempLow + "°F"));

        TextView humidity = (TextView) findViewById(R.id.humidity);
        humidity.setText(String.valueOf("Humidity:      " + MainActivity.humidity + "%"));

        TextView pressure = (TextView) findViewById(R.id.pressure);
        pressure.setText(String.valueOf("Pressure:      " + MainActivity.pressure + " hPa"));

        TextView windSpeed = (TextView) findViewById(R.id.windSpeed);
        windSpeed.setText(String.valueOf("Winds:           " + MainActivity.windSpeed + " mph"));

        long inTime = MainActivity.sunrise;
        Date time = new java.util.Date(inTime*1000L);
        SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("h:mm:ss a, z");
        String formattedTime = dateFormat.format(time);

        TextView sunrise = (TextView) findViewById(R.id.sunrise);
        sunrise.setText(String.valueOf("Sunset:          " + formattedTime));

        inTime = MainActivity.sunset;
        time = new java.util.Date(inTime*1000L);
        dateFormat = new java.text.SimpleDateFormat("h:mm:ss a, z");
        formattedTime = dateFormat.format(time);

        TextView sunset = (TextView) findViewById(R.id.sunset);
        sunset.setText(String.valueOf("Sunset:          " + formattedTime));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_weather_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_home:
                startActivity(new Intent(this, MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
