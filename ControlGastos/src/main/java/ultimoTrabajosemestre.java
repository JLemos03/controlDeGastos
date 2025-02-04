import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ultimoTrabajosemestre extends JFrame {
    private double saldo;
    private JLabel saldoLabel;
    private JTextField montoField;
    private JButton agregarButton, gastarButton;
    private JProgressBar alimentacionBar, ropaBar, facturasBar, mascotasBar, transporteBar, gastosHormigaBar;
    private JComboBox<String> categoriaBox;
    private HashMap<String, JProgressBar> barrasGastos;
    
    public ultimoTrabajosemestre() {
        setTitle("Calculadora de Ahorro");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); 

        saldo = 0;

        
        saldoLabel = new JLabel("Saldo: $" + saldo);
        saldoLabel.setBounds(180, 20, 200, 30);
        add(saldoLabel);

        
        montoField = new JTextField(10);
        montoField.setBounds(50, 60, 200, 30);
        add(montoField);

        
        agregarButton = new JButton("Agregar Dinero");
        agregarButton.setBounds(50, 100, 150, 30);
        add(agregarButton);

        
        gastarButton = new JButton("Registrar Gasto");
        gastarButton.setBounds(220, 100, 150, 30);
        add(gastarButton);

        //agregado
        categoriaBox = new JComboBox<>(new String[]{"Alimentación", "Ropa", "Facturas", "Mascotas", "Transporte", "Gastos Hormiga"});
        categoriaBox.setBounds(250, 60, 200, 30);
        add(categoriaBox);
        
        int barraY = 150;
        int barraAncho = 250, barraAlto = 20;

        String[] categorias = {"Alimentación", "Ropa", "Facturas", "Mascotas", "Transporte", "Gastos Hormiga"};
        JProgressBar[] barras = {
            alimentacionBar = new JProgressBar(0, 100),
            ropaBar = new JProgressBar(0, 100),
            facturasBar = new JProgressBar(0, 100),
            mascotasBar = new JProgressBar(0, 100),
            transporteBar = new JProgressBar(0, 100),
            gastosHormigaBar = new JProgressBar(0, 100)
        };

        
        // agregado 
        barrasGastos = new HashMap<>();
        
        for (int i = 0; i < categorias.length; i++) {
            JLabel label = new JLabel(categorias[i]);
            label.setBounds(50, barraY + (i * 40), 100, 20);
            add(label);

            barras[i].setBounds(160, barraY + (i * 40), barraAncho, barraAlto);
            add(barras[i]);
            
            barrasGastos.put(categorias[i], barras[i]);
        }

        
        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double monto = Double.parseDouble(montoField.getText());
                    saldo += monto;
                    saldoLabel.setText("Saldo: $" + saldo);
                    montoField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido");
                }
            }
        });
        
        //Agregado
        gastarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double monto = Double.parseDouble(montoField.getText());
                    String categoria = (String) categoriaBox.getSelectedItem();

                    if (monto <= 0) {
                        JOptionPane.showMessageDialog(null, "Ingrese un monto mayor a 0");
                        return;
                    }

                    if (monto > saldo) {
                        JOptionPane.showMessageDialog(null, "Saldo insuficiente para este gasto");
                        return;
                    }

                    saldo -= monto;
                    saldoLabel.setText("Saldo: $" + saldo);

                    JProgressBar barra = barrasGastos.get(categoria);
                    int nuevoValor = Math.min(barra.getValue() + (int) monto, 100);
                    barra.setValue(nuevoValor);

                    montoField.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido");
                }
            }
        });
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ultimoTrabajosemestre().setVisible(true);
        });
    }
}
