package com.example.tallerbicicletas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import com.example.tallerbicicletas.model.Orden;

public class Taller {

    //Atributos del Taller
    private String nombre;
    private String direccion;
    private String nit;

    //listas del taller
    private List<Cliente> clientes;
    private ArrayList<Mecanico> mecanicos = new ArrayList<>();
    private List<Bicicleta> bicicletas = new ArrayList<>();
    private ArrayList<Orden> ordenes;

    //Constructor
    public Taller(String nombre, String direccion, String nit){
        this.nombre = nombre;
        this.direccion = direccion;
        this.nit = nit;
        this.clientes = new ArrayList<>();
        this.ordenes = new ArrayList<>();
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

    //Método para agregar una órden
    public void agregarOrden(Orden orden) {
        ordenes.add(orden);
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

    public int getCantidadOrdenes() {
        return ordenes.size();
    }

    public ArrayList<Orden> getOrdenes() {
        return ordenes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Mecanico> getMecanicos() {
        return mecanicos;
    }

    public List<Bicicleta> getBicicletas() {
        return bicicletas;
    }

    //Método toString
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
