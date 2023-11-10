package com.toys_market.Accessory;
import java.util.Collection;
import com.toys_market.Shop.Toy;
import java.util.Random;

public class ToysRandom implements Lototron<Toy> {
    

    @Override
    public Integer GetRandom(Collection<Toy> collection) {
        Integer numberOfItem;
        Integer[] toysId, toysFrequency;
        String[] toysName;
        numberOfItem = collection.size();
        toysId = new Integer[numberOfItem];
        toysFrequency = new Integer[numberOfItem];
        toysName = new String[numberOfItem];
        int i = 0;
        for (Toy item: collection){
            toysId[i] = item.getToyId();
            toysFrequency[i] = item.getFrequency();
            toysName[i] = item.getToyName();
            i++;
        }
        Integer summ = 0;
        for (Integer item: toysFrequency)
            summ+=item;
        int randomNumber = new Random().nextInt(summ);
        i=0;
        while (true) {
        if ((randomNumber>=summ-toysFrequency[i])&&(randomNumber<summ))
            return toysId[i];
        summ-=toysFrequency[i];    
        i++;
        }   
        }
    }
    
