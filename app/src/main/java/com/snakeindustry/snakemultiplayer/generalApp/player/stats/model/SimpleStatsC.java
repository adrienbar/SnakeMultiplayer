package com.snakeindustry.snakemultiplayer.generalApp.player.stats.model;

import android.graphics.Bitmap;

/**
 * Created by Adrien on 06/04/15.
 */
public class SimpleStatsC implements SimpleStats {
    private String description;
    private double value;
    private String unit;
    private Bitmap icon;


    public SimpleStatsC(String description, double value, String unit, Bitmap icon) {
        this.description = description;
        this.value = value;
        this.unit = unit;
        this.icon=icon;
    }




    public SimpleStatsC(String description,double value) {
        this(description, value, "", Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_4444));
    }

    public SimpleStatsC(String description){
        this(description,0);
    }

    public SimpleStatsC() {
        this("untitled stats");
    }



    public void add(double value){
      this.setValue(this.getValue()+value);
      System.out.println("AAAAA OnStatsClass" + "Value added");
    }


    @Override
    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    //GETTERS and SETTERS
    public String getDescription() {
        return description;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public void reset() {
        this.setValue(0);
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
