package com.heiko.recyclerviewswipedtest;

/**
 * Bean
 *
 * @author EthanCo
 * @since 2018/5/10
 */

public class Bean {
    private String name;
    private boolean isChecked;

    public Bean(String name, boolean isChecked) {
        this.name = name;
        this.isChecked = isChecked;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
