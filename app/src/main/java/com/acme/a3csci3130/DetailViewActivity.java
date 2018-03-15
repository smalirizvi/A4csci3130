package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class DetailViewActivity extends Activity {

    private EditText nameField, userNumber, userAddress;
    Contact receivedPersonInfo;
    private MyApplicationData appState;
    private Spinner dropdown1, dropdown2;
    private FirebaseListAdapter<Contact> firebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        appState = ((MyApplicationData) getApplicationContext());
        nameField = (EditText) findViewById(R.id.name);
        userNumber = (EditText) findViewById(R.id.bNumber);
        userAddress = (EditText) findViewById(R.id.address);

        // spinner 1: for business type
        dropdown1 = (Spinner) findViewById(R.id.primaryB);
        String[] items = new String[]{"Fisher", " Distributor", " Processor", "Fish Monger" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown1.setAdapter(adapter);
        dropdown1.setSelection(0);

        // spinner 2: for province selection
        dropdown2 = (Spinner) findViewById(R.id.province);
        String[] items2 = new String[]{" ", "AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE", "QC", "SK", "YT"} ;
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        //set the spinners adapter to the previously created one.
        dropdown2.setAdapter(adapter2);
        dropdown2.setSelection(0);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            userNumber.setText(receivedPersonInfo.businessNumber);
            userAddress.setText(receivedPersonInfo.address);
        }
    }

    /**
     *
     * @param v
     */
    public void updateContact(View v){
        String bNumber = userNumber.getText().toString();
        String name = nameField.getText().toString();
        String primaryB = dropdown1.getSelectedItem().toString();
        String address = userAddress.getText().toString();
        String province = dropdown2.getSelectedItem().toString();
        Contact person = new Contact(receivedPersonInfo.uid,bNumber,name,primaryB,address,province);

        appState.firebaseReference.child(receivedPersonInfo.uid).setValue(person);
        finish();
    }

    /**
     *
     * @param v
     */
    public void eraseContact(View v)
    {
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();
        finish();
    }
}
