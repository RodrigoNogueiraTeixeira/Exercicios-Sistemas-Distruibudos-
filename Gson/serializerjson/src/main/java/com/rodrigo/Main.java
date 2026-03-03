package com.rodrigo;
import com.google.gson.Gson;
public class Main {
    public static void main(String[] args) {

        Pessoa pessoa = new Pessoa("Rodrigo", 20);

        Gson gson = new Gson();
        String json = gson.toJson(pessoa);

        System.out.println("Objeto serializado em JSON:");
        System.out.println(json);

    }
}