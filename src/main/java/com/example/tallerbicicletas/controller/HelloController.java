package com.example.tallerbicicletas.controller;

import com.example.tallerbicicletas.model.Bicicleta;
import com.example.tallerbicicletas.model.Cliente;
import com.example.tallerbicicletas.model.Mecanico;
import com.example.tallerbicicletas.model.Orden;
import com.example.tallerbicicletas.model.Taller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import javafx.scene.control.TextArea;
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
    @FXML private TextField txtAnioBici;
    @FXML private Button btnGuardarBicicleta;
    @FXML private ComboBox<TipoBicicleta> comboTipo;
    @FXML private ComboBox<Cliente> comboClienteBici;

    // --- ÓRDENES ---
    @FXML private ComboBox<Cliente> comboClienteOrden;
    @FXML private ComboBox<Bicicleta> comboBicicletaOrden;
    @FXML private ComboBox<Mecanico> comboMecanicoOrden;
    @FXML private TextField txtMotivoOrden;
    @FXML private TextField txtProblemaOrden;
    @FXML private TextField txtDiagnosticoOrden;
    @FXML private TextField txtTrabajoOrden;
    @FXML private TextField txtCostoTrabajo;

    // --- CONSULTAS ---
    @FXML private TextArea txtAreaConsultas;
    @FXML private ComboBox<Bicicleta> comboHistorialBici;
    @FXML private DatePicker datePickerOrden;

    //Método para refresacar los combos
    private void refrescarCombosOrden() {
        comboClienteOrden.getItems().setAll(taller.getClientes());
        comboBicicletaOrden.getItems().setAll(taller.getBicicletas());
        comboMecanicoOrden.getItems().setAll(taller.getMecanicos());
        comboHistorialBici.getItems().setAll(taller.getBicicletas());

    }

    private final Taller taller = new Taller("Taller BiciCentral", "Armenia", "123456ABC");

    private Orden ordenActual;

    //Método para guardar clientes
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

    //Método para guardar mecánicos
    @FXML
    private void guardarMecanico() {
        String nombre = txtNombreMecanico.getText();
        String identificacion = txtIdentificacionMecanico.getText();
        String especialidad = txtEspecialidad.getText();

        double sueldo;
        try {
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

    //Método para guardar la bicicleta
    @FXML
    private void guardarBicicleta(){

        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        String color = txtColor.getText();
        String serial = txtSerial.getText();

        Cliente cliente = comboClienteBici.getValue();

        if(cliente == null){
            System.out.println("Seleccione un cliente para la bicicleta.");
            return;
        }

        TipoBicicleta tipo = comboTipo.getValue();

        if (tipo == null) {
            System.out.println("Seleccione un tipo de bicicleta.");
            return;
        }

        int anio;
        try {
            anio = Integer.parseInt(txtAnioBici.getText());
        } catch (Exception e) {
            System.out.println("Ingrese un año válido.");
            return;
        }

        Bicicleta bicicleta = new Bicicleta(marca, modelo, color, serial, tipo, cliente, anio);
        taller.agregarBicicleta(bicicleta);

        System.out.println("Bicicleta guardada:");
        System.out.println(bicicleta);
        System.out.println(taller);

        txtMarca.clear();
        txtModelo.clear();
        txtColor.clear();
        txtSerial.clear();
        txtAnioBici.clear();
        comboTipo.setValue(null);
        comboClienteBici.setValue(null);

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

        ordenActual = new Orden(cliente, bicicleta, mecanico, descripcion, motivo);
        taller.agregarOrden(ordenActual);

        if (txtDiagnosticoOrden != null && txtDiagnosticoOrden.getText() != null) {
            ordenActual.setDiagnostico(txtDiagnosticoOrden.getText());
        }

        System.out.println("Orden creada:");
        System.out.println(ordenActual);
        System.out.println(taller);

        //para que la orden se "limpie"
        txtMotivoOrden.clear();
        txtProblemaOrden.clear();
        comboClienteOrden.setValue(null);
        comboBicicletaOrden.setValue(null);
        comboMecanicoOrden.setValue(null);
    }

    //Método para cerrar la órden
    @FXML
    private void cerrarOrden() {

        if (ordenActual == null) {
            txtAreaConsultas.setText("No hay una orden activa. Primero crea una orden.");
            return;
        }

        String diagnosticoFinal = txtDiagnosticoOrden.getText();

        if (diagnosticoFinal == null || diagnosticoFinal.trim().isEmpty()) {
            txtAreaConsultas.setText("Escribe el diagnóstico antes de cerrar la orden.");
            return;
        }

        ordenActual.cerrarOrden(diagnosticoFinal);

        txtAreaConsultas.setText("Orden cerrada.\n\n" + ordenActual.toTextoBonito());
    }

    //Método para ver el historial de la bicicleta
    @FXML
    private void verHistorialBicicleta() {

        Bicicleta bici = comboHistorialBici.getValue();

        if (bici == null) {
            txtAreaConsultas.setText("Seleccione una bicicleta.");
            return;
        }

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        StringBuilder historial = new StringBuilder();
        historial.append("===== HISTORIAL DE LA BICICLETA =====\n\n");

        // (Opcional) info de la bicicleta al inicio
        historial.append("Bicicleta: ")
                .append(bici.getMarca()).append(" ")
                .append(bici.getModelo())
                .append(" | Serial: ").append(bici.getSerial())
                .append(" | Tipo: ").append(bici.getTipo())
                .append("\n\n");

        boolean encontro = false;

        for (Orden o : taller.getOrdenes()) {
            if (o.getBicicleta().equals(bici)) {
                encontro = true;

                historial.append("Cliente: ")
                        .append(o.getCliente().getNombre())
                        .append("\n");

                historial.append("Mecánico: ")
                        .append(o.getMecanico().getNombre())
                        .append("\n");

                historial.append("Motivo del servicio: ")
                        .append(o.getMotivoServicio())
                        .append("\n");

                historial.append("Descripción del problema: ")
                        .append(o.getDescripcionProblema())
                        .append("\n");

                historial.append("Estado: ")
                        .append(o.getEstado())
                        .append("\n");

                if (o.getFechaIngreso() != null) {
                    historial.append("Fecha de ingreso: ")
                            .append(o.getFechaIngreso().format(formatoFecha))
                            .append("\n");
                } else {
                    historial.append("Fecha de ingreso: (sin fecha)\n");
                }

                historial.append("------------------------------------\n\n");
            }
        }

        if (!encontro) {
            historial.append("No hay órdenes registradas para esta bicicleta.\n");
        }

        txtAreaConsultas.setText(historial.toString());
    }

    //Método para buscar la órden por fecha
    @FXML
    private void buscarOrdenPorFecha() {

        LocalDate fecha = datePickerOrden.getValue();

        if (fecha == null) {
            txtAreaConsultas.setText("Seleccione una fecha.");
            return;
        }

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        StringBuilder resultado = new StringBuilder();
        resultado.append("===== ÓRDENES DEL DÍA ")
                .append(fecha.format(formatoFecha))
                .append(" =====\n\n");

        boolean encontro = false;

        for (Orden o : taller.getOrdenes()) {

            if (o.getFechaIngreso() != null && o.getFechaIngreso().equals(fecha)) {

                encontro = true;

                resultado.append("Cliente: ")
                        .append(o.getCliente().getNombre())
                        .append("\n");

                resultado.append("Bicicleta: ")
                        .append(o.getBicicleta().getMarca())
                        .append(" ")
                        .append(o.getBicicleta().getModelo())
                        .append("\n");

                resultado.append("Mecánico: ")
                        .append(o.getMecanico().getNombre())
                        .append("\n");

                resultado.append("Motivo del servicio: ")
                        .append(o.getMotivoServicio())
                        .append("\n");

                resultado.append("Descripción del problema: ")
                        .append(o.getDescripcionProblema())
                        .append("\n");

                resultado.append("Estado: ")
                        .append(o.getEstado())
                        .append("\n");

                resultado.append("Fecha de ingreso: ")
                        .append(o.getFechaIngreso().format(formatoFecha))
                        .append("\n");

                resultado.append("------------------------------------\n\n");
            }
        }

        if (!encontro) {
            resultado.append("No hay órdenes registradas en esa fecha.");
        }

        txtAreaConsultas.setText(resultado.toString());
    }

    //Método para agregar trabajo a la órden
    @FXML
    private void agregarTrabajoOrden() {

        if (ordenActual == null) {
            txtAreaConsultas.setText("Primero crea una orden. Luego puedes agregar trabajos.");
            return;
        }

        String trabajo = txtTrabajoOrden.getText();
        String costoTexto = txtCostoTrabajo.getText();

        if (trabajo == null || trabajo.trim().isEmpty()) {
            txtAreaConsultas.setText("Escribe el trabajo realizado.");
            return;
        }

        double costo;
        try {
            costoTexto = costoTexto.replace(".", "").replace(",", "");
            costo = Double.parseDouble(costoTexto);
        } catch (Exception e) {
            txtAreaConsultas.setText("Costo inválido. Escribe solo números.");
            return;
        }

        if (txtDiagnosticoOrden != null && txtDiagnosticoOrden.getText() != null) {
            ordenActual.setDiagnostico(txtDiagnosticoOrden.getText());
        }

        ordenActual.agregarTrabajo(trabajo, costo);

        txtAreaConsultas.setText("Trabajo agregado.\n\n" + ordenActual.toTextoBonito());

        // para limpiar los campos de trabajo
        txtTrabajoOrden.clear();
        txtCostoTrabajo.clear();
    }

    //Método para mostrar las consultas
    @FXML
    private void mostrarConsultas() {

        txtAreaConsultas.setText(taller.toString());
    }

    //Método para incializar el combobox
    @FXML
    public void initialize() {

        // Combo de tipos de bicicleta
        if (comboTipo != null) {
            comboTipo.getItems().addAll(TipoBicicleta.values());
        }

        // Combo cliente bicicleta
        if (comboClienteBici != null) {
            comboClienteBici.getItems().setAll(taller.getClientes());
        }

        // Combos de órdenes
        if (comboClienteOrden != null) {
            comboClienteOrden.getItems().setAll(taller.getClientes());
        }

        if (comboBicicletaOrden != null) {
            comboBicicletaOrden.getItems().setAll(taller.getBicicletas());
        }

        if (comboMecanicoOrden != null) {
            comboMecanicoOrden.getItems().setAll(taller.getMecanicos());
        }

        // Combo historial
        if (comboHistorialBici != null) {
            comboHistorialBici.getItems().setAll(taller.getBicicletas());
        }
    }
}