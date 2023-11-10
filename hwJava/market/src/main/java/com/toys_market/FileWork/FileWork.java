package com.toys_market.FileWork;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.toys_market.Shop.ToyController;

public interface FileWork<T extends ToyController> {
    
    public T ReadFile(String fileName) throws IOException, FileNotFoundException;
    public void WriteFile(String fileName, T toys) throws IOException;
}
