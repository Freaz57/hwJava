package com.toys_market.Presenter;

import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;

import com.toys_market.Accessory.ToyParser;
import com.toys_market.Accessory.ToysRandom;
import com.toys_market.Exeption.ToyException;
import com.toys_market.Exeption.ToyFrequencyException;
import com.toys_market.Exeption.ToyParseException;
import com.toys_market.FileWork.FileWork;
import com.toys_market.FileWork.JsShopFileWork;
import com.toys_market.FileWork.JsWonFileWork;
import com.toys_market.Shop.Toy;
import com.toys_market.Shop.ToyController;
import com.toys_market.Shop.WonToyController;
import com.toys_market.View.View;

public class PresenterConsole {
    private View view;
    private ToyController toys;
    private WonToyController wonToys;
    private FileWork<ToyController> fileWorkShop;
    private FileWork<WonToyController> fileWorkWon;
    private ToysRandom toysRandom;


    public PresenterConsole(View view, ToysRandom toysRandom) {
        this.view = view;
        this.toysRandom = toysRandom;
        fileWorkShop = new JsShopFileWork();
        fileWorkWon = new JsWonFileWork();
        toys = new ToyController();
        wonToys = new WonToyController();
    }

    public void CreateToy(){
        view.Set("Enter name of toy, loss frequency(0...100) and amount(>0) of toys via space: \n"+
                    "Name 35 2 \n");
        while (true)
        try {
            Toy toy = new ToyParser().Parse(view.Get());
            if (toys.AddToy(toy)){ 
                view.Set("Add " +toy.getAmount() +" " + toy.getToyName() + ".");
            } else view.Set(toy.getToyName() + " add  to the store.");
            break;
        } catch (ToyParseException e) {
            view.Set(e.getMessage() + " Re-enter (Name frequency(0...100) amount(>0)): ");
        } catch (ToyException e){
            view.Set(e.getMessage() + " Re-enter (Name frequency(0...100) amount(>0)): ");
        }
    }

    public void LoadToys(){
        try {
            this.toys = fileWorkShop.ReadFile("ToysInShop.json");
            view.Set("Toys load in the shop");  
        } catch (Exception e) {
           view.Set(e.getMessage());
        }
        try {
            this.wonToys = fileWorkWon.ReadFile("WonToys.json");
            view.Set("Toys add to the wonList");     
        } catch (Exception e) {
            view.Set(e.getMessage());
        }
    }

    public void SaveToys(){
        try {
            fileWorkShop.WriteFile("ToysInShop.json",toys);
            view.Set("The stores toys were saved");
        } catch (Exception e) {
            view.Set(e.getMessage());
        }
        try {
            fileWorkWon.WriteFile("WonToys.json",wonToys);
            view.Set("The winList toys were saved");
        } catch (Exception e) {
            view.Set(e.getMessage());
        }
        
    }

    public void RemoveToy(){
        ArrayList<Integer> toyId = ShowToy();
        view.Set("Enter number of toy for remove this toy: ");
        boolean stop = false;
        while (!stop) {
            try {
                Integer IdToRemove = toyId.get(Integer.parseInt(view.Get())-1);
                this.toys.RemoveToy(IdToRemove);
                view.Set("Remove was completed");
                stop=true;
            } catch (NumberFormatException e) {
                view.Set(e.getMessage() + " Re-enter number of toy for remove ");
            }  
        }
    }

    private ArrayList<Integer> ShowToy(){
        ArrayList<Integer> toyId = new ArrayList<>();
        int i = 1;
        for (Toy toy: this.toys.getToys()){
            view.Set(i + ".) " + toy.getToyName() + "\n");
            toyId.add(toy.getToyId());
            i++;
        }
        return toyId;
    }

    public void EditFrequencyToy(){
        ArrayList<Integer> toyId = ShowToy();
        view.Set("Enter number of toy for edit frequency: ");
        Integer IdToEdit=null;
        Integer newFrequency=null;
        while (true) {
            try {
                IdToEdit = toyId.get(Integer.parseInt(view.Get())-1);
                 break;}
            catch (RuntimeException e) {
                view.Set("Re-enter number of toy for remove ");
            }} 
            Toy editToy = this.toys.SearchToy(IdToEdit);
            while (true){
                view.Set(editToy.getToyName() + 
                    " have frequency = " + editToy.getFrequency() + ".");
                view.Set("Enter new frequency(0..100)");
                try {
                    newFrequency = Integer.parseInt(view.Get());
                    if ((newFrequency<=0)||(newFrequency>=100))
                        throw new ToyFrequencyException("Invalid frequency value - " +
                        newFrequency + " of the " + editToy.getToyName() + " A value >" + 
                " 0 and less than 100 is allowed \n",newFrequency);
                    break;
                    }
                catch(ToyFrequencyException e){
                    view.Set(e.getMessage());
                }
                catch (RuntimeException e) {
                    view.Set("Frequency entered incorrectly");
                } 
            }
            editToy.setFrequency(newFrequency);
            this.toys.RemoveToy(IdToEdit);
            this.toys.AddToy(editToy);
            view.Set("The changes were completed correctly");
            }

    public void PlayToy(){
        view.Set("Lets go");
        Integer winId = this.toysRandom.GetRandom(toys.getToys());
        Map<Toy,Boolean> result = this.toys.GetToy(winId);
        Toy wonToy = null;
        String name = null;
        for (Toy item:result.keySet()){
            name = item.getToyName();
            Integer frequency = item.getFrequency();
            wonToy = new Toy(1,frequency,name,1);
            if (result.get(item))
                view.Set("You win last " + name + " in the shop.");    
        }
        if (this.wonToys.AddToy(wonToy))
            view.Set("You are win one more " + name + ".");
        else view.Set("You are win " + name + ".");
    }

    public PriorityQueue<Toy> getShopToys() {
        PriorityQueue<Toy> toys = new PriorityQueue<>();
        for (Toy item:this.toys.getToys())
            toys.add(item);
        return toys;
    }

    public PriorityQueue<Toy> getWonToys() {
        PriorityQueue<Toy> wontoys = new PriorityQueue<>();
        for (Toy item:this.wonToys.getToys())
           wontoys.add(item);
        return wontoys;
    }

}
