
package vista;

import javax.swing.*;

/**
 *
 * @author Mui_f
 */
public class VentanaConsulta extends javax.swing.JFrame {

    /**
     * Creates new form ventanaDatos
     */
    public VentanaConsulta() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDatos = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTitulo.setBackground(new java.awt.Color(255, 255, 255));
        labelTitulo.setFont(new java.awt.Font("Sitka Heading", 0, 18)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        panelDatos.add(labelTitulo);

        getContentPane().add(panelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPanel panelDatos;
    // End of variables declaration//GEN-END:variables

public JLabel getLabelTitulo(){
    return labelTitulo;
}

public JPanel getPanel(){
    return panelDatos;
}
}
