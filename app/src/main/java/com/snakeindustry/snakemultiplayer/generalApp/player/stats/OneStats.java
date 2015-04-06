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


    public void increaseBy(double value){
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

    private void setValue(double value) {
        this.value = value;
    }

}
