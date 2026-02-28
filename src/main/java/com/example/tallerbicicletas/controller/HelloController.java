package com.example.tallerbicicletas.controller;

import com.example.tallerbicicletas.model.Cliente;
import com.example.tallerbicicletas.model.Mecanico;
import com.example.tallerbicicletas.model.Taller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    private final Taller taller = new Taller("Taller BiciCentral", "Armenia", "123456ABC");

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
    }

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
    }
}