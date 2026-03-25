package com.example.thuchanh4;

import java.io.Serializable;

public class Examinee implements Serializable {
    private String Id;
    private String FullName;
    private String Image;
    private float Math, Physic, Chemical;
    public Examinee(String id, String name,String image, float math, float physic, float chemical) {
        this.Id = id;
        this.FullName = name;
        this.Math = math;
        this.Physic = physic;
        this.Chemical = chemical;
        this.Image = image;
    }

    @Override
    public String toString() {
        return Id + " - " + FullName + " - " + Math + ", " + Physic + ", " + Chemical;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public float getChemical() {
        return Chemical;
    }

    public void setChemical(float chemical) {
        Chemical = chemical;
    }

    public float getPhysic() {
        return Physic;
    }

    public void setPhysic(float physic) {
        Physic = physic;
    }

    public float getMath() {
        return Math;
    }

    public void setMath(float math) {
        Math = math;
    }

    public float getSum() {
        return Math + Physic + Chemical;
    }
    public float getAverage() {
        return getSum() / 3;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
