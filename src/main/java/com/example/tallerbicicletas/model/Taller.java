package com.example.tallerbicicletas.model;

import java.util.ArrayList;
import java.util.List;

public class Taller {

    //Atributos del Taller
    private String nombre;
    private String direccion;
    private String nit;

    //listas del taller
    private List<Cliente> clientes;
    private ArrayList<Mecanico> mecanicos = new ArrayList<>();
    private List<Bicicleta> bicicletas = new ArrayList<>();

    //Constructor
    public Taller(String nombre, String direccion, String nit){
        this.nombre = nombre;
        this.direccion = direccion;
        this.nit = nit;
        this.clientes = new ArrayList<>();
    }
    //Método para agregar clientes
    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    //Método para agregar mecánicos
    public void agregarMecanico(Mecanico mecanico) {
        mecanicos.add(mecanico);
    }

    //Método para ver cuántos mecánicos hay
    public int getCantidadMecanicos() {
        return mecanicos.size();
    }

    //Método para agregar bicicletas
    public void agregarBicicleta(Bicicleta bicicleta){
        bicicletas.add(bicicleta);
    }

    //Getters y setters
    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNit() {
        return nit;
    }

    //Método toString
    @Override
    public String toString() {
        return "Taller: " + nombre +
                "\nDirección: " + direccion +
                "\nNIT: " + nit +
                "\nClientes registrados: " + clientes.size() +
                "\nMecánicos registrados: " + mecanicos.size() +
                "\nBicicletas registradas: " + bicicletas.size();
    }
}
