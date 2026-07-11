//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String url = "jdbc:mysql://localhost:3306/universidadUT";
    private static final String user = "root";
    private static final String password = "ggxd12.com";

    public Conexion() {
        super();
    }

    public static Connection conectar() {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidadUT", "root", "ggxd12.com");
            System.out.println("Conexion Realizada Correctamente");
        } catch (SQLException err) {
            System.out.println("Error al Conectar con MySQL" + err.getMessage());
        }

        return conexion;
    }
}
