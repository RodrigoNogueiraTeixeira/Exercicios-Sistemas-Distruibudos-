package com.rodrigo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

public class BuscaCepTask implements Runnable {

    private final String cep;
    private EnderecoViaCep endereco;
    private Exception erro;

    public BuscaCepTask(String cep) {
        this.cep = cep;
    }

    @Override
    public void run() {
        try {
            String url = "https://viacep.com.br/ws/" + cep + "/json/";

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new IOException("Erro HTTP: " + response.statusCode());
            }

            Gson gson = new Gson();
            this.endereco = gson.fromJson(response.body(), EnderecoViaCep.class);

        } catch (Exception e) {
            this.erro = e;
        }
    }

    public EnderecoViaCep getEndereco() {
        return endereco;
    }

    public Exception getErro() {
        return erro;
    }

}
