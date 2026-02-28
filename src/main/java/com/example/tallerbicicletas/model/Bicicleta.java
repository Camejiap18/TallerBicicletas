package com.example.tallerbicicletas.model;

public class Bicicleta {

    private String marca;
    private String modelo;
    private String color;
    private String serial;

    //Constructor
    public Bicicleta(String marca, String modelo, String color, String serial) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.serial = serial;
    }

    //Getters y setters
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

    //Método toString
    @Override
    public String toString() {
        return "Bicicleta{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", serial='" + serial + '\'' +
                '}';
    }
}
