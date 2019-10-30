package com.example.inventorytracking;

import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Name: Ryan Walker
 * Date: 5/15/19
 * Class: CIS 4280 Android Mobile App Development
 * Professor: Dr. Hui Shi
 *
 * The product class is used to create an object for each booth so that object can be
 * inserted into the database. When called is passes in the school name, date, the number of booths,
 * the products that are being sold in the booths, and the amount of each product left.
 */
public class Product {



    private String schoolName, date, numOfBooths, productSold, productAmounnt;
    private ArrayList<String> products;


    public Product(){
        this.schoolName = null;
        this.date = null;
        this.numOfBooths=null;
        products = new ArrayList<>();
    }
    public Product(String schoolName, String booths, String date, String sold, String amount){
        this.schoolName = schoolName;
        this.numOfBooths = booths;
        this.date = date;
        this.productSold = sold;
        this.productAmounnt = amount;
        products = new ArrayList<>();

    }

    //getters
    public String getSchoolName() { return schoolName; }
    public String getDate() { return date; }
    public String getNumOfBooths() { return numOfBooths; }
    public String getProductSold() { return productSold; }
    public String getProductAmounnt() { return productAmounnt; }


    public ArrayList<String> getProducts() { return products; }

    //setters
    public void setSchoolName(String schoolName) { this.schoolName = schoolName; }
    public void setDate(String date) { this.date = date; }
    public void setProductSold(String productSold) { this.productSold = productSold; }
    public void setProductAmounnt(String productAmounnt) { this.productAmounnt = productAmounnt; }

    public void setNumOfBooths(String numOfBooths) {
        this.numOfBooths = numOfBooths;
    }

    public void addProduct(String product){
        this.products.add(product);
    }
}
