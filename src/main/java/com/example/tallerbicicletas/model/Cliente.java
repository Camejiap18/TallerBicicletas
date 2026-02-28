package com.example.tallerbicicletas.model;

public class Cliente extends Persona {

    private String telefono;
    private String direccion;

    public Cliente(String nombre, String identificacion, String telefono, String direccion){
        super(nombre, identificacion);
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }
}
