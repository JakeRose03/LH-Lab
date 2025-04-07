package io.littlehorse.quickstart.Model;

import java.util.List;

public class Order {

    private Long  orderId;
    private String restaurantName;
    private Long userId;;
    private List<String> items;
    private double price;

    public Order() {}

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Order(Long userId, List<String> items , String restaurantName, double price ){
        this.userId = userId;
        this.items = items;
        this.restaurantName = restaurantName;
        this.price = price;

    }


    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
