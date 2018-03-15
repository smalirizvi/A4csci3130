package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Contact implements Serializable {
    public String uid;
    public String businessNumber;
    public String name;
    public String primaryBusiness;
    public String address;
    public String province;

    /**
     * java docs
     */
    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     *
     * @param uid
     * @param bNumber
     * @param name
     * @param primaryB
     * @param add
     * @param prov
     */
    public Contact(String uid, String bNumber, String name, String primaryB, String add, String prov){
        this.uid = uid;
        this.businessNumber = bNumber;
        this.name = name;
        this.primaryBusiness = primaryB;
        this.address = add;
        this.province = prov;
    }

    /**
     *
     * @return
     */
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("BusinessNumber", businessNumber);
        result.put("name", name);
        result.put("PrimaryBusiness", primaryBusiness);
        result.put("Address", address);
        result.put("Province", province);

        return result;
    }
}
