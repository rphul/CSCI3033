package com.example.weathernow;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

public class WeatherStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_status);

        String dat = new java.text.SimpleDateFormat("EEEE MMM dd").format(new java.util.Date(MainActivity.date*1000));
        Date today = new java.util.Date(MainActivity.date*1000);
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(today);
        cal2.add(Calendar.DATE,1);
        String dat2 = new java.text.SimpleDateFormat("EEEE MMM dd").format(cal2.getTime());

        Calendar cal3 = Calendar.getInstance();
        cal3.setTime(today);
        cal3.add(Calendar.DATE,2);
        String dat3 = new java.text.SimpleDateFormat("EEEE MMM dd").format(cal3.getTime());

        Calendar cal4 = Calendar.getInstance();
        cal4.setTime(today);
        cal4.add(Calendar.DATE,3);
        String dat4 = new java.text.SimpleDateFormat("EEEE MMM dd").format(cal4.getTime());

        Calendar cal5 = Calendar.getInstance();
        cal5.setTime(today);
        cal5.add(Calendar.DATE,4);
        String dat5 = new java.text.SimpleDateFormat("EEEE MMM dd").format(cal5.getTime());

        Calendar cal6 = Calendar.getInstance();
        cal6.setTime(today);
        cal6.add(Calendar.DATE,5);
        String dat6 = new java.text.SimpleDateFormat("EEEE MMM dd").format(cal6.getTime());

        Calendar cal7 = Calendar.getInstance();
        cal7.setTime(today);
        cal7.add(Calendar.DATE,6);
        String dat7 = new java.text.SimpleDateFormat("EEEE MMM dd").format(cal7.getTime());



        // get data from main activity and display in TextView
        TextView ci = (TextView) findViewById(R.id.textView9);
        ci.setText(String.valueOf(MainActivity.cityName));

        TextView t = (TextView) findViewById(R.id.textView);
        String first = dat + "  " + String.valueOf(MainActivity.temperature) + "\u2109";
        t.setText(first);

        TextView t2 = (TextView) findViewById(R.id.textView3);
        String second = dat2 + "  65" + "\u2109";
        t.setText(second);

        TextView t3 = (TextView) findViewById(R.id.textView4);
        String third = dat3 + "  68" + "\u2109";
        t.setText(third);

        TextView t4 = (TextView) findViewById(R.id.textView5);
        String fourth = dat4 + "  " + String.valueOf(72) + "\u2109";
        t.setText(fourth);

        TextView t5 = (TextView) findViewById(R.id.textView6);
        String fifth = dat5 + "  " + String.valueOf(73) + "\u2109";
        t.setText(fifth);

        TextView t6 = (TextView) findViewById(R.id.textView7);
        String sixth = dat6 + "  " + String.valueOf(70) + "\u2109";
        t.setText(sixth);

        TextView t7 = (TextView) findViewById(R.id.textView8);
        String seventh = dat7 + "  " + String.valueOf(75) + "\u2109";
        t.setText(seventh);

        // load icon image from iconUrl in MainActivity
        ImageView image = (ImageView) findViewById(R.id.imageView);
        if (MainActivity.descript.contains("clear"))
        { image.setImageDrawable(getDrawable(R.drawable.clear)); }
        else if (MainActivity.descript.contains("few clouds"))
        { image.setImageDrawable(getDrawable(R.drawable.mostly_sunny)); }
        else if (MainActivity.descript.contains("scattered clouds"))
        { image.setImageDrawable(getDrawable(R.drawable.partly_sunny)); }
        else if( (MainActivity.descript.contains("broken clouds")) || (MainActivity.descript.contains("overcast clouds")) )
        { image.setImageDrawable(getDrawable(R.drawable.cloudy)); }
        else if (MainActivity.descript.contains("shower rain"))
        { image.setImageDrawable(getDrawable(R.drawable.rain)); }
        else if ( (MainActivity.descript.contains("rain")) )
        { image.setImageDrawable(getDrawable(R.drawable.rain_showers)); }
        else if (MainActivity.descript.contains("thunderstorm"))
        { image.setImageDrawable(getDrawable(R.drawable.thuderstorms)); }
        else if (MainActivity.descript.contains("snow"))
        { image.setImageDrawable(getDrawable(R.drawable.snow)); }
        else if (MainActivity.descript.contains("mist"))
        { image.setImageDrawable(getDrawable(R.drawable.foggy)); }

        ImageView image2 = (ImageView) findViewById(R.id.imageView2);
        image2.setImageDrawable(getDrawable(R.drawable.clear));

        ImageView image3 = (ImageView) findViewById(R.id.imageView3);
        image3.setImageDrawable(getDrawable(R.drawable.rain));

        ImageView image4 = (ImageView) findViewById(R.id.imageView4);
        image4.setImageDrawable(getDrawable(R.drawable.cloudy));

        ImageView image5 = (ImageView) findViewById(R.id.imageView5);
        image5.setImageDrawable(getDrawable(R.drawable.thuderstorms));

        ImageView image6 = (ImageView) findViewById(R.id.imageView6);
        image6.setImageDrawable(getDrawable(R.drawable.rain_showers));

        ImageView image7 = (ImageView) findViewById(R.id.imageView7);
        image7.setImageDrawable(getDrawable(R.drawable.clear));

        // Button to go to the third activity
        Button detail = (Button)findViewById(R.id.detail_button);
        final Intent thirdActivity = new Intent (this, WeatherDetail.class);

        // Go to detail activity
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(thirdActivity);
            }
        });

    }






}


