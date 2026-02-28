package com.example.tallerbicicletas.model;

public class Cliente extends Persona {

    private String telefono;
    private String direccion;

    //Constructor
    public Cliente(String nombre, String identificacion, String telefono, String direccion){
        super(nombre, identificacion);
        this.telefono = telefono;
        this.direccion = direccion;
    }

    //Getters
    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    //Método toString
    @Override
    public String toString() {
        return "Cliente{" + super.toString() +
                ", telefono='" + telefono +
                '\'' + ", direccion='" +
                direccion + '\'' +
                '}';
    }
}
