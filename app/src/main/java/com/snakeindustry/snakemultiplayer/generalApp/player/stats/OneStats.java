package com.snakeindustry.snakemultiplayer.generalApp.player.stats;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by Adrien on 06/04/15.
 */
public interface OneStats {
    public void add(double value);
    public double getValue();
    public String getDescription();
    public String getUnit();
    public void reset();
    public Bitmap getIcon();
}
