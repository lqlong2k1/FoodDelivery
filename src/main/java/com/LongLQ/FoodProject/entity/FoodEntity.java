package com.LongLQ.FoodProject.entity;
import javax.persistence.*;
import java.util.Set;

@Entity(name = "food")
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private int price;

    @ManyToOne()
    @JoinColumn(name = "id_category")
    private CategoryEntity category;

    @ManyToOne()
    @JoinColumn(name = "id_restaurant")
    private RestaurantEntity restaurant;

    @OneToOne(mappedBy = "food")
    private FoodDetailEntity foodDetail;

    @OneToMany(mappedBy = "food")
    private Set<FoodReviewEntity> foodReviews;

    @OneToMany(mappedBy = "food")
    private Set<FoodAddonEntity> foodAdons;

    @OneToMany(mappedBy = "food")
    private Set<FoodMaterialEntity> foodMaterials;

    @OneToMany(mappedBy = "food")
    private Set<FoodOrderEntity> foodOrders;

    public Set<FoodOrderEntity> getFoodOrders() {
        return foodOrders;
    }

    public void setFoodOrders(Set<FoodOrderEntity> foodOrders) {
        this.foodOrders = foodOrders;
    }

    public Set<FoodMaterialEntity> getFoodMaterials() {
        return foodMaterials;
    }

    public void setFoodMaterials(Set<FoodMaterialEntity> foodMaterials) {
        this.foodMaterials = foodMaterials;
    }

    public Set<FoodAddonEntity> getFoodAdons() {
        return foodAdons;
    }

    public void setFoodAdons(Set<FoodAddonEntity> foodAdons) {
        this.foodAdons = foodAdons;
    }

    public Set<FoodReviewEntity> getFoodReviews() {
        return foodReviews;
    }

    public void setFoodReviews(Set<FoodReviewEntity> foodReviews) {
        this.foodReviews = foodReviews;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public FoodDetailEntity getFoodDetail() {
        return foodDetail;
    }

    public void setFoodDetail(FoodDetailEntity foodDetail) {
        this.foodDetail = foodDetail;
    }
}
