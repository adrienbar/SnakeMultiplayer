package com.snakeindustry.snakemultiplayer.generalApp.mainActivity;

import android.content.Intent;
import android.view.View;

/**
 * Created by Adrien on 30/03/15.
 */
public class ButtonController implements View.OnClickListener {

    private Class activityToLaunch;

    public ButtonController(Class activityToLaunch) {
        this.activityToLaunch = activityToLaunch;
    }

    public Class getActivityToLaunch() {
        return activityToLaunch;
    }

    @Override
    public void onClick(View v) {
        Intent myIntent = new Intent(v.getContext(), this.getActivityToLaunch());
        v.getContext().startActivity(myIntent);
    }
}
