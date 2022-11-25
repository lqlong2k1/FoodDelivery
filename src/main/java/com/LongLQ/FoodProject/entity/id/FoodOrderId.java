package com.LongLQ.FoodProject.entity.id;
import java.io.Serializable;

public class FoodOrderId implements Serializable {
    private int id_order;
    private int id_food;

    public void FoodMaterialId(int id_order, int id_food){
        this.id_food = id_food;
        this.id_order = id_order;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getId_food() {
        return id_food;
    }

    public void setId_food(int id_food) {
        this.id_food = id_food;
    }
}
