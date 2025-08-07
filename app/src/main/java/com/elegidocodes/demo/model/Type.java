package com.elegidocodes.demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Type {

    @SerializedName("slot")
    @Expose
    private Integer slot;
    @SerializedName("type")
    @Expose
    private Type1 type;

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Type1 getType() {
        return type;
    }

    public void setType(Type1 type) {
        this.type = type;
    }

}
