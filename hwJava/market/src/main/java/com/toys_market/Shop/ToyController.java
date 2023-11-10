package com.toys_market.Shop;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import com.toys_market.Exeption.ToyException;
import com.toys_market.Exeption.ToyFrequencyException;

public class ToyController {
    private PriorityQueue<Toy> toys;

    public ToyController() {
        this.toys = new PriorityQueue<>();
    }

    protected Integer GetId(PriorityQueue<Toy> toys){
        if (toys==null)
            return 1;
        Integer toyId = 1;
        for (Toy items:toys){
            if (toyId <= items.getToyId())
                toyId=items.getToyId()+1;
        }
        return toyId;
    }

    public boolean CreatedToy(String toyName, Integer frequency, Integer amount) throws ToyException{
        if (amount<=0) 
            throw new ToyException("The number of toys must be more than 0 \n");
        boolean toyInShop = false;
        if (this.toys!=null) 
            for (Toy item:this.toys){
                if ((item.getToyName().equalsIgnoreCase(toyName))&&
                    (item.getFrequency()==frequency)) {
                    Integer sumAmount = item.getAmount()+amount;
                    item.setAmount(sumAmount);
                    toyInShop = true;
                    return toyInShop;
                }
            }
        if ((frequency<0)||(frequency>100))
            throw new ToyFrequencyException(
                "Invalid frequency value - " + frequency + 
                "of the " + toyName + "A value >" + 
                "0 and less than 100 is allowed \n", frequency);
        if (!toyInShop){
            Integer toyId = this.GetId(this.toys);
            Toy toy = new Toy(toyId, frequency,toyName,amount);
            this.toys.add(toy);}
        return toyInShop; 
    }

    public boolean AddToy(Toy toy) throws ToyException{
        boolean toyInShop = true;
        if (this.toys.contains(toy))
            for (Toy itemToy : toys) {
                if (itemToy.equals(toy)) {
                    Integer amount = itemToy.getAmount()+toy.getAmount();
                    itemToy.setAmount(amount);
                    return toyInShop;
                }
        }
        return CreatedToy(toy.getToyName(), toy.getFrequency(), toy.getAmount());
        }

    public void AddToys(Collection<Toy> toys){
        for (Toy item: toys)
            AddToy(item);
    }
    /**
     * Return Toy by ToyID
     * @param toyId ToyId
     * @return Toy
     */
    public Map<Toy,Boolean> GetToy(Integer toyId){
        Map<Toy, Boolean> result = new HashMap<>();
        boolean toyAreOver = false;
        for (Toy item: this.toys)
            if (item.getToyId()==toyId) {
                Integer amount = item.getAmount()-1;
                item.setAmount(amount);
                if (amount==0) {
                    toyAreOver = true;
                    RemoveToy(toyId);
                    result.put(item,toyAreOver);
                    return result;}
                else {
                    result.put(item, toyAreOver);
                    return result;}
            }
        throw new ToyException("Toys with ID" + toyId + 
        "are not in the store");
    }
    
    /**
     * Remove toy by ToyId
     */
    public Toy SearchToy(Integer toyId){
        for (Toy item: this.toys)
            if (item.getToyId()==toyId)
                    return item;
        throw new ToyException("Toys with ID" + toyId + 
        "are not in the store");
    }

    public void RemoveToy(Integer toyId){
        if (!this.toys.removeIf(e->e.getToyId()==toyId))
            throw new ToyException("Toys with ID " + toyId + 
        " are not in the store");
    }

    public PriorityQueue<Toy> getToys() {
        return this.toys;
    }

    public void setToys(Collection<Toy> toys) {
        this.toys = new PriorityQueue<>(toys);
    }

    @Override
    public String toString() {
        return "{" +
            " toys='" + getToys() + "'" +
            "}";
    }

      
}
