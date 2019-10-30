package com.example.inventorytracking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Name: Ryan Walker
 * Date: 5/15/19
 * Class: CIS 4280 Android Mobile App Development
 * Professor: Dr. Hui Shi
 * The MainActivity Class is displays a start screen with a title and a logo.
 * It also contains a start button that will take you to the next screen.
 */
public class MainActivity extends AppCompatActivity {

    private TextView titleBanner;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        titleBanner = findViewById(R.id.tv_startMessage);
        startButton = findViewById(R.id.btn_start);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(MainActivity.this, SecondPage.class);
                startActivity(start);

            }
        });




    }
}
