package com.toys_market.FileWork;

import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.toys_market.Shop.ToyController;

public class JsShopFileWork implements FileWork<ToyController> {
    Gson gson;

    @Override
    public ToyController ReadFile(String fileName) throws IOException, FileNotFoundException{
        File file = new File(fileName);
        if (!file.exists())
            file.createNewFile();
        gson = new Gson();
        FileReader reader = new FileReader(fileName);
        ToyController toys = gson.fromJson(reader, ToyController.class);
        reader.close();
        if (toys==null)
            throw new IOException("List of stores toys is empty");
        return toys;
    }

    @Override
    public void WriteFile(String fileName, ToyController toys) throws IOException{
        gson = new Gson();
        FileWriter writer = new FileWriter(fileName);
        writer.write(gson.toJson(toys));
        writer.close();
        }
    }
    
