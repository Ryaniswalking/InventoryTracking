package com.example.inventorytracking;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Name: Ryan Walker
 * Date: 5/15/19
 * Class: CIS 4280 Android Mobile App Development
 * Professor: Dr. Hui Shi
 *
 *
 *
 * SecondPage class extends AppCompatActivity and implements Serializable.
 * The user enters the school name. Then the user enters the amount of booths that are selling products
 * The user then enter the data and checks the boxes of which product is being sold.
 * Once the user has figured all of that out they press the button and it will inflate the Top and
 * bottom fragment and place all the contents in the top fragment
 */
public class SecondPage extends AppCompatActivity implements Serializable {

    private EditText et_schoolName, et_booths;
    private CheckBox cb_roses, cb_spring, cb_doubleLeis, cb_singleLeis,
            cb_premKuk, cb_branKuk;
    private ArrayList<String> boothArrayList;
    private static FragmentManager fm;
    final Calendar myCalendar = Calendar.getInstance();
    private String schoolNameString, boothAmountString, date;
    private String[] testArray;
    private FragmentTop fag;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);


        fm =getSupportFragmentManager();


        //EditTexts
        et_schoolName = findViewById(R.id.et_schoolName);
        et_booths = findViewById(R.id.et_booths);

        //Checkboxes
        cb_roses = findViewById(R.id.cb_roses);
        cb_spring = findViewById(R.id.cb_spring);
        cb_doubleLeis = findViewById(R.id.cb_doubleLei);
        cb_singleLeis = findViewById(R.id.cb_singleLei);
        cb_premKuk = findViewById(R.id.cb_premKuk);
        cb_branKuk = findViewById(R.id.cb_branKuk);


        //EditText for the date
        //Use the DatePickerDialog to open up the calendar when
        //the user clicks on the Date Edit box
        final EditText etDate = findViewById(R.id.et_date);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };


        //onClickListener for the date EditText
        etDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new DatePickerDialog(SecondPage.this, date, myCalendar.get(Calendar.YEAR)
                        ,myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        //button
        Button secondStart = (Button) findViewById(R.id.btn_submit);
        secondStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //set from the EditTexts to Strings

                //to get the name of the school
                schoolNameString = et_schoolName.getText().toString();
                //the amount of booths
                boothAmountString = et_booths.getText().toString();



                boothArrayList = new ArrayList<>();
                //if the check box is is clicked it saves the text of the
                //of the edit box and stores it in an boothArrayList
                if(cb_roses.isChecked()){
                    boothArrayList.add(cb_roses.getText().toString());
                }if (cb_spring.isChecked()){
                    boothArrayList.add(cb_spring.getText().toString());
                }if (cb_doubleLeis.isChecked()){
                    boothArrayList.add(cb_doubleLeis.getText().toString());
                }if (cb_singleLeis.isChecked()){
                    boothArrayList.add(cb_singleLeis.getText().toString());
                }if (cb_premKuk.isChecked()){
                    boothArrayList.add(cb_premKuk.getText().toString());
                }if (cb_branKuk.isChecked()){
                    boothArrayList.add(cb_branKuk.getText().toString());
                }


                //When the button is clicked it sends the name of the school
                //the amount of booths entered, the date, and the products chosen
                //from the checkboxes to be displayed on the Top Fragment

                //Fragment fragment = new FragmentTop();
                fag = new FragmentTop();
                Bundle bundle2 = new Bundle();
                bundle2.putString("NAME", schoolNameString); //the name of the school
                bundle2.putString("AMOUNT", boothAmountString); //amount of booths entered by user
                bundle2.putString("DATE", etDate.getText().toString()); //the date
                bundle2.putSerializable("LIST",boothArrayList); //products chosen by user
                fag.setArguments(bundle2);
                //fragment.setArguments(bundle2);


                //adds the bottom and top fragments to the view
                fm.beginTransaction().add(R.id.fragmentContainerTop, fag).commit();
                fm.beginTransaction().add(R.id.fragmentContainerBottom, new FragmentBottom()).commit();
            }
        });

    }


    //method used to update the label for the date
    private void updateLabel()
    {
        String format = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);

        EditText etDate = findViewById(R.id.et_date);
        etDate.setText(sdf.format(myCalendar.getTime()));
        date = etDate.getText().toString();
    }

    /**
     * Method that passes in the Array of the amount of products given by the user
     * in the top fragment. Pass the array of products to the Display Activity along with
     * the name of the school, the amount of booths entered ty the user, the date, and the amount
     * of booths entered by the user.
     * @param array String array of the amount of each product entered by the user
     */
    public void sendToDisplay(String[] array){

        String [] productArray = getStringArray(boothArrayList);

        Intent displayPage = new Intent(SecondPage.this, Display.class);

        displayPage.putExtra("NAME", schoolNameString); //the name of the school
        displayPage.putExtra("AMOUNT",boothAmountString); //amount of booths user entered
        displayPage.putExtra("DATE", date); //date
        displayPage.putExtra("SOLD", productArray); //products entered by the user
        displayPage.putExtra("PRODUCTS", array); //amount of each product from Top Fragment
        startActivity(displayPage);
    }

    /**
     * Method used to turn an ArrayList<String> to and String [] array
     * @param arr ArrayList<String>
     * @return String [] array
     */
    public String[] getStringArray(ArrayList<String> arr){
        String [] str = new String[arr.size()];
        for (int i=0;i<arr.size();i++){
            str[i] = arr.get(i);
        }
        return str;
    }





}