package org.AI02;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class No implements Comparable<No> {

    private Coordenada coordenada;
    private Double g;
    private Double h;
    private No pai;
    private Map<No, Double> vizinhos = new HashMap<>();

    public No(Coordenada coordenada, double g, double h, No pai){
        this.coordenada = coordenada;
        this.g = g;
        this.h = h;
        this.pai = pai;
    }

    public void addVizinho(No vizinho, double distancia) {
        vizinhos.put(vizinho, distancia);
    }

    public double getF() {
        return g + h;
    }
    @Override
    public int compareTo(No o) {
        return Double.compare(this.getF(), o.getF());
    }
}
