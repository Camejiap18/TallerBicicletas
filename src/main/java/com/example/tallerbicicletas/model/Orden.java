package com.example.tallerbicicletas.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class Orden {

    //Atributos de la órden
    private Cliente cliente;
    private Bicicleta bicicleta;
    private Mecanico mecanico;
    private String descripcionProblema;
    private String estado;
    private LocalDate fechaIngreso;
    private LocalTime horaIngreso;
    private String motivoServicio;
    private String diagnostico;
    private ArrayList<String> trabajosRealizados;
    private double costoTotal;

    //Constructor
    public Orden(Cliente cliente, Bicicleta bicicleta, Mecanico mecanico,
                 String descripcionProblema, String motivoServicio) {

        this.cliente = cliente;
        this.bicicleta = bicicleta;
        this.mecanico = mecanico;

        this.descripcionProblema = descripcionProblema;
        this.motivoServicio = motivoServicio;

        this.fechaIngreso = LocalDate.now();
        this.horaIngreso = LocalTime.now();

        this.diagnostico = " ";
        this.trabajosRealizados = new ArrayList<>();
        this.costoTotal = 0.0;

        this.estado = "Pendiente";
    }

    //Método para agregar un trabajo
    public void agregarTrabajo(String trabajo, double costo) {
        if (trabajo != null && !trabajo.isEmpty() && costo > 0) {
            trabajosRealizados.add(trabajo);
            costoTotal += costo;
            if (estado.equals("Pendiente")) {
                estado = "En proceso";
            }
        }
    }

    //Método para actualizar el estado de la óden
    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    //Método para finalizar la órdden
    public void cerrarOrden(String diagnosticoFinal) {
        if (!trabajosRealizados.isEmpty()) {
            this.diagnostico = diagnosticoFinal;
            this.estado = "Finalizada";
        } else {
            System.out.println("No se puede cerrar una orden sin trabajos realizados.");
        }
    }

    //Método para que se vea bonita la órden por fecha
    public String toTextoBonito() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return  "Cliente: " + cliente.getNombre() + " (" + cliente.getIdentificacion() + ")\n"
                + "Bicicleta: " + bicicleta.getMarca() + " " + bicicleta.getModelo()
                + " | Serial: " + bicicleta.getSerial()
                + " | Tipo: " + bicicleta.getTipo() + "\n"
                + "Mecánico: " + mecanico.getNombre() + " (" + mecanico.getIdentificacion() + ")"
                + " | Esp: " + mecanico.getEspecialidad() + "\n"
                + "Fecha ingreso: " + fechaIngreso.format(f) + "  Hora: " + horaIngreso + "\n"
                + "Motivo: " + motivoServicio + "\n"
                + "Problema: " + descripcionProblema + "\n"
                + "Estado: " + estado + "\n"
                + "Costo: " + costoTotal + "\n";
    }

    //Getters y settters
    public String getDescripcionProblema() {
        return descripcionProblema;
    }

    public void setDescripcionProblema(String descripcionProblema) {
        this.descripcionProblema = descripcionProblema;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public String getMotivoServicio() {
        return motivoServicio;
    }



    //Método toString
    @Override
    public String toString() {
        return "Orden{" +
                "cliente=" + cliente +
                ", bicicleta=" + bicicleta +
                ", mecanico=" + mecanico +
                ", problema='" + descripcionProblema + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
