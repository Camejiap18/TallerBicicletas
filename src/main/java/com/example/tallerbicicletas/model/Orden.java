package com.example.tallerbicicletas.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class Orden {

    // Atributos de la orden
    private Cliente cliente;
    private Bicicleta bicicleta;
    private Mecanico mecanico;

    private String motivoServicio;
    private String descripcionProblema;

    private String diagnostico; // diagnóstico (se puede actualizar al cerrar)
    private ArrayList<String> trabajosRealizados; // lista de trabajos
    private double costoTotal;

    private String estado; // Pendiente, En proceso, Finalizada

    private LocalDate fechaIngreso;
    private LocalTime horaIngreso;

    // Constructor
    public Orden(Cliente cliente, Bicicleta bicicleta, Mecanico mecanico,
                 String descripcionProblema, String motivoServicio) {

        this.cliente = cliente;
        this.bicicleta = bicicleta;
        this.mecanico = mecanico;

        this.descripcionProblema = descripcionProblema;
        this.motivoServicio = motivoServicio;

        this.fechaIngreso = LocalDate.now();
        this.horaIngreso = LocalTime.now();

        this.diagnostico = ""; // vacío al inicio
        this.trabajosRealizados = new ArrayList<>();
        this.costoTotal = 0.0;

        this.estado = "Pendiente";
    }

    // Agregar un trabajo (y sumar costo)
    public void agregarTrabajo(String trabajo, double costo) {
        if (trabajo != null && !trabajo.trim().isEmpty() && costo >= 0) {
            trabajosRealizados.add(trabajo + " ($" + costo + ")");
            costoTotal += costo;

            if (estado.equals("Pendiente")) {
                estado = "En proceso";
            }
        }
    }

    // Cambiar estado manualmente
    public void cambiarEstado(String nuevoEstado) {
        if (nuevoEstado != null && !nuevoEstado.trim().isEmpty()) {
            this.estado = nuevoEstado;
        }
    }

    // Cerrar la orden (poner diagnóstico final y marcar finalizada)
    public void cerrarOrden(String diagnosticoFinal) {
        if (!trabajosRealizados.isEmpty()) {
            this.diagnostico = diagnosticoFinal;
            this.estado = "Finalizada";
        } else {
            System.out.println("No se puede cerrar una orden sin trabajos realizados.");
        }
    }

    // Texto bonito para mostrar en TextArea (historial / por fecha)
    public String toTextoBonito() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter h = DateTimeFormatter.ofPattern("HH:mm");

        StringBuilder sb = new StringBuilder();

        sb.append("Cliente: ").append(cliente.getNombre())
                .append(" (").append(cliente.getIdentificacion()).append(")\n");

        sb.append("Bicicleta: ").append(bicicleta.getMarca()).append(" ").append(bicicleta.getModelo())
                .append(" | Serial: ").append(bicicleta.getSerial())
                .append(" | Tipo: ").append(bicicleta.getTipo()).append("\n");

        sb.append("Mecánico: ").append(mecanico.getNombre())
                .append(" (").append(mecanico.getIdentificacion()).append(")")
                .append(" | Esp: ").append(mecanico.getEspecialidad()).append("\n");

        sb.append("Fecha ingreso: ").append(fechaIngreso.format(f))
                .append("  Hora: ").append(horaIngreso.format(h)).append("\n");

        sb.append("Motivo: ").append(motivoServicio).append("\n");
        sb.append("Problema: ").append(descripcionProblema).append("\n");

        sb.append("Diagnóstico: ").append(diagnostico.isBlank() ? "(sin diagnóstico)" : diagnostico).append("\n");

        sb.append("Trabajos realizados:\n");
        if (trabajosRealizados.isEmpty()) {
            sb.append("  - (sin trabajos)\n");
        } else {
            for (String t : trabajosRealizados) {
                sb.append("  - ").append(t).append("\n");
            }
        }

        sb.append("Estado: ").append(estado).append("\n");
        sb.append("Costo total: $").append(costoTotal).append("\n");

        return sb.toString();
    }

    // --- Getters y Setters ---

    public Cliente getCliente() {
        return cliente;
    }

    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public String getDescripcionProblema() {
        return descripcionProblema;
    }

    public void setDescripcionProblema(String descripcionProblema) {
        this.descripcionProblema = descripcionProblema;
    }

    public String getMotivoServicio() {
        return motivoServicio;
    }

    public void setMotivoServicio(String motivoServicio) {
        this.motivoServicio = motivoServicio;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public ArrayList<String> getTrabajosRealizados() {
        return trabajosRealizados;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public LocalTime getHoraIngreso() {
        return horaIngreso;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "cliente=" + cliente +
                ", bicicleta=" + bicicleta +
                ", mecanico=" + mecanico +
                ", motivo='" + motivoServicio + '\'' +
                ", problema='" + descripcionProblema + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", horaIngreso=" + horaIngreso +
                ", costoTotal=" + costoTotal +
                '}';
    }
}
