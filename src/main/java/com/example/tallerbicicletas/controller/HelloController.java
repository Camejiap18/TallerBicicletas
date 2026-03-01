package com.example.tallerbicicletas.controller;

import com.example.tallerbicicletas.model.Bicicleta;
import com.example.tallerbicicletas.model.Cliente;
import com.example.tallerbicicletas.model.Mecanico;
import com.example.tallerbicicletas.model.Orden;
import com.example.tallerbicicletas.model.Taller;
import com.example.tallerbicicletas.model.TipoBicicleta;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    @FXML private TextField txtRepuestoOrden;

    // --- CONSULTAS ---
    @FXML private TextArea txtAreaConsultas;
    @FXML private TextField txtSerialConsulta;
    @FXML private ComboBox<Bicicleta> comboHistorialBici;
    @FXML private DatePicker datePickerOrden;

    private final Taller taller = new Taller("Taller BiciCentral", "Armenia", "123456ABC");
    private Orden ordenActual;

    // =========================
    // ALERTAS
    // =========================
    private void alertaInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void alertaError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // =========================
    // REFRESCAR COMBOS
    // =========================
    private void refrescarCombosOrden() {

        // Bicicletas: combo de cliente dueño
        if (comboClienteBici != null) {
            comboClienteBici.getItems().setAll(taller.getClientes());
        }

        // Órdenes
        if (comboClienteOrden != null) {
            comboClienteOrden.getItems().setAll(taller.getClientes());
        }
        if (comboBicicletaOrden != null) {
            comboBicicletaOrden.getItems().setAll(taller.getBicicletas());
        }
        if (comboMecanicoOrden != null) {
            comboMecanicoOrden.getItems().setAll(taller.getMecanicos());
        }

        // Consultas
        if (comboHistorialBici != null) {
            comboHistorialBici.getItems().setAll(taller.getBicicletas());
        }
    }

    // =========================
    // CLIENTES
    // =========================
    @FXML
    private void guardarCliente() {

        String nombre = txtNombre.getText();
        String identificacion = txtIdentificacion.getText();
        String telefono = txtTelefono.getText();
        String direccion = txtDireccion.getText();

        if (nombre == null || nombre.trim().isEmpty()
                || identificacion == null || identificacion.trim().isEmpty()
                || telefono == null || telefono.trim().isEmpty()
                || direccion == null || direccion.trim().isEmpty()) {
            alertaError("Completa todos los campos del cliente.");
            return;
        }

        Cliente cliente = new Cliente(nombre.trim(), identificacion.trim(), telefono.trim(), direccion.trim());
        taller.agregarCliente(cliente);

        txtNombre.clear();
        txtIdentificacion.clear();
        txtTelefono.clear();
        txtDireccion.clear();

        refrescarCombosOrden();
        alertaInfo("Cliente guardado correctamente.");
    }

    // =========================
    // MECÁNICOS
    // =========================
    @FXML
    private void guardarMecanico() {

        String nombre = txtNombreMecanico.getText();
        String identificacion = txtIdentificacionMecanico.getText();
        String especialidad = txtEspecialidad.getText();

        if (nombre == null || nombre.trim().isEmpty()
                || identificacion == null || identificacion.trim().isEmpty()
                || especialidad == null || especialidad.trim().isEmpty()) {
            alertaError("Completa nombre, código/identificación y especialidad del mecánico.");
            return;
        }

        double sueldo;
        try {
            String sueldoTexto = txtSueldo.getText();
            if (sueldoTexto == null || sueldoTexto.trim().isEmpty()) {
                alertaError("Ingrese el sueldo del mecánico.");
                return;
            }
            sueldoTexto = sueldoTexto.replace(".", "").replace(",", "");
            sueldo = Double.parseDouble(sueldoTexto);
        } catch (Exception e) {
            alertaError("Sueldo inválido. Escribe solo números.");
            return;
        }

        Mecanico mecanico = new Mecanico(nombre.trim(), identificacion.trim(), especialidad.trim(), sueldo);
        taller.agregarMecanico(mecanico);

        txtNombreMecanico.clear();
        txtIdentificacionMecanico.clear();
        txtEspecialidad.clear();
        txtSueldo.clear();

        refrescarCombosOrden();
        alertaInfo("Mecánico guardado correctamente.");
    }

    // =========================
    // BICICLETAS
    // =========================
    @FXML
    private void guardarBicicleta() {

        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        String color = txtColor.getText();
        String serial = txtSerial.getText();

        if (marca == null || marca.trim().isEmpty()
                || modelo == null || modelo.trim().isEmpty()
                || color == null || color.trim().isEmpty()
                || serial == null || serial.trim().isEmpty()) {
            alertaError("Completa marca, modelo, color y serial.");
            return;
        }

        Cliente cliente = comboClienteBici.getValue();
        if (cliente == null) {
            alertaError("Seleccione un cliente para la bicicleta.");
            return;
        }

        TipoBicicleta tipo = comboTipo.getValue();
        if (tipo == null) {
            alertaError("Seleccione un tipo de bicicleta.");
            return;
        }

        int anio;
        try {
            String anioTxt = txtAnioBici.getText();
            if (anioTxt == null || anioTxt.trim().isEmpty()) {
                alertaError("Ingrese el año de la bicicleta.");
                return;
            }
            anio = Integer.parseInt(anioTxt.trim());
        } catch (Exception e) {
            alertaError("Ingrese un año válido (solo números).");
            return;
        }

        // Evitar duplicado por serial (opcional, pero recomendado)
        if (taller.buscarBicicletaPorSerial(serial.trim()) != null) {
            alertaError("Ya existe una bicicleta con ese serial.");
            return;
        }

        Bicicleta bicicleta = new Bicicleta(marca.trim(), modelo.trim(), color.trim(), serial.trim(), tipo, cliente, anio);
        taller.agregarBicicleta(bicicleta);

        txtMarca.clear();
        txtModelo.clear();
        txtColor.clear();
        txtSerial.clear();
        txtAnioBici.clear();
        comboTipo.setValue(null);
        comboClienteBici.setValue(null);

        refrescarCombosOrden();
        alertaInfo("Bicicleta guardada correctamente.");
    }

    // =========================
    // CONSULTAS
    // =========================
    @FXML
    private void buscarHistorialPorSerial() {

        String serial = txtSerialConsulta.getText();

        if (serial == null || serial.trim().isEmpty()) {
            alertaError("Ingrese un serial.");
            return;
        }

        Bicicleta bici = taller.buscarBicicletaPorSerial(serial.trim());

        if (bici == null) {
            alertaError("No existe una bicicleta con serial: " + serial);
            return;
        }

        comboHistorialBici.setValue(bici);
        verHistorialBicicleta();
    }

    @FXML
    private void verHistorialBicicleta() {

        Bicicleta bici = comboHistorialBici.getValue();

        if (bici == null) {
            alertaError("Seleccione una bicicleta.");
            return;
        }

        StringBuilder historial = new StringBuilder();
        historial.append("===== HISTORIAL DE LA BICICLETA =====\n\n");

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
                historial.append(o.toTextoBonito())
                        .append("\n------------------------------------\n\n");
            }
        }

        if (!encontro) {
            historial.append("No hay órdenes registradas para esta bicicleta.\n");
        }

        txtAreaConsultas.setText(historial.toString());
    }

    @FXML
    private void buscarOrdenPorFecha() {

        LocalDate fecha = datePickerOrden.getValue();

        if (fecha == null) {
            alertaError("Seleccione una fecha.");
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
                resultado.append(o.toTextoBonito())
                        .append("\n------------------------------------\n\n");
            }
        }

        if (!encontro) {
            resultado.append("No hay órdenes registradas en esa fecha.");
        }

        txtAreaConsultas.setText(resultado.toString());
    }

    @FXML
    private void mostrarConsultas() {
        txtAreaConsultas.setText(taller.toString());
    }

    // =========================
    // ÓRDENES
    // =========================
    @FXML
    private void crearOrden() {

        Cliente cliente = comboClienteOrden.getValue();
        Bicicleta bicicleta = comboBicicletaOrden.getValue();
        Mecanico mecanico = comboMecanicoOrden.getValue();

        String motivo = txtMotivoOrden.getText();
        String descripcion = txtProblemaOrden.getText();

        if (cliente == null || bicicleta == null || mecanico == null) {
            alertaError("Debes seleccionar cliente, bicicleta y mecánico.");
            return;
        }

        if (motivo == null || motivo.trim().isEmpty() || descripcion == null || descripcion.trim().isEmpty()) {
            alertaError("Completa motivo del servicio y descripción del problema.");
            return;
        }

        ordenActual = new Orden(cliente, bicicleta, mecanico, descripcion.trim(), motivo.trim());
        taller.agregarOrden(ordenActual);

        // Mano de obra automática del mecánico (5% del sueldo)
        double manoObra = mecanico.getSueldo() * 0.05;
        ordenActual.agregarTrabajo("Mano de obra (" + mecanico.getNombre() + ")", manoObra);

        txtMotivoOrden.clear();
        txtProblemaOrden.clear();
        comboClienteOrden.setValue(null);
        comboBicicletaOrden.setValue(null);
        comboMecanicoOrden.setValue(null);

        refrescarCombosOrden();
        alertaInfo("Orden creada correctamente.");
        txtAreaConsultas.setText("Orden creada.\n\n" + ordenActual.toTextoBonito());
    }

    @FXML
    private void agregarTrabajoOrden() {

        if (ordenActual == null) {
            alertaError("Primero crea una orden. Luego puedes agregar trabajos.");
            return;
        }

        String trabajo = txtTrabajoOrden.getText();
        String costoTexto = txtCostoTrabajo.getText();

        if (trabajo == null || trabajo.trim().isEmpty()) {
            alertaError("Escribe el trabajo realizado.");
            return;
        }

        double costo;
        try {
            if (costoTexto == null || costoTexto.trim().isEmpty()) {
                alertaError("Escribe el costo del trabajo.");
                return;
            }
            costoTexto = costoTexto.replace(".", "").replace(",", "");
            costo = Double.parseDouble(costoTexto);
        } catch (Exception e) {
            alertaError("Costo inválido. Escribe solo números.");
            return;
        }

        ordenActual.agregarTrabajo(trabajo.trim(), costo);

        txtTrabajoOrden.clear();
        txtCostoTrabajo.clear();

        alertaInfo("Trabajo agregado correctamente.");
        txtAreaConsultas.setText("Trabajo agregado.\n\n" + ordenActual.toTextoBonito());
    }

    @FXML
    private void agregarRepuestoOrden() {

        if (ordenActual == null) {
            alertaError("Primero crea una orden. Luego puedes agregar repuestos.");
            return;
        }

        String rep = txtRepuestoOrden.getText();

        if (rep == null || rep.trim().isEmpty()) {
            alertaError("Escribe el nombre del repuesto.");
            return;
        }

        ordenActual.agregarRepuesto(rep.trim());
        txtRepuestoOrden.clear();

        alertaInfo("Repuesto agregado correctamente.");
        txtAreaConsultas.setText("Repuesto agregado.\n\n" + ordenActual.toTextoBonito());
    }

    @FXML
    private void cerrarOrden() {

        if (ordenActual == null) {
            alertaError("No hay una orden activa. Primero crea una orden.");
            return;
        }

        String diagnosticoFinal = txtDiagnosticoOrden.getText();

        if (diagnosticoFinal == null || diagnosticoFinal.trim().isEmpty()) {
            alertaError("Escribe el diagnóstico antes de cerrar la orden.");
            return;
        }

        ordenActual.cerrarOrden(diagnosticoFinal.trim());

        alertaInfo("Orden cerrada correctamente.");
        txtAreaConsultas.setText("Orden cerrada.\n\n" + ordenActual.toTextoBonito());

        txtDiagnosticoOrden.clear();
        txtTrabajoOrden.clear();
        txtCostoTrabajo.clear();
        if (txtRepuestoOrden != null) txtRepuestoOrden.clear();

        ordenActual = null;
        refrescarCombosOrden();
    }

    // =========================
    // INIT
    // =========================
    @FXML
    public void initialize() {

        if (comboTipo != null) {
            comboTipo.getItems().setAll(TipoBicicleta.values());
        }

        refrescarCombosOrden();
    }
}