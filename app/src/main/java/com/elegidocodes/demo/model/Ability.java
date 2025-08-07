package com.elegidocodes.demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Ability {

    @SerializedName("ability")
    @Expose
    private Ability1 ability;
    @SerializedName("is_hidden")
    @Expose
    private Boolean isHidden;
    @SerializedName("slot")
    @Expose
    private Integer slot;

    public Ability1 getAbility() {
        return ability;
    }

    public void setAbility(Ability1 ability) {
        this.ability = ability;
    }

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    @Override
    public String toString() {
        return "Ability{" +
                "ability=" + ability +
                ", isHidden=" + isHidden +
                ", slot=" + slot +
                '}';
    }

}
