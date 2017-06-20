package com.bestdeals.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by arif on 18/06/2017.
 */
@Entity
public class Deal {

  @Id
  @GeneratedValue
  int Id;
  private double principle;
  private double rate;
  private int time;
  private BigDecimal dealAmount;
  private BigDecimal dealInterestAmount;
  private BigDecimal baseCurrencyAmount;
  private BigDecimal baseCurrencyInterestAmount;
  private String interestType;
  private double compoundingPeriod;
  private String fromCurrency;
  private String toCurrency;
  private LocalDateTime createdOn;
  private Double fxRate;

  public int getId() {
    return Id;
  }

  public void setId(int id) { this.Id = id; }

  public double getPrinciple() {
    return principle;
  }

  public void setPrinciple(double principle) {
    this.principle = principle;
  }

  public double getRate() {
    return rate;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public BigDecimal getDealInterestAmount() {
    return dealInterestAmount;
  }

  public void setDealInterestAmount(BigDecimal dealInterestAmount) {
    this.dealInterestAmount = dealInterestAmount;
  }

  public BigDecimal getBaseCurrencyAmount() {
    return baseCurrencyAmount;
  }

  public void setBaseCurrencyAmount(BigDecimal baseCurrencyAmount) {
    this.baseCurrencyAmount = baseCurrencyAmount;
  }

  public BigDecimal getBaseCurrencyInterestAmount() {
    return baseCurrencyInterestAmount;
  }

  public void setBaseCurrencyInterestAmount(BigDecimal baseCurrencyInterestAmount) {
    this.baseCurrencyInterestAmount = baseCurrencyInterestAmount;
  }

  public String getFromCurrency() {
    return fromCurrency;
  }

  public void setFromCurrency(String fromCurrency) {
    this.fromCurrency = fromCurrency;
  }

  public String getToCurrency() {
    return toCurrency;
  }

  public void setToCurrency(String toCurrency) {
    this.toCurrency = toCurrency;
  }

  public String getInterestType() {
    return interestType;
  }

  public void setInterestType(String interestType) {
    this.interestType = interestType;
  }

  public double getCompoundingPeriod() {
    return compoundingPeriod;
  }

  public void setCompoundingPeriod(double compoundingPeriod) {
    this.compoundingPeriod = compoundingPeriod;
  }

  public BigDecimal getDealAmount() {
    return dealAmount;
  }

  public void setDealAmount(BigDecimal dealAmount) {
    this.dealAmount = dealAmount;
  }

  public LocalDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public Double getFxRate() {
    return fxRate;
  }

  public void setFxRate(Double fxRate) {
    this.fxRate = fxRate;
  }
}