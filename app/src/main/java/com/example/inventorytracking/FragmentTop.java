package com.example.inventorytracking;


import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * Name: Ryan Walker
 * Date: 5/15/19
 * Class: CIS 4280 Android Mobile App Development
 * Professor: Dr. Hui Shi
 *
 *
 * The top fragment is inflated when the user selects all the data. The fragment dynamically
 * creates TextViews with the booth number as well as edit boxes of each of the products being sold.
 * The user input how much of each product hasn't been sold. Once the they are finished it will send
 * the contents to be displayed on the Display page
 */
public class FragmentTop extends Fragment {



    private TextView tv_Title;
    private String amountBoothString, schoolNameString, date;
    private int booths;
    private ArrayList<TextView> boothNameList;
    private ArrayList<TextView> productList;
    private ArrayList<String> productListString;
    private ArrayList<EditText> productInputList;
    private Button submit;
    private String[] inputArray;

    public FragmentTop() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);


        //Relative and Linear Layout used to place dynamic TextViews and EditTexts
        final RelativeLayout ll = (RelativeLayout) view.findViewById(R.id.fragmentTop);
        final LinearLayout pp = (LinearLayout) view.findViewById(R.id.linear);

        //name of the school
        schoolNameString = getArguments().getString("NAME");

        //amount of booths parsed to an int
        amountBoothString = getArguments().getString("AMOUNT");
        booths = Integer.parseInt(amountBoothString);

        //date
        date = getArguments().getString("DATE");

        //the products being sold cast to and array
        productListString = (ArrayList<String>)getArguments().getSerializable("LIST");
        String[] products = productListString.toArray(new String[0]);

        //Set the title of the Fragment with the name of the school and the Date
        tv_Title = (TextView) view.findViewById(R.id.title);
        tv_Title.setText(schoolNameString + " " + date );



        //create Array of text views the size of the amount of booths entered by the user
        TextView[] tv_boothArray = new TextView[booths];

        boothNameList = new ArrayList<>();
        productInputList = new ArrayList<>();

        //for loop to dynamically create the TextViews for each booth
        //after 1 booth TextView is created it dynamically puts the products
        //entered by the user as EditTexts
        for (int i = 0; i < tv_boothArray.length; ++i)
        {
            TextView tv = new TextView(getActivity());
            tv.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.leftMargin = 50;
            //lp.topMargin = i*100;
            tv.setText("Booth " + (i + 1));
            tv.setTextSize((float) 30);
            tv.setPadding(0,150,0,0);
            //lp.addRule(RelativeLayout.BELOW, tv.getId());
            tv.setLayoutParams(lp);
            tv.setId(i);
            boothNameList.add(tv);
            pp.addView(tv);

            for(int j=0;j<products.length;j++){
                EditText prod = new EditText(getActivity());
                prod.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                //prod.setLayoutParams( new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));
                //RelativeLayout.LayoutParams pp = new RelativeLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT);
                prod.setHint(products[j]);
                prod.setId(j);
                //lp.addRule(RelativeLayout.BELOW,tv.getId());
                //prod.setPadding(20,50,20,50);
                pp.setGravity(Gravity.RIGHT);
                productInputList.add(prod);
                prod.setLayoutParams(lp);
                pp.addView(prod);
            }
        }

        //creates a submit button at the bottom of all the TextView and EditTexts
        submit = new Button(getActivity());
        submit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        submit.setText("Submit");
        pp.addView(submit);


        inputArray = new String[productInputList.size()];
        for(int i=0;i<productInputList.size();i++) {

            inputArray[i] = productInputList.get(i).getText().toString();

        }


        //When clicked the button will send the array of the totals for each product
        //inputted by the user to the Display Page
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                String[] inputArray = new String[productInputList.size()];
                for(int i=0;i<productInputList.size();i++)
                 {
                     inputArray[i] = productInputList.get(i).getText().toString();

                    SecondPage ma = (SecondPage) getActivity();
                    ma.sendToDisplay(inputArray);
                }

            }
        });
        return view;
    }


    public String[] getInputArray() {
        return inputArray;
    }
}