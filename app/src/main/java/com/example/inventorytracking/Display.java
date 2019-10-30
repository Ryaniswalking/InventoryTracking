


package com.example.inventorytracking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Name: Ryan Walker
 * Date: 5/15/19
 * Class: CIS 4280 Android Mobile App Development
 * Professor: Dr. Hui Shi
 *
 *
 *
 * The Display page receives all the contents of the top fragment and displays it on the screen.
 * Then the user can highlight and copy all the contents on the screen. After countless hours of
 * various methods, I was unable to get it to work. The user can only highlight one textview at a time.
 * I do plan on getting this to work in the future as it defeats the purpose of the entire app if the
 * user can't copy the contents on this screen
 */
public class Display extends AppCompatActivity {



    private Button saveToDB;
    private Product product;
    private DatabaseManager db;
    private int k = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        //create object of DatabaseManager
        db = new DatabaseManager(this);

        //saveToDB = findViewById(R.id.copy);


        //get the School Name, the amount of booths, the date, the products sold
        //and how many of each product sold from the second page
        Bundle b = getIntent().getExtras();
        final String schoolName = b.getString("NAME"); //school name
        final int boothsAmount = Integer.parseInt(b.getString("AMOUNT")); //amount of booths
        final String date = b.getString("DATE"); //date
        final String [] productsEnteredArr = b.getStringArray("PRODUCTS");// amount of each product
        final String [] productsArray = b.getStringArray("SOLD");// the amount of products


        //Linear Layout to display the contents
        final LinearLayout pp = (LinearLayout) findViewById(R.id.linear);


        TextView title = new TextView(this);
        title.setText(schoolName + " " + date);
        title.setTextSize((float) 35);
        pp.addView(title);

        //Nest for loop to display the booths
        for (int i = 0; i < boothsAmount; ++i)
        {
            TextView tv = (TextView) new TextView(this);
            tv.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.leftMargin = 50;
            tv.setText("Booth " + (i + 1));
            tv.setTextSize((float) 30);
            tv.setPadding(0,0,0,0);
            tv.setTextIsSelectable(true);
            tv.setLayoutParams(lp);
            tv.setId(i);
            pp.addView(tv);

            //Nested for loop to display the product and how many are left
            //it is supposed to print the amount of each product but i couldnt get it to work

            for(int j=0;j<productsArray.length;j++)
            {
                TextView prod = new TextView(this);
                prod.setText("\t\t\t\t" +productsArray[j] + ":  " + productsEnteredArr[k]);
                k++;
                prod.setTextIsSelectable(true);
                prod.setId(j);
                prod.setLayoutParams(lp);
                pp.addView(prod);

            }
        }

        saveToDB = new Button(this);
        saveToDB.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        saveToDB.setText("Save to Database");
        pp.addView(saveToDB);


        saveToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Take the array and convert it to single string to be inserted into the database

                //products entered by the user
                String pEntered = ("" + Arrays.asList(productsEnteredArr))
                                    .replace("(^.|.$)", " ")
                                    .replace(", ", " , ");
                //amount of each product left at the booth
                String pSold = ("" + Arrays.asList(productsArray))
                        .replace("(^.|.$)", " ")
                        .replace(", ", " , ");


                //create product class and insert it into data base
                for(int i=0;i<boothsAmount;i++){
                    product = new Product(schoolName,Integer.toString(i),date, pEntered,pSold);
                    db.insert(product);
                }


                Toast.makeText(Display.this, pEntered, Toast.LENGTH_LONG).show();


            }
        });




    }


}
