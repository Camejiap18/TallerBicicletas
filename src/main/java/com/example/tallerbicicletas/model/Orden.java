package com.example.tallerbicicletas.model;

public class Orden {

    //Atributos de la órden
    private Cliente cliente;
    private Bicicleta bicicleta;
    private Mecanico mecanico;
    private String descripcionProblema;
    private String estado;

    //Constructor
    public Orden(Cliente cliente, Bicicleta bicicleta, Mecanico mecanico, String descripcionProblema) {
        this.cliente = cliente;
        this.bicicleta = bicicleta;
        this.mecanico = mecanico;
        this.descripcionProblema = descripcionProblema;
        this.estado = "Pendiente";
    }

    //Método para actualizar el estado de la óden
    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    //Getters y settters
    public String getDescripcionProblema() {
        return descripcionProblema;
    }

    public void setDescripcionProblema(String descripcionProblema) {
        this.descripcionProblema = descripcionProblema;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    //Método toString
    @Override
    public String toString() {
        return "Orden{" +
                "cliente=" + cliente +
                ", bicicleta=" + bicicleta +
                ", mecanico=" + mecanico +
                ", problema='" + descripcionProblema + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
