package com.example.inventorytracking;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * Name: Ryan Walker
 * Date: 5/15/19
 * Class: CIS 4280 Android Mobile App Development
 * Professor: Dr. Hui Shi
 *
 *
 *
 * The bottom fragment has a button that when press, sends the contents of the top fragment to the
 * display page. Unfortunately, after countless hours of various methods I was unable to get this to work
 * I do plan on fixing this in the future so that it does work.
 */
public class FragmentBottom extends Fragment  {

    private Button button;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom,container,false);


        button = (Button) view.findViewById(R.id.btn_submit);


        //this button was supposed to click and send the contents of the top fragment to the other page
        //but after many many tried I could not get it to work :(
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Toast.makeText(getActivity(),"Was supposed to Submit and send contents to next page, but couldn't get it to work :(", Toast.LENGTH_LONG).show();
            }
        });





        return view;
    }
}

