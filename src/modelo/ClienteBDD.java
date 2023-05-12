
package modelo;

import controlador.Oyente;
import javax.swing.JFrame;
import vista.PanelDatos;

/**
 *
 * @author Mui_f
 */
public class ClienteBDD {
    public static void main(String[] args) {
        
//        Crud modelo = new Crud();
        //Creacion de MVC
        JFrame f = new JFrame("Cliente Banco A3M");    
        PanelDatos panel = new PanelDatos();                     //VISTA
        Oyente oyente = new Oyente(panel);       //CONTROLADOR
        panel.agregarOyente(oyente);                             
        
        //Configuracion de ventana
        f.setSize(520, 470);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(panel);
        f.setVisible(true);
        
        
        
    }
}
