package com.example.tallerbicicletas.model;

import com.example.tallerbicicletas.model.Cliente;
import com.example.tallerbicicletas.model.TipoBicicleta;

public class Bicicleta {

    private String marca;
    private String modelo;
    private String color;
    private String serial;
    private TipoBicicleta tipo;
    private Cliente cliente;
    private int anio;

    public Bicicleta(String marca, String modelo, String color,
                     String serial, TipoBicicleta tipo, Cliente cliente, int anio) {

        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.serial = serial;
        this.tipo = tipo;
        this.cliente = cliente;
        this.anio = anio;
    }

    public String getMarca() {return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getSerial() { return serial; }
    public void setSerial(String serial) { this.serial = serial; }

    public TipoBicicleta getTipo() { return tipo; }
    public void setTipo(TipoBicicleta tipo) { this.tipo = tipo; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    @Override
    public String toString() {
        String dueno = (cliente != null) ? cliente.getNombre() : "Sin cliente";
        return serial + " - " + marca + " " + modelo + " (" + tipo + ", " + anio + ") - " + dueno;
    }
}