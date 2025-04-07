package io.littlehorse.quickstart.Model;

import java.util.HashMap;
import java.util.List;

public class Restaurant {

   public Long restrauntId;
   public String name;
   public HashMap<String, Double> menu;
   public Boolean open;

    public Restaurant(HashMap<String, Double> menu, Boolean open, String name, Long restrauntId) {
        this.menu = menu;
        this.open = open;
        this.name = name;
        this.restrauntId = restrauntId;
    }


    public Long getRestrauntId() {
        return restrauntId;
    }

    public void setRestrauntId(Long restrauntId) {
        this.restrauntId = restrauntId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Double> getMenu() {
        return menu;
    }

    public void setMenu(HashMap<String, Double> menu) {
        this.menu = menu;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
