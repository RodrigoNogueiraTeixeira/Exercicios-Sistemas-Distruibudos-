package com.rodrigo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o CEP (somente números): ");
        String cep = scanner.nextLine().trim();

        // Remove qualquer caractere que não seja número
        cep = cep.replaceAll("\\D", "");

        if (cep.length() != 8) {
            System.out.println("CEP inválido! O CEP deve ter 8 dígitos.");
            scanner.close();
            return;
        }

        BuscaCepTask task = new BuscaCepTask(cep);
        Thread threadBusca = new Thread(task);

        // Inicia a busca em outra thread
        threadBusca.start();

        // Animação (spinner) enquanto a thread da busca estiver rodando
        char[] spinner = { '|', '/', '-', '\\' };
        int i = 0;

        try {
            while (threadBusca.isAlive()) {
                System.out.print("\rBuscando informações do CEP... " + spinner[i % spinner.length]);
                i++;
                Thread.sleep(120);
            }

            // Garante que a thread terminou
            threadBusca.join();

            // Limpa a linha da animação (opcional)
            System.out.print("\r                                      \r");

            if (task.getErro() != null) {
                System.out.println("Erro ao buscar CEP: " + task.getErro().getMessage());
                scanner.close();
                return;
            }

            EnderecoViaCep endereco = task.getEndereco();

            if (endereco == null) {
                System.out.println("Não foi possível obter resposta da API.");
                scanner.close();
                return;
            }

            if (Boolean.TRUE.equals(endereco.erro)) {
                System.out.println("CEP não encontrado.");
                scanner.close();
                return;
            }

            System.out.println("Resultado:");
            System.out.println("Logradouro: " + endereco.logradouro
                    + ", bairro: " + endereco.bairro
                    + ", cidade: " + endereco.localidade
                    + " e uf: " + endereco.uf + ".");

        } catch (InterruptedException e) {
            System.out.println("A execução foi interrompida.");
        } finally {
            scanner.close();
        }

    }
}