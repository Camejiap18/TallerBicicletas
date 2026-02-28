package com.example.tallerbicicletas.model;

import java.util.ArrayList;
import java.util.List;

public class Taller {

    private String nombre;
    private String direccion;
    private String nit;

    private List<Cliente> clientes;

    public Taller(String nombre, String direccion, String nit){
        this.nombre = nombre;
        this.direccion = direccion;
        this.nit = nit;
        this.clientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
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

    @Override
    public String toString() {
        return "Taller: " + nombre +
                "\nDirección: " + direccion +
                "\nNIT: " + nit +
                "\nClientes registrados: " + clientes.size();
    }
}
