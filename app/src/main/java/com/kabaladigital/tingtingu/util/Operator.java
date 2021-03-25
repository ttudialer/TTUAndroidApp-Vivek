package com.kabaladigital.tingtingu.util;

import com.kabaladigital.tingtingu.R;
import com.kabaladigital.tingtingu.models.OperatorSelectModel;

import java.util.ArrayList;
import java.util.List;

public class Operator {

    public static List<OperatorSelectModel> getMobileOperator(){
        List<OperatorSelectModel> operatorSelectModels = new ArrayList<>();
        OperatorSelectModel model=new OperatorSelectModel("1","RelianceJIO", R.drawable.jioicon);
        operatorSelectModels.add(model);

        OperatorSelectModel model2=new OperatorSelectModel("2","Airtel",R.drawable.airtel);
        operatorSelectModels.add(model2);

        OperatorSelectModel model3=new OperatorSelectModel("3","BSNL",R.drawable.bsnl);
        operatorSelectModels.add(model3);

        OperatorSelectModel model4=new OperatorSelectModel("4","Vodafone",R.drawable.voda);
        operatorSelectModels.add(model4);

        OperatorSelectModel model5=new OperatorSelectModel("5","Idea",R.drawable.idea);
        operatorSelectModels.add(model5);

//        OperatorSelectModel model6=new OperatorSelectModel("6","MNTL",R.drawable.mtnl);
//        operatorSelectModels.add(model6);

        return operatorSelectModels;
    }

    public static List<OperatorSelectModel> getDTHOperator(){
        List<OperatorSelectModel> operatorSelectModels = new ArrayList<>();
        OperatorSelectModel model=new OperatorSelectModel("1","Airtel",R.drawable.ic_dth_airtel);
        operatorSelectModels.add(model);

        OperatorSelectModel model2=new OperatorSelectModel("2","DishTV",R.drawable.ic_dish_tv);
        operatorSelectModels.add(model2);

        OperatorSelectModel model3=new OperatorSelectModel("3","TataSky",R.drawable.ic_tata_sky);
        operatorSelectModels.add(model3);

        OperatorSelectModel model4=new OperatorSelectModel("4","SunDirect",R.drawable.ic_sun_direct);
        operatorSelectModels.add(model4);

        OperatorSelectModel model5=new OperatorSelectModel("5","Videocon",R.drawable.d2h);
        operatorSelectModels.add(model5);

        return operatorSelectModels;
    }

    public static int getOperatorIcon(String select_Operator){
        switch (select_Operator) {
            case "RelianceJIO":
                return R.drawable.jioicon;
            case "Airtel":
                return R.drawable.airtel;
            case "BSNL":
                return R.drawable.bsnl;
            case "Vodafone":
                return R.drawable.voda;
            case "Idea":
                return R.drawable.idea;
            case "DishTV":
                return R.drawable.ic_dish_tv;
            case "TataSky":
                return R.drawable.ic_tata_sky;
            case "SunDirect":
                return R.drawable.ic_sun_direct;
            case "Videocon":
                return R.drawable.d2h;
        }
        return R.drawable.jioicon;
    }
}
