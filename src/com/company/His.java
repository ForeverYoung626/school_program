package com.company;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class His {
    private Calendar time;

    private Map<String, Map<String, Integer>> hisForm = new HashMap<>();
    private Map<String, ArrayList<String>> hisCourt = new HashMap<>();

    His( School s, Map<String, Map<String, Integer>> mapForm, Map<String, ArrayList<String>> mapCourt){
        time = Calendar.getInstance();
//        for (int i=0; i<s.getBallKinds();i++){
//            ArrayList<String> temp = new ArrayList<>();
//            for (int j=0; j<s.getBall(i).getCourtNum(); j++)
//                temp.add(cls[i][j]);
//            hisCourt.add(temp);
//        }
        hisForm = mapForm;
        hisCourt = mapCourt;

    }

    public void updateForm (String cls, String crt, int i) {
        hisForm.get(cls).put(crt, i);
    }

    public void updateCourt( String crt, int i, String cls) {
        hisCourt.get(crt).set(i, cls);
    }

    public String getCourt ( School sch, String crt , int index) {
        return hisCourt.get(crt).get(index);
    }

//    public String getCourt (School sch, String crt) {
//        for (int i=0; i<crt.length(); )
//
//        return getCourt(sch, /*国字*/, /*数字*/);
//    }

    public Calendar getTime() {
        return time;
    }

}
