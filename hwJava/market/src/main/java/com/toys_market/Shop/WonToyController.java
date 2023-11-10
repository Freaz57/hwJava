package com.toys_market.Shop;
import java.util.PriorityQueue;


public class WonToyController extends ToyController {
    private PriorityQueue<Toy> wonToys;


    public WonToyController() {
        this.wonToys = new PriorityQueue<>();
    }

    @Override
    public boolean AddToy(Toy toy){
        Integer id = super.GetId(this.wonToys);
        Toy addToy = new Toy(id,toy.getFrequency(), toy.getToyName(), 1);
        boolean toyInShop = true;
        if (wonToys.contains(addToy))
            for (Toy itemToy: this.wonToys) {
                if (addToy.equals(itemToy)) {
                    Integer amount = 1+itemToy.getAmount();
                    itemToy.setAmount(amount);
                    return toyInShop;
                }
            }
        toyInShop= false;
        this.wonToys.add(addToy);
        return toyInShop;
    }

    

    @Override
    public String toString() {
        return "{" +
            " wonToys='" + this.wonToys + "'" +
            "}";
    }

    @Override
    public PriorityQueue<Toy> getToys() {
        return this.wonToys;
    }

}
