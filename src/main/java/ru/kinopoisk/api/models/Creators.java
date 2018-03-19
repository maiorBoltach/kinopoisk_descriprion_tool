package ru.kinopoisk.api.models;

import java.util.Objects;

public class Creators {
    private int id = 0;
    private String nameRu;
    private String nameEn;
    private String posterUrl;
    private String professionText;
    private creatorType professionKey;
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


    public Creators(int id, String nameRu, String nameEn, String posterUrl, String professionText,
                    creatorType professionKey, String description) {
        this.id = id;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.posterUrl = posterUrl;
        this.professionText = professionText;
        this.professionKey = professionKey;
        this.description = description;
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

    public creatorType getProfessionKey() {
        return professionKey;
    }

    public void setProfessionKey(creatorType professionKey) {
        this.professionKey = professionKey;
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

    public enum creatorType {ACTOR, DIRECTOR, PRODUCER}

}
