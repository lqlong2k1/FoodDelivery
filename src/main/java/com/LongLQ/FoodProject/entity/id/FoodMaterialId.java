package com.LongLQ.FoodProject.entity.id;

import javax.persistence.Id;
import java.io.Serializable;

public class FoodMaterialId implements Serializable {
    private int id_food;
    private int id_material;

    public FoodMaterialId(int id_food,int id_material){
        this.id_food = id_food;
        this.id_material = id_material;
    }

    public int getId_food() {
        return id_food;
    }

    public void setId_food(int id_food) {
        this.id_food = id_food;
    }

    public int getId_material() {
        return id_material;
    }

    public void setId_material(int id_material) {
        this.id_material = id_material;
    }
}
