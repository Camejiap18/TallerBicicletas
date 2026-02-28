package com.example.tallerbicicletas.model;

public class Mecanico extends Persona{

    private String especialidad;
    private double sueldo;

    public Mecanico(String nombre, String identificacion, String especialidad, double sueldo){
        super(nombre, identificacion);
        this.especialidad = especialidad;
        this.sueldo = sueldo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Mecanico{" +
                "nombre='" + getNombre() + '\'' +
                ", identificacion='" + getIdentificacion() + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", sueldo=" + sueldo +
                '}';
    }
}
