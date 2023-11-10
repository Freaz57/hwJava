package com.toys_market.View;

import java.util.Scanner;

public class ConsoleView implements View{
    private Scanner in = new Scanner(System.in);
/**
 * Input-output infotmation via the console.
 * @return Entered string
 */
    @Override
    public String Get() {
        return in.nextLine();
    }

    @Override
    public void Set(String Value) {
        System.out.println(Value);
        
    }
    
}
