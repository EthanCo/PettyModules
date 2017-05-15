package com.ethanco.mytest170515.stream_test;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2017/5/15
 */

public class Dish {
    public static final String MEAT = "MEAT";
    public static final String OTHER = "OTHER";
    public static final String FISH = "FISH";

    @StringDef({MEAT, OTHER, FISH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {

    }

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final @Type
    String type;

    public Dish(String name, boolean vegetarian, int calories, @Type String type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
}
