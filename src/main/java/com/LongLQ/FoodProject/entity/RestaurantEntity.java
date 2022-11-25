package com.LongLQ.FoodProject.entity;
import javax.persistence.*;
import java.util.Set;

@Entity(name = "restaurant")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "restaurant")
    private Set<RestaurantReviewEntity> restaurantReviews;

    @OneToMany(mappedBy = "restaurant")
    private Set<FoodEntity> foods;

    public Set<FoodEntity> getFoods() {
        return foods;
    }

    public void setFoods(Set<FoodEntity> foods) {
        this.foods = foods;
    }

    public Set<RestaurantReviewEntity> getRestaurantReviews() {
        return restaurantReviews;
    }

    public void setRestaurantReviews(Set<RestaurantReviewEntity> restaurantReviews) {
        this.restaurantReviews = restaurantReviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
