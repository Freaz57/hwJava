package com.toys_market.Shop;
/**
 * Toy for shop
 */
public class Toy implements Comparable<Toy> {
    private Integer toyId,frequency,amount;
    private String toyName;


    public Toy(Integer toyId, Integer frequency, 
            String toyName,Integer amount) {
        this.toyId = toyId;
        this.frequency = frequency;
        this.toyName = toyName;
        this.amount = amount;
    }

    public Toy(Integer frequency, 
            String toyName,Integer amount) {
        this(null,frequency, toyName, amount);
    }


    public Integer getToyId() {
        return this.toyId;
    }

    public void setToyId(Integer toyId) {
        this.toyId = toyId;
    }

    public Integer getFrequency() {
        return this.frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getToyName() {
        return this.toyName;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(Toy o) {
        return Integer.compare(this.frequency, o.getFrequency());        
    }

    @Override
    public boolean equals(Object o){
        Toy toy = (Toy) o;
        return ((this.toyName.equalsIgnoreCase(toy.toyName)));
    } 

    @Override
    public String toString() {
        return "{" +
            " toyId ='" + getToyId() + "'" +
            ", frequency ='" + getFrequency() + "'" +
            ", toyName ='" + getToyName() + "'" +
            ", amount ='" + getAmount() + "'" +"}";
    }


    


}
