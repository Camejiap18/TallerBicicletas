package com.example.tallerbicicletas.model;

public class Taller {

    private String nombre;
    private String direccion;
    private String nit;

    public Taller(String nombre, String direccion, String nit){
        this.nombre = nombre;
        this.direccion = direccion;
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNit() {
        return nit;
    }
}
