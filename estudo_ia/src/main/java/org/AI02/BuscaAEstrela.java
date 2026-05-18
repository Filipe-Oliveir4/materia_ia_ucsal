package org.AI02;

import java.util.*;

public class BuscaAEstrela {


    public static List<Coordenada> encontrarCaminho(int[][] grade, Coordenada inicio, Coordenada objetivo) {
        No noInicio = new No(inicio, 0.0, calcularHeuristica(inicio, objetivo), null);

        PriorityQueue<No> listaAberta = new PriorityQueue<>();
        Map<Coordenada, No> mapaAberto = new HashMap<>();
        Set<Coordenada> conjuntoFechado = new HashSet<>();

        listaAberta.add(noInicio);
        mapaAberto.put(inicio, noInicio);

        while (!listaAberta.isEmpty()) {
            No noAtual = listaAberta.poll();
            Coordenada posicaoAtual = noAtual.getCoordenada();
            mapaAberto.remove(posicaoAtual);

            if (posicaoAtual.equals(objetivo)) {
                return reconstruirCaminho(noAtual);
            }

            conjuntoFechado.add(posicaoAtual);

            for (Coordenada posicaoVizinho : obterVizinhosValidos(grade, posicaoAtual)) {

                if (conjuntoFechado.contains(posicaoVizinho)) {
                    continue;
                }

                double gProvisorio = noAtual.getG() + calcularHeuristica(posicaoAtual, posicaoVizinho);

                if (!mapaAberto.containsKey(posicaoVizinho)) {
                    No vizinho = new No(posicaoVizinho, gProvisorio, calcularHeuristica(posicaoVizinho, objetivo), noAtual);

                    listaAberta.add(vizinho);
                    mapaAberto.put(posicaoVizinho, vizinho);
                } else if (gProvisorio < mapaAberto.get(posicaoVizinho).getG()) {
                    No vizinho = mapaAberto.get(posicaoVizinho);

                    listaAberta.remove(vizinho);

                    vizinho.setG(gProvisorio);
                    vizinho.setPai(noAtual);

                    listaAberta.add(vizinho);
                }
            }
        }

        return new ArrayList<>();
    }

    private static double calcularHeuristica(Coordenada c1, Coordenada c2) {
        return Math.sqrt(Math.pow(c2.getX() - c1.getX(), 2) + Math.pow(c2.getY() - c1.getY(), 2));
    }

    private static List<Coordenada> obterVizinhosValidos(int[][] grade, Coordenada posicao) {
        List<Coordenada> vizinhos = new ArrayList<>();
        int linhas = grade.length;
        int colunas = grade[0].length;

        int[][] movimentosPossiveis = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1},
                {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
        };

        for (int[] mov : movimentosPossiveis) {
            int novoX = posicao.getX() + mov[0];
            int novoY = posicao.getY() + mov[1];

            if (novoX >= 0 && novoX < linhas && novoY >= 0 && novoY < colunas && grade[novoX][novoY] == 0) {
                vizinhos.add(new Coordenada(novoX, novoY));
            }
        }

        return vizinhos;
    }

    private static List<Coordenada> reconstruirCaminho(No noObjetivo) {
        List<Coordenada> caminho = new ArrayList<>();
        No atual = noObjetivo;

        while (atual != null) {
            caminho.add(0, atual.getCoordenada());
            atual = atual.getPai();
        }

        return caminho;
    }
}