package com.example.gestoreducativo;

public class Modulo {
    private String nombreCorto;
    private String nombreCompleto;
    private int numeroHoras;

    public Modulo(String nombreCorto, String nombreCompleto, int numeroHoras) {
        this.nombreCorto = nombreCorto;
        this.nombreCompleto = nombreCompleto;
        this.numeroHoras = numeroHoras;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(int numeroHoras) {
        this.numeroHoras = numeroHoras;
    }
}
