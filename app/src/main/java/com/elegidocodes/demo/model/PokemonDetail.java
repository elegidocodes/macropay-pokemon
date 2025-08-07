package com.elegidocodes.demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class PokemonDetail {

    @SerializedName("abilities")
    @Expose
    private List<Ability> abilities;
    @SerializedName("sprites")
    @Expose
    private Sprites sprites;
    @SerializedName("weight")
    @Expose
    private Integer weight;

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "PokemonDetail{" +
                "abilities=" + abilities +
                ", sprites=" + sprites +
                ", weight=" + weight +
                '}';
    }

}
