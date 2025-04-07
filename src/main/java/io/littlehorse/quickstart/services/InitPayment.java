package io.littlehorse.quickstart.services;

import io.littlehorse.quickstart.Model.Order;
import io.littlehorse.quickstart.Model.User;
import io.littlehorse.sdk.worker.LHTaskMethod;

public class InitPayment{

    @LHTaskMethod("calculate-payment")
    public double calculatePayment(User user,Order order){
        double price = order.getPrice();
        if(user.getDashPass() || order.getPrice() > 5){
            price = order.getPrice();
            price = price - (price * 0.02);
            order.setPrice(price);
        }

        return order.getPrice();
    }

    @LHTaskMethod("pay-for-order")
    public String payForOrder(User user, Order order){

        if(user.getAccountBalance() < order.getPrice()){
            return "#tobroke";
        }else{
            return "Payment processed";
        }

    }
}
