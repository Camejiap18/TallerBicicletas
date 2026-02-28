package com.example.tallerbicicletas.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.example.tallerbicicletas.model.Cliente;
import com.example.tallerbicicletas.model.Taller;

public class HelloController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtIdentificacion;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtDireccion;
    @FXML private Button btnGuardarCliente;

    private final Taller taller = new Taller("Taller Central", "Armenia", "123456ABC");

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
}
