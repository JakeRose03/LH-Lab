package io.littlehorse.quickstart.services;
import io.littlehorse.quickstart.Model.Order;
import io.littlehorse.sdk.worker.LHTaskMethod;

import java.util.List;
import java.util.Random;

public class InitiateOrder {

    Random rand = new Random();

    @LHTaskMethod("initiate-order")
    public Order initiateOrder(Order order){

        order.setOrderId(rand.nextLong());
        return order;

    }

}
