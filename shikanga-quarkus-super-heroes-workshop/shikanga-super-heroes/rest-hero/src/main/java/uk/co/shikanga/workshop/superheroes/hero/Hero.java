package uk.co.shikanga.workshop.superheroes.hero;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.StringJoiner;

@Entity
@Table(name = "hero")
public class Hero extends BaseEntity       {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @Column(name = "other_name")
    private String otherName;

    @NotNull
    @Min(1)
    private int level;

    private String picture;

    @Column(columnDefinition = "TEXT")
    private String powers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPowers() {
        return powers;
    }

    public void setPowers(String powers) {
        this.powers = powers;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Hero.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("otherName='" + otherName + "'")
                .add("level=" + level)
                .add("picture='" + picture + "'")
                .add("powers='" + powers + "'")
                .toString();
    }
}
