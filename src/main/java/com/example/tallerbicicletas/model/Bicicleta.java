package com.example.tallerbicicletas.model;

public class Bicicleta {

    private String marca;
    private String modelo;
    private String color;
    private String serial;
    private TipoBicicleta tipo;

    //Constructor
    public Bicicleta(String marca, String modelo, String color, String serial, TipoBicicleta tipo) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.serial = serial;
        this.tipo = tipo;
    }

    //Getters y setterss
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public TipoBicicleta getTipo() {
        return tipo;
    }

    //Método toString
    @Override
    public String toString() {
        return "Bicicleta{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", serial='" + serial + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
