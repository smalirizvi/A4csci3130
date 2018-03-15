package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Java docs.
 */
public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText bNumberField, nameField, addField;
    private MyApplicationData appState;
    private Spinner spin,spin2;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        // Set up spinners
        spin = (Spinner) findViewById(R.id.primaryB);
        List<String> list = new ArrayList<String>();
        list.add("Fisher");
        list.add("Distributor");
        list.add("Processor");
        list.add("Fish Monger");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapter);

        spin2 = (Spinner) findViewById(R.id.province);
        List<String> list2 = new ArrayList<String>();
        list2.add("AB");
        list2.add("BC");
        list2.add("MB");
        list2.add("NB");
        list2.add("NL");
        list2.add("NS");
        list2.add("NT");
        list2.add("NU");
        list2.add("ON");
        list2.add("PE");
        list2.add("QC");
        list2.add("SK");
        list2.add("YT");
        list2.add("");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(dataAdapter2);

        submitButton = (Button) findViewById(R.id.submitButton);
        bNumberField = (EditText) findViewById(R.id.bNumber);
        nameField = (EditText) findViewById(R.id.name);
        addField = (EditText) findViewById(R.id.address);
    }

    /**
     *
     * @param v
     */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String bNumber = bNumberField.getText().toString();
        String name = nameField.getText().toString();
        String primaryB = spin.getSelectedItem().toString();
        String address = addField.getText().toString();
        String province = spin2.getSelectedItem().toString();
        Contact person = new Contact(personID,bNumber,name,primaryB,address,province);

        appState.firebaseReference.child(personID).setValue(person);

        finish();

    }
}
