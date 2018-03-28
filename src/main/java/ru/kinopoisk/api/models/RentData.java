package ru.kinopoisk.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RentData {
    private String premiereDVD;
    private String premiereBluRay;

    public RentData() {
        this.premiereDVD = null;
        this.premiereBluRay = null;
    }

    public String getPremiereDVD() {
        return premiereDVD;
    }

    public void setPremiereDVD(String premiereDVD) {
        this.premiereDVD = premiereDVD;
    }

    public String getPremiereBluRay() {
        return premiereBluRay;
    }

    public void setPremiereBluRay(String premiereBluRay) {
        this.premiereBluRay = premiereBluRay;
    }

    @Override
    public String toString() {
        return "RentData{" +
                "premiereDVD='" + premiereDVD + '\'' +
                ", premiereBluRay='" + premiereBluRay + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentData rentData = (RentData) o;
        return Objects.equals(getPremiereDVD(), rentData.getPremiereDVD()) &&
                Objects.equals(getPremiereBluRay(), rentData.getPremiereBluRay());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPremiereDVD(), getPremiereBluRay());
    }
}
