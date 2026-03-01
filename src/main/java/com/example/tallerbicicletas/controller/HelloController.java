package com.example.tallerbicicletas.controller;

import com.example.tallerbicicletas.model.Bicicleta;
import com.example.tallerbicicletas.model.Cliente;
import com.example.tallerbicicletas.model.Mecanico;
import com.example.tallerbicicletas.model.Orden;
import com.example.tallerbicicletas.model.Taller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import com.example.tallerbicicletas.model.TipoBicicleta;

public class HelloController {

    // --- CLIENTES ---
    @FXML private TextField txtNombre;
    @FXML private TextField txtIdentificacion;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtDireccion;
    @FXML private Button btnGuardarCliente;

    // --- MECÁNICOS ---
    @FXML private TextField txtNombreMecanico;
    @FXML private TextField txtIdentificacionMecanico;
    @FXML private TextField txtEspecialidad;
    @FXML private TextField txtSueldo;
    @FXML private Button btnGuardarMecanico;

    // --- BICICLETAS ---
    @FXML private TextField txtMarca;
    @FXML private TextField txtModelo;
    @FXML private TextField txtColor;
    @FXML private TextField txtSerial;
    @FXML private Button btnGuardarBicicleta;
    @FXML private ComboBox<TipoBicicleta> comboTipo;

    // --- ÓRDENES ---
    @FXML private ComboBox<Cliente> comboClienteOrden;
    @FXML private ComboBox<Bicicleta> comboBicicletaOrden;
    @FXML private ComboBox<Mecanico> comboMecanicoOrden;
    @FXML private TextField txtMotivoOrden;
    @FXML private TextField txtProblemaOrden;

    //Método para refresacar los combos
    private void refrescarCombosOrden() {
        comboClienteOrden.getItems().setAll(taller.getClientes());
        comboBicicletaOrden.getItems().setAll(taller.getBicicletas());
        comboMecanicoOrden.getItems().setAll(taller.getMecanicos());
    }

    private final Taller taller = new Taller("Taller BiciCentral", "Armenia", "123456ABC");

    //Método para guardar bicicletas
    @FXML
    private void guardarCliente() {
        String nombre = txtNombre.getText();
        String identificacion = txtIdentificacion.getText();
        String telefono = txtTelefono.getText();
        String direccion = txtDireccion.getText();

        Cliente cliente = new Cliente(nombre, identificacion, telefono, direccion);
        taller.agregarCliente(cliente);

        System.out.println("Cliente guardado:");
        System.out.println(cliente);
        System.out.println(taller);

        txtNombre.clear();
        txtIdentificacion.clear();
        txtTelefono.clear();
        txtDireccion.clear();

        refrescarCombosOrden();
    }

    //Método para guardar bicicletas
    @FXML
    private void guardarMecanico() {
        String nombre = txtNombreMecanico.getText();
        String identificacion = txtIdentificacionMecanico.getText();
        String especialidad = txtEspecialidad.getText();

        double sueldo;
        try {
            // Por si escriben "1.500.000" o "1,500,000" etc:
            String sueldoTexto = txtSueldo.getText()
                    .replace(".", "")
                    .replace(",", "");
            sueldo = Double.parseDouble(sueldoTexto);
        } catch (Exception e) {
            System.out.println("Sueldo inválido. Escribe solo números.");
            return;
        }

        Mecanico mecanico = new Mecanico(nombre, identificacion, especialidad, sueldo);
        taller.agregarMecanico(mecanico);

        System.out.println("Mecánico guardado:");
        System.out.println(mecanico);
        System.out.println(taller);

        txtNombreMecanico.clear();
        txtIdentificacionMecanico.clear();
        txtEspecialidad.clear();
        txtSueldo.clear();

        refrescarCombosOrden();
    }

    //Método para guardar bicicletas
    @FXML
    private void guardarBicicleta(){

        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        String color = txtColor.getText();
        String serial = txtSerial.getText();

        //Condición para seleccionar un tipo
        TipoBicicleta tipo = comboTipo.getValue();
        if (tipo == null) {
            System.out.println("Selecciona un tipo de bicicleta.");
            return;
        }

        Bicicleta bicicleta = new Bicicleta(marca, modelo, color, serial, tipo);
        taller.agregarBicicleta(bicicleta);

        System.out.println("Bicicleta guardada:");
        System.out.println(bicicleta);
        System.out.println(taller);

        txtMarca.clear();
        txtModelo.clear();
        txtColor.clear();
        txtSerial.clear();
        comboTipo.setValue(null);

        refrescarCombosOrden();
    }

    //Método para crear la órden
    @FXML
    private void crearOrden() {

        Cliente cliente = (Cliente) comboClienteOrden.getValue();
        Bicicleta bicicleta = (Bicicleta) comboBicicletaOrden.getValue();
        Mecanico mecanico = (Mecanico) comboMecanicoOrden.getValue();

        String motivo = txtMotivoOrden.getText();
        String descripcion = txtProblemaOrden.getText();

        if(cliente == null || bicicleta == null || mecanico == null) {
            System.out.println("Debes seleccionar cliente, bicicleta y mecánico");
            return;
        }

        Orden orden = new Orden(cliente, bicicleta, mecanico, descripcion, motivo);
        taller.agregarOrden(orden);

        System.out.println("Orden creada:");
        System.out.println(orden);
        System.out.println(taller);

        //para que la orden se "limpie"
        txtMotivoOrden.clear();
        txtProblemaOrden.clear();
        comboClienteOrden.setValue(null);
        comboBicicletaOrden.setValue(null);
        comboMecanicoOrden.setValue(null);
    }

    //Método para incializar el combobox
    @FXML
    public void initialize() {

        // Combo de tipos de bicicleta
        comboTipo.getItems().addAll(TipoBicicleta.values());

        // Combos de Órdenes
        comboClienteOrden.getItems().setAll(taller.getClientes());
        comboBicicletaOrden.getItems().setAll(taller.getBicicletas());
        comboMecanicoOrden.getItems().setAll(taller.getMecanicos());

        refrescarCombosOrden();
    }

}