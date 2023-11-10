package com.toys_market.Accessory;

import com.toys_market.Exeption.ToyParseException;
import com.toys_market.Shop.Toy;

public class ToyParser {
    private String[] list;

    public ToyParser(){

    } 
    public Toy Parse(String string) throws ToyParseException{
        String toyName;
        Integer frequency = null;
        Integer amount = null;
        int length = string.split(" ").length;
        if (length!=3)
            throw new ToyParseException("Incorrect amount of data entered");
        this.list = string.split(" ", 0);
        toyName = list[0];
        try {
            frequency = Integer.parseInt(list[1]);
        } catch (RuntimeException e) {
            throw new ToyParseException("The frequency entered is incorrect");
        }
        try {
            amount = Integer.parseInt(list[2]);
        } catch (RuntimeException e) {
           throw new ToyParseException("The amount entered incorrect");
        }
        return new Toy(frequency,toyName,amount);
    }
    
}
