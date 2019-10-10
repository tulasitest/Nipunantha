package com.globalsoft.nipunatha.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> service= new ArrayList<String>();
        // cricket.add("India");


        expandableListDetail.put("Select The Service you are looking for", service);
        return expandableListDetail;
    }
}
