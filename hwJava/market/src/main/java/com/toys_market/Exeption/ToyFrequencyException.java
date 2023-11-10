package com.toys_market.Exeption;

public class ToyFrequencyException extends ToyException {

    private Integer frequency;
    /**
     * Invalid value of frequency
     * @param message Output message
     */
    public ToyFrequencyException(String message, Integer frequency) {
        super(message);
        this.frequency = frequency;
    }
    
    public Integer getFrequency(){
        return this.frequency;
    }
}
