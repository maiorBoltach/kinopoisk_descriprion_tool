package ru.kinopoisk.downloader.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Creator {
    private Integer id;
    private String nameRU;
    private String nameEN;
    private String posterURL;
    private String professionText;
    private String professionKey;
    private String description;

    public Creator() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRU() {
        return nameRU;
    }

    public void setNameRU(String nameRU) {
        this.nameRU = nameRU;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = "https://st.kp.yandex.net/images/" + posterURL.replace("iphone_", "iphone360_");
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
        if (o == null || getClass() != o.getClass()) return false;
        Creator creator = (Creator) o;
        return Objects.equals(getId(), creator.getId()) &&
                Objects.equals(getNameRU(), creator.getNameRU()) &&
                Objects.equals(getNameEN(), creator.getNameEN()) &&
                Objects.equals(getPosterURL(), creator.getPosterURL()) &&
                Objects.equals(getProfessionText(), creator.getProfessionText()) &&
                Objects.equals(getProfessionKey(), creator.getProfessionKey()) &&
                Objects.equals(getDescription(), creator.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNameRU(), getNameEN(), getPosterURL(), getProfessionText(),
                getProfessionKey(), getDescription());
    }

    @Override
    public String toString() {
        return "Creator{" +
                "id=" + id +
                ", nameRU='" + nameRU + '\'' +
                ", nameEN='" + nameEN + '\'' +
                ", posterURL='" + posterURL + '\'' +
                ", professionText='" + professionText + '\'' +
                ", professionKey='" + professionKey + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
