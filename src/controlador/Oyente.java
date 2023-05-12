package controlador;

import java.awt.Dimension;
import java.awt.event.*;
import java.text.Normalizer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Crud;
import vista.*;

/**
 *
 * @author Mui_f
 */
public class Oyente implements ActionListener {

    VentanaConsulta ventanaConsulta = new VentanaConsulta();
    PanelDatos panel;
    Crud accion;

    public Oyente(PanelDatos panel) {
        this.panel = panel;
        accion = new Crud();
    }

    //Metodo que recibe la accion del boton y genera la consulta
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Consultar")) {
            String tabla = panel.getCombo().getSelectedItem().toString();
            System.out.println(tabla);
            String consulta = accion.consultar(cadenaSinAcentos(tabla));
            if (!consulta.isEmpty()) {
                JOptionPane.showMessageDialog(null, consulta, "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                ventanaConsulta.getLabelTitulo().setText("Resultado de la consulta a la tabla " + tabla);
                ventanaConsulta.setVisible(true);
                ventanaConsulta.setResizable(false);
                ventanaConsulta.setLocationRelativeTo(null);
                ventanaConsulta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                DefaultTableModel model = new DefaultTableModel(accion.getRegistros(), accion.getColumnas());
                JTable table = new JTable(model);
                table.setPreferredScrollableViewportSize(new Dimension(600, 300));
                JScrollPane scroll = new JScrollPane(table);
                ajustarColumna(table);
                scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                ventanaConsulta.getPanel().add(scroll);
            }

        }
    }

    //Metodo que retorna una cadena sin acentos
    public String cadenaSinAcentos(String conAcentos) {
        String cadenaNormalize = Normalizer.normalize(conAcentos, Normalizer.Form.NFD);
        return cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");
    }
    
    //Metodo que ajusta el tamaño de las columnas de la tabla
    public void ajustarColumna(JTable tabla) {
        for (int columna = 0; columna < tabla.getColumnCount(); columna++) {
            int ancho = 600; // valor mínimo de ancho
                tabla.getColumnModel().getColumn(columna).setPreferredWidth(ancho);
        }
    }
    


}
