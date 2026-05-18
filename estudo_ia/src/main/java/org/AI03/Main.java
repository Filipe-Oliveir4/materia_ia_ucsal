package org.AI03;

import org.AI02.BuscaAEstrela;
import org.AI02.Coordenada;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int linhas = 4;
        int colunas = 4;
        int[][] grade = new int[linhas][colunas];

        grade[1][1] = 1;
        grade[1][3] = 1;

        Coordenada inicio = new Coordenada(0, 0);
        Coordenada objetivo = new Coordenada(3, 3);

        System.out.println("Calculando a melhor rota...");
        List<Coordenada> caminho = BuscaAEstrela.encontrarCaminho(grade, inicio, objetivo);

        if (caminho.isEmpty()) {
            System.out.println("Nenhum caminho foi encontrado! O objetivo está inacessível.");
        } else {
            System.out.println("Caminho encontrado com sucesso! Total de movimentos: " + (caminho.size() - 1));


            List<Coordenada> caminhoParcial = new ArrayList<>();

            for (int p = 0; p < caminho.size(); p++) {
                Coordenada passoAtual = caminho.get(p);
                caminhoParcial.add(passoAtual);

                System.out.println("\n--- Passo " + p + " -> Coordenada: (" + passoAtual.getX() + ", " + passoAtual.getY() + ") ---");
                imprimirMapa(grade, caminhoParcial, inicio, objetivo, passoAtual);
            }
        }
    }

    private static void imprimirMapa(int[][] grade, List<Coordenada> caminhoAteAgora, Coordenada inicio, Coordenada objetivo, Coordenada posicaoAtual) {
        System.out.println("Legenda: S = Início | G = Objetivo | * = Rastros | X = Posição Atual | █ = Ponto Bloqueado");

        for (int i = 0; i < grade.length; i++) {
            for (int j = 0; j < grade[0].length; j++) {
                Coordenada atual = new Coordenada(i, j);

                if (atual.equals(posicaoAtual)) {
                    System.out.print(" X ");
                } else if (atual.equals(inicio)) {
                    System.out.print(" S ");
                } else if (atual.equals(objetivo)) {
                    System.out.print(" G ");
                } else if (caminhoAteAgora.contains(atual)) {
                    System.out.print(" * ");
                } else if (grade[i][j] == 1) {
                    System.out.print("███");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }
}