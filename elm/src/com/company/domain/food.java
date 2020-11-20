package com.company.domain;

import java.math.BigDecimal;

public class food {
    private int foodId;
    private String foodName;
    private String foodExplain;;
    private BigDecimal foodPrice;
    private int businessId;

    public food() {
    }

    public food(int foodId, String foodName, String foodExplain, BigDecimal foodPrice, int businessId) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodExplain = foodExplain;
        this.foodPrice = foodPrice;
        this.businessId = businessId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodExplain() {
        return foodExplain;
    }

    public void setFoodExplain(String foodExplain) {
        this.foodExplain = foodExplain;
    }

    public BigDecimal getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigDecimal foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    @Override
    public String toString() {
        return "food{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", foodExplain='" + foodExplain + '\'' +
                ", foodPrice=" + foodPrice +
                ", businessId=" + businessId +
                '}';
    }
}
