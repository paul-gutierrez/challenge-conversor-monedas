package gui;

import models.BuscarMoneda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

public class ConversorMonedasGUI extends JFrame {
    private JTextField campoCantidad;
    private JComboBox<String> comboMonedaBase;
    private JComboBox<String> comboMonedaDestino;
    private JButton botonConvertir;
    private JLabel etiquetaResultado;

    public ConversorMonedasGUI() {
        setTitle("Conversor de Monedas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear instancia de BuscarMoneda
        BuscarMoneda buscarMoneda = new BuscarMoneda();
        Map<String, Double> tasasConversion = buscarMoneda.obtenerConversiones("USD");

        // Crear los componentes de la interfaz
        campoCantidad = new JTextField(10);
        comboMonedaBase = new JComboBox<>();
        comboMonedaDestino = new JComboBox<>();
        botonConvertir = new JButton("Convertir");
        etiquetaResultado = new JLabel("Resultado: ");

        // Llenar los menús desplegables
        if (tasasConversion != null) {
            TreeMap<String, Double> monedasOrdenadas = new TreeMap<>(tasasConversion);
            for (String moneda : monedasOrdenadas.keySet()) {
                comboMonedaBase.addItem(moneda);
                comboMonedaDestino.addItem(moneda);
            }
        } else {
            System.out.println("Error: No se pudieron cargar las monedas.");
        }

        // Panel para los campos de entrada
        JPanel panelInputs = new JPanel(new GridLayout(3, 2));
        panelInputs.add(new JLabel("Cantidad:"));
        panelInputs.add(campoCantidad);
        panelInputs.add(new JLabel("Moneda Base:"));
        panelInputs.add(comboMonedaBase);
        panelInputs.add(new JLabel("Moneda Destino:"));
        panelInputs.add(comboMonedaDestino);

        // Panel para el botón y resultado
        JPanel panelResultado = new JPanel();
        panelResultado.add(botonConvertir);
        panelResultado.add(etiquetaResultado);

        // Diseño de la interfaz
        setLayout(new BorderLayout());
        add(panelInputs, BorderLayout.CENTER);
        add(panelResultado, BorderLayout.SOUTH);

        // Acción del botón "Convertir"
        botonConvertir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double cantidad = Double.parseDouble(campoCantidad.getText());
                    String monedaBase = (String) comboMonedaBase.getSelectedItem();
                    String monedaDestino = (String) comboMonedaDestino.getSelectedItem();
                    double tasa = buscarMoneda.obtenerTasaConversion(monedaBase, monedaDestino);

                    if (tasa != -1) {
                        double resultado = cantidad * tasa;
                        etiquetaResultado.setText("Resultado: " + resultado);
                    } else {
                        etiquetaResultado.setText("Error en la conversión.");
                    }

                } catch (NumberFormatException ex) {
                    etiquetaResultado.setText("Por favor, ingresa un número válido.");
                }
            }
        });
    }

    public static void main(String[] args) {
        ConversorMonedasGUI frame = new ConversorMonedasGUI();
        frame.setVisible(true);
    }
}
