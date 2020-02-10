
package com.servicenowtask.app.ui.main.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flicker {

    @SerializedName("photos")
    @Expose
    public Photos photos;
    @SerializedName("stat")
    @Expose
    public String stat;

}
