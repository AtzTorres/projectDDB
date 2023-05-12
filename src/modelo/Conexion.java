package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    public Connection conn;
    public String user = "admin";
    public String bd = "a3m";
    public String port = "3306";
    public String host = "192.168.100.20";
    public String psswd = "1234";
    public String[] columnas;
    public String[][] registros;

    public Conexion() {
       conectar();
    }

    public void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + bd, user, psswd);
            System.out.println("Se realizo la conexion");
        } catch (Exception e) {
            switch (host){
                case "192.168.100.30":
                host = "192.168.100.40";
                    break;
                case "192.168.100.40":
                    host = "192.168.100.20";
                    break;
                default:
                    host = "192.168.100.30";
                    break;
            }
//            if (host.equals("localhost")) {
//                bd = "BD ALTERNATIVO";
//                host = "IP ALTERNATIVO";
//                port = "PUERTO ALTERNATIVO";
//                psswd = "PSSWD ALTERNATIVO";
//                user = "USUARIO ALTERNATIVO";
//            } else if(){
//                host = "localhost";
//                port = "3306";
//                psswd = "Minicuervo3+";
//                user = "root";
//                bd = "A3M";
//            }
        }
        System.out.println("\nHost: " + host + " - Puerto: " + port);

    }

    public String consultar(String tabla) {
        String error = "";

        try {
            if (!conn.isClosed()) {
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet consulta = stmt.executeQuery("select * from " + tabla + ";");
                int nColumnas = consulta.getMetaData().getColumnCount();
                int j = 0;
                
                columnas = new String[nColumnas];
                for (int i = 1; i <= nColumnas; i++) {
                    columnas[i - 1] = consulta.getMetaData().getColumnName(i);
                }

                registros = new String[contarRegistros(consulta)][nColumnas];
                while (consulta.next()) {
                    for (int i = 1; i <= nColumnas; i++) {
                        registros[j][i - 1] = consulta.getString(i);
//                        System.out.println(consulta.getString(i) + "");
                    }
                    j++;
                }
            }

        } catch (SQLException ex) {
            error = ex.getMessage();
            System.out.println(ex.getMessage());
            conectar();
        }
        return error;
    }

    public int contarRegistros(ResultSet rs) throws SQLException {
        rs.last();
        int aux = rs.getRow();
        rs.beforeFirst();
        return aux;
    }

    public String[][] getRegistros() {
        return registros;
    }

    public String[] getColumnas() {
        return columnas;
    }
}
