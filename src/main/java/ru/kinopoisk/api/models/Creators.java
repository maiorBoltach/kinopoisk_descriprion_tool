package ru.kinopoisk.api.models;

import java.util.Objects;

public class Creators {
    private int id = 0;
    private String nameRu;
    private String nameEn;
    private String posterUrl;
    private String professionText;
    private String professionKey;
    private String description;

    public Creators() {
        this.id = 0;
        this.nameRu = null;
        this.nameEn = null;
        this.posterUrl = null;
        this.professionText = null;
        this.professionKey = null;
        this.description = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getProfessionText() {
        return professionText;
    }

    public void setProfessionText(String professionText) {
        this.professionText = professionText;
    }

    public String getProfessionKey() {
        return professionKey;
    }

    public void setProfessionKey(String professionKey) {
        switch (professionKey) {
            case "director":
                this.professionKey = "DIRECTOR";
                break;
            case "actor":
                this.professionKey = "ACTOR";
                break;
            case "producer":
                this.professionKey = "PRODUCER";
                break;
            case "producer_ussr":
                this.professionKey = "PRODUCER";
                break;
            default:
                this.professionKey = null;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Creators)) return false;
        Creators creators = (Creators) o;
        return id == creators.id &&
                Objects.equals(nameRu, creators.nameRu) &&
                Objects.equals(nameEn, creators.nameEn) &&
                Objects.equals(posterUrl, creators.posterUrl) &&
                Objects.equals(professionText, creators.professionText) &&
                professionKey == creators.professionKey &&
                Objects.equals(description, creators.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nameRu, nameEn, posterUrl, professionText, professionKey, description);
    }

    @Override
    public String toString() {
        return "Creators{" +
                "id=" + id +
                ", nameRu='" + nameRu + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", professionText='" + professionText + '\'' +
                ", professionKey=" + professionKey +
                ", description='" + description + '\'' +
                '}';
    }

}
