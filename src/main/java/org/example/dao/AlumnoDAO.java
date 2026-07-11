//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.example.config.Conexion;
import org.example.modelo.Alumno;

public class AlumnoDAO {
    public AlumnoDAO() {
        super();
    }

    public boolean inscribirAlumno(Alumno alumno) {
        boolean inscrito = false;
        String sql = "INSERT INTO alumnos VALUES (?,?,?,?,?)";

        try (
                Connection conexion = Conexion.conectar();
                PreparedStatement stm = conexion.prepareStatement(sql);
        ) {
            stm.setInt(1, alumno.getNumExpediente());
            stm.setString(2, alumno.getNombre());
            stm.setString(3, alumno.getCurp());
            stm.setString(4, alumno.getGrupo());
            stm.setDouble(5, alumno.getPromedio());
            stm.executeUpdate();
            System.out.println("Registro realizado Correctamente");
        } catch (SQLException err) {
            System.out.println("Error " + err.getMessage());
        }

        return inscrito;
    }

    public ArrayList<Alumno> extraerAlumno() {
        ArrayList<Alumno> alumnos = new ArrayList();
        String sql = "SELECT * FROM alumnos";

        try (
                Connection conexion = Conexion.conectar();
                PreparedStatement stm = conexion.prepareStatement(sql);
        ) {
            ResultSet rs = stm.executeQuery();

            while(rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setNumExpediente(rs.getInt("numExpediente"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setCurp(rs.getString("curp"));
                alumno.setGrupo(rs.getString("grupo"));
                alumno.setPromedio(rs.getDouble("promedio"));
                alumnos.add(alumno);
            }
        } catch (SQLException err) {
            System.out.println("Error al extraer alumnos" + err.getMessage());
        }

        return alumnos;
    }
    public boolean darBajaAlumno(int numExpediente) {
        boolean eliminado = false;
        String sql = "DELETE FROM alumnos WHERE numExpediente = ?";

        try (
                Connection conexion = Conexion.conectar();
                PreparedStatement stm = conexion.prepareStatement(sql);
        ) {
            stm.setInt(1, numExpediente);

            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0) {
                eliminado = true;
                System.out.println("A lumno dado de baja correctamente.");
            } else {
                System.out.println("No se encontró ningún alumno con ese número de expediente.");
            }
        } catch (SQLException err) {
            System.out.println("Error al dar de baja al alumno: " + err.getMessage());
        }

        return eliminado;
    }
    public Alumno buscarAlumnoPorExpediente(int numExpediente) {
        Alumno alumno = null;
        String sql = "SELECT * FROM alumnos WHERE numExpediente = ?";

        try (
                Connection conexion = Conexion.conectar();
                PreparedStatement stm = conexion.prepareStatement(sql);
        ) {
            stm.setInt(1, numExpediente);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                alumno = new Alumno();
                alumno.setNumExpediente(rs.getInt("numExpediente"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setCurp(rs.getString("curp"));
                alumno.setGrupo(rs.getString("grupo"));
                alumno.setPromedio(rs.getDouble("promedio"));
            }
        } catch (SQLException err) {
            System.out.println("Error al buscar alumno: " + err.getMessage());
        }

        return alumno; // Retorna el objeto lleno, o null si no lo encontró
    }
}
