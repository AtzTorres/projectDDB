/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Mui_f
 */
public class ConexionSQL {

    public final String[] esquema = {"ea", "eb", "ec"};
    public final String[] nodo = {"25.46.170.37", "25.47.41.213", "25.46.32.68"};
    
    public Connection conn;
    public String user = "admin";
    public String port = "3306";
    public String psswd = "1234";

    

    //Metodo que realiza la conexion a la base de datos y la monitorea 
    public void conectar(int esquema, int nodo) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + this.nodo[nodo] + ":" + port + "/" + this.esquema[esquema], user, psswd);
            System.out.println("Se realizo la conexion");
            System.out.println("\nHost: " + this.nodo[nodo] + " - Puerto: " + port);
            
        } catch (Exception e) {
            switch (nodo) {
                case 0:
                    conectar(esquema, 1);
                    break;
                case 1:
                    conectar(esquema, 2);
                    break;
                case 2:
                    conectar(esquema, 0);
                    break;
            }
        }
    }

}
