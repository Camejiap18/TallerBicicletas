package com.example.tallerbicicletas.model;

import java.util.ArrayList;
import java.util.List;

public class Taller {

    // Atributos del Taller
    private String nombre;
    private String direccion;
    private String nit;

    // Listas del taller
    private List<Cliente> clientes;
    private List<Mecanico> mecanicos;
    private List<Bicicleta> bicicletas;
    private List<Orden> ordenes;

    // Constructor
    public Taller(String nombre, String direccion, String nit) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.nit = nit;

        this.clientes = new ArrayList<>();
        this.mecanicos = new ArrayList<>();
        this.bicicletas = new ArrayList<>();
        this.ordenes = new ArrayList<>();
    }

    // Métodos para agregar
    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void agregarMecanico(Mecanico mecanico) {
        mecanicos.add(mecanico);
    }

    public void agregarBicicleta(Bicicleta bicicleta) {
        bicicletas.add(bicicleta);
    }

    public void agregarOrden(Orden orden) {
        ordenes.add(orden);
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNit() {
        return nit;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Mecanico> getMecanicos() {
        return mecanicos;
    }

    public List<Bicicleta> getBicicletas() {
        return bicicletas;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    // Contadores
    public int getCantidadMecanicos() {
        return mecanicos.size();
    }

    public int getCantidadOrdenes() {
        return ordenes.size();
    }

    // toString
    @Override
    public String toString() {
        return "Taller: " + nombre +
                "\nDirección: " + direccion +
                "\nNIT: " + nit +
                "\nClientes registrados: " + clientes.size() +
                "\nMecánicos registrados: " + mecanicos.size() +
                "\nBicicletas registradas: " + bicicletas.size() +
                "\nÓrdenes registradas: " + ordenes.size();
    }
}
