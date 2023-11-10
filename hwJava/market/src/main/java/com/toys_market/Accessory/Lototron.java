package com.toys_market.Accessory;
import com.toys_market.Shop.Toy;
import java.util.Collection;

public interface Lototron<T extends Toy> {
    
    public Integer GetRandom(Collection<T> collection);
}
