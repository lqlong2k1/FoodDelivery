package com.LongLQ.FoodProject.entity;
import javax.persistence.*;
import java.util.Set;

@Entity(name = "material")
public class MaterialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "material")
    private Set<FoodMaterialEntity> foodMaterial;

    public Set<FoodMaterialEntity> getFoodMaterial() {
        return foodMaterial;
    }

    public void setFoodMaterial(Set<FoodMaterialEntity> foodMaterial) {
        this.foodMaterial = foodMaterial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
