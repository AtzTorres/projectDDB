/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Mui_f
 */
public class Crud {
    
    public String[] columnas;
    public String[][] registros;
    public ConexionSQL conexion;

    public ArrayList<ArrayList<String>> register = new ArrayList<ArrayList<String>>();
    public ArrayList<String> columns = new ArrayList<>();
    
    
//  Metodo que realiza una consulta al servidor MySQL
    public String consultar(String tabla) {
        conexion = new ConexionSQL();
        String sentenciaConsulta = "select * from " + tabla + ";";
        String error = "";
        
        try {
            Statement stmt=null;
            ResultSet consulta=null;
            
            switch (tabla) {
                case "Cliente":
                    conexion.conectar(0, 0);
                    procesarConsulta(stmt, consulta, sentenciaConsulta, true);
                    break;

                case "Pagare", "Amortizacion":
                    conexion.conectar(0, 0);
                    procesarConsulta(stmt, consulta, sentenciaConsulta, true);
                    
                    conexion.conectar(1, 1);
                    procesarConsulta(stmt, consulta, sentenciaConsulta, false);
                    
                    conexion.conectar(2, 2);
                    procesarConsulta(stmt, consulta, sentenciaConsulta, false);
                    break;
                
                case "Tasa":
                    conexion.conectar(2, 2);
                    procesarConsulta(stmt, consulta, sentenciaConsulta, true);
                    break;
            }
            mostrarDatos();
            limpiarColumnasYRegistros();
            conexion.conn.close();
            
        } catch (SQLException ex) {
            error = ex.getMessage();
            System.out.println(ex.getMessage());
        }
        return error;
    }
    
//  Método que evita repetir tanto las sentencia de ResultSet Y Statement, lo que hace es extraer los datos una vez realizada la conexión 
    public void procesarConsulta(Statement stmt, ResultSet consulta, String sentencia, boolean conColumnas) {
        try {
            stmt = conexion.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            consulta = stmt.executeQuery(sentencia);
            if(conColumnas){
                nombrarColumnas(consulta);
            }
            rellenarRegistros(consulta);
            
        } catch (SQLException ex) {}
    }

//  Método que nombrar las columnas de la consulta a las listas
    private void nombrarColumnas(ResultSet consulta) {
        try {
            for (int i = 1; i <= consulta.getMetaData().getColumnCount(); i++) {
                columns.add(consulta.getMetaData().getColumnName(i));
            }

            int j = 0;
            while (consulta.next()) {
                register.add(new ArrayList<String>());
                for (int i = 1; i <= consulta.getMetaData().getColumnCount(); i++) {
                    register.get(j).add(consulta.getString(i));
                }
                j++;
            }
        } catch (SQLException ex) {
        }
    }
    
//  Método que rellenar con datos de la consulta a las listas
    private void rellenarRegistros(ResultSet consulta) {
        try {
            int j = 0;
            if(!register.isEmpty()){
                j = register.size();
            }
            
            while (consulta.next()) {
                register.add(new ArrayList<String>());
                for (int i = 1; i <= consulta.getMetaData().getColumnCount(); i++) {
                    register.get(j).add(consulta.getString(i));
                }
                j++;
            }
        } catch (SQLException ex) {
        }
    }
    
//  Método que imprime los datos contenidos en la lista: columns y lista de listas: register
    private void mostrarDatos() {
        for (String cadaColumna : columns) {
            System.out.print(cadaColumna + " || ");
        }

        System.out.println();
        for (ArrayList<String> elemento : register) {
            for (String registro : elemento) {
                System.out.print(registro + " || ");
            }
            System.out.println();
        }

    }
    
    private void limpiarColumnasYRegistros(){
        register.clear();
        columns.clear();
    }

    
    public String[][] getRegistros() {
        return registros;
    }

    public String[] getColumnas() {
        return columnas;
    }
    
    
    //Posibles mousquerramientas misteriosas XD
//----------------------------------------------------------------------------------
//    if (conexion.conectar(0, 0)) {
//        Statement stmt = conexion.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//        ResultSet consulta = stmt.executeQuery(sentenciaConsulta);
//        nombrarColumnas(consulta);
//        rellenarRegistros(consulta);
//    } else if (conexion.conectar(0, 1)) {
//        Statement stmt = conexion.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//        ResultSet consulta = stmt.executeQuery(sentenciaConsulta);
//        nombrarColumnas(consulta);
//        rellenarRegistros(consulta);
//    } else if (conexion.conectar(0, 2)) {
//        Statement stmt = conexion.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//        ResultSet consulta = stmt.executeQuery(sentenciaConsulta);
//        nombrarColumnas(consulta);
//        rellenarRegistros(consulta);
//    } else {
//        error = "Sistema caído, espere hasta que se restableza";
//    }
//--------------------------------------------------------------------------------

//    Para recorrer las tres tablas pagare en los tres esquemas
//                    for (int i = 0; i <= 2; i++) {
//                        conexion.conectar(i, i);
//                        stmt = conexion.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                        consulta = stmt.executeQuery(sentenciaConsulta);
//                        rellenarRegistros(consulta);
//                    }
//                    stmt = conexion.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                    consulta = stmt.executeQuery(sentenciaConsulta);
//                    nombrarColumnas(consulta);
//    
//--------------------------------------------------------------------------------
}
