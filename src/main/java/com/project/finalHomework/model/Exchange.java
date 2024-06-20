package com.project.finalHomework.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("table")
    private String t;
    private String currency;
    private String code;
    @JsonProperty("rates")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Rate> rate;
    private LocalDateTime queryDate = LocalDateTime.now();;

    public Exchange() {
    }

    public Exchange(Long id, String t, String currency, String code, List<Rate> rate) {
        this.id = id;
        this.t = t;
        this.currency = currency;
        this.code = code;
        this.rate = rate;
    }

    public LocalDateTime getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(LocalDateTime queryDate) {
        this.queryDate = queryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Rate> getRate() {
        return rate;
    }

    public void setRate(List<Rate> rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "id=" + id +
                ", t='" + t + '\'' +
                ", currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", rate=" + rate +
                ", queryDate=" + queryDate +
                '}';
    }
}
