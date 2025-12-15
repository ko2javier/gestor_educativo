package com.example.gestoreducativo;

public class Modulo {
    private String idModulo;
    private String nombreCorto;

    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        this.idModulo = idModulo;
    }

    private String nombreCompleto;
    private int numeroHoras;

    public Modulo() { }

    public Modulo(String idModulo, String nombreCompleto, String nombreCorto, int numeroHoras) {
        this.idModulo = idModulo;
        this.nombreCompleto = nombreCompleto;
        this.nombreCorto = nombreCorto;
        this.numeroHoras = numeroHoras;
    }

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
