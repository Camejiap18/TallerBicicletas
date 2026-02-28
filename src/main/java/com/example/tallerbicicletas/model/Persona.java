package com.example.tallerbicicletas.model;

public class Persona {

    private String nombre;
    private String identificacion;

    public Persona(String nombre, String identificacion){
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    public String getNombre(){
        return nombre;
    }

    public String getIdentificacion(){
        return identificacion;
    }

    @Override
    public String toString() {
        return "nombre='" + nombre + '\'' + ", identificacion='" + identificacion + '\'';
    }



}
