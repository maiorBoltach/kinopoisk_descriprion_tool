package ru.kinopoisk.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BudgetData {
    private Long budget;
    private Long grossWorld;

    public BudgetData() {
        this.budget = null;
        this.grossWorld = null;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public void setBudget(String budget) {
        this.budget = Long.parseLong(budget.replaceAll("\\D", ""));
    }

    public Long getGrossWorld() {
        return grossWorld;
    }

    public void setGrossWorld(Long grossWorld) {
        this.grossWorld = grossWorld;
    }

    public void setGrossWorld(String grossWorld) {
        this.grossWorld = Long.parseLong(grossWorld.replaceAll("\\D", ""));
    }

    @Override
    public String toString() {
        return "BudgetData{" +
                "budget=" + budget +
                ", grossWorld=" + grossWorld +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetData that = (BudgetData) o;
        return Objects.equals(getBudget(), that.getBudget()) &&
                Objects.equals(getGrossWorld(), that.getGrossWorld());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBudget(), getGrossWorld());
    }
}
