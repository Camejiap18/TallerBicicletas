package com.example.tallerbicicletas;

import com.example.tallerbicicletas.model.*;

public class PruebasClases {

    private static void ok(String nombre) {
        System.out.println("OK: " + nombre);
    }

    private static void fail(String nombre, String detalle) {
        System.out.println("FAIL: " + nombre + " -> " + detalle);
    }

    public static void main(String[] args) {

        // Datos base
        Taller t = new Taller("BiciCentral", "Armenia", "NIT");
        Cliente c = new Cliente("Camila", "123", "300", "Pereira");
        Mecanico m = new Mecanico("Luis", "M-01", "Frenos", 2000);
        Bicicleta b1 = new Bicicleta("GW", "Speed", "Rojo", "SER-1", TipoBicicleta.RUTA, c, 2022);

        // 1) Taller agrega cliente/bici/mecánico
        t.agregarCliente(c);
        t.agregarMecanico(m);
        t.agregarBicicleta(b1);

        if (t.getClientes().size() == 1)
            ok("Taller.agregarCliente");
        else
            fail("Taller.agregarCliente", "No aumentó la lista");

        // 2) Buscar bicicleta por serial
        Bicicleta encontrada = t.buscarBicicletaPorSerial("ser-1");

        if (encontrada != null && "SER-1".equals(encontrada.getSerial()))
            ok("Taller.buscarBicicletaPorSerial");
        else
            fail("Taller.buscarBicicletaPorSerial", "No encontró la bici por serial");

        // 3) Bicicleta equals por serial
        Bicicleta b2 = new Bicicleta("Trek", "X", "Azul", "SER-1", TipoBicicleta.MONTAÑA, c, 2020);

        if (b1.equals(b2))
            ok("Bicicleta.equals por serial");
        else
            fail("Bicicleta.equals por serial", "Bicis con mismo serial no son iguales");

        // 4) Orden crea fecha/hora y suma costo
        Orden o = new Orden(c, b1, m, "No frena", "Revisión");

        if (o.getFechaIngreso() != null && o.getHoraIngreso() != null)
            ok("Orden asigna fecha y hora");
        else
            fail("Orden asigna fecha y hora", "Fecha u hora es null");

        o.agregarTrabajo("Ajuste frenos", 50000);

        if (o.getCostoTotal() == 50000 && "En proceso".equals(o.getEstado()))
            ok("Orden.agregarTrabajo suma costo y cambia estado");
        else
            fail("Orden.agregarTrabajo", "Costo o estado incorrecto");

        // 5) Cerrar orden
        o.cerrarOrden("Pastillas gastadas");

        if ("Finalizada".equals(o.getEstado()) && "Pastillas gastadas".equals(o.getDiagnostico()))
            ok("Orden.cerrarOrden guarda diagnóstico y finaliza");
        else
            fail("Orden.cerrarOrden", "No finalizó o no guardó diagnóstico");
    }
}
