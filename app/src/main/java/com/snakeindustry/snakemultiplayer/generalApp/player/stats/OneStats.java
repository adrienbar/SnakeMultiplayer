package com.snakeindustry.snakemultiplayer.generalApp.player.stats;

/**
 * Created by Adrien on 06/04/15.
 */
public class OneStats {
    private String description;
    private double value;
    private String unit;
    //private Image icon;


    public OneStats(String description, double value, String unit) {
        this.description = description;
        this.value = value;
        this.unit = unit;
    }

    public OneStats() {
        this("untitled stats",0,"");
    }


    public void add(double value){
      this.setValue(this.getValue()+value);
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
