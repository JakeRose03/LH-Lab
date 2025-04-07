package io.littlehorse.quickstart.services;

import io.littlehorse.quickstart.Model.Driver;
import io.littlehorse.quickstart.Model.Order;
import io.littlehorse.sdk.worker.LHTaskMethod;

import java.util.Random;

public class FindDriver {

    @LHTaskMethod("available-drivers")
    public String availableDrivers(){

        return "available Drivers";

    }

    @LHTaskMethod("rank-drivers")
    public String rankDriver(){

        return "ranking drivers";

    }
    @LHTaskMethod("assign-driver")
    public String findDriver(Order order){

        String driverName = "";

        if(order.getRestaurantName().equals("Mcdonalds")){
             driverName = "Ronald Mcdonald";
        }else if(order.getRestaurantName().equals("chick-fil-a")){
             driverName = "Cow";
        }else{
            driverName = "Driver Not Found";
        }

        return driverName;


    }


}
