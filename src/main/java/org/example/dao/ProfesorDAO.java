package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.example.config.Conexion;
import org.example.modelo.Profesor;

public class ProfesorDAO {

    public ProfesorDAO() {
        super();
    }


    public boolean registrarProfesor(Profesor profesor) {
        boolean registrado = false;
        String sql = "INSERT INTO profesores (nomEmpleado, nombre, curp, puesto, sueldo) VALUES (?,?,?,?,?)";

        try (
                Connection conexion = Conexion.conectar();
                PreparedStatement stm = conexion.prepareStatement(sql);
        ) {
            stm.setInt(1, profesor.getNumEmpleado());
            stm.setString(2, profesor.getNombre());
            stm.setString(3, profesor.getCurp());   
            stm.setString(4, profesor.getDepartamento());
            stm.setDouble(5, profesor.getSalario());

            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0) {
                registrado = true;
                System.out.println("Profesor registrado correctamente en la Base de Datos.");
            }
        } catch (SQLException err) {
            System.out.println("Error al registrar profesor: " + err.getMessage());
        }

        return registrado;
    }
    public boolean modificarProfesor(Profesor profesor) {
        boolean modificado = false;
        String sql = "UPDATE profesores SET nombre = ?, curp = ?, puesto = ?, sueldo = ? WHERE nomEmpleado = ?";

        try (
                Connection conexion = Conexion.conectar();
                PreparedStatement stm = conexion.prepareStatement(sql);
        ) {

            stm.setString(1, profesor.getNombre());
            stm.setString(2, profesor.getCurp());
            stm.setString(3, profesor.getDepartamento());
            stm.setDouble(4, profesor.getSalario());
            stm.setInt(5, profesor.getNumEmpleado()); // El ID para el WHERE

            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0) {
                modificado = true;
                System.out.println("Profesor actualizado con exito en la Base de Datos.");
            } else {
                System.out.println("No se encontró ningún profesor con el Número de Empleado proporcionado.");
            }
        } catch (SQLException err) {
            System.out.println("Error al modificar profesor: " + err.getMessage());
        }

        return modificado;
    }

    public ArrayList<Profesor> extraerProfesores() {
        ArrayList<Profesor> profesores = new ArrayList<>();
        String sql = "SELECT * FROM profesores";

        try (
                Connection conexion = Conexion.conectar();
                PreparedStatement stm = conexion.prepareStatement(sql);
        ) {
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Profesor prof = new Profesor();
                prof.setNumEmpleado(rs.getInt("nomEmpleado"));
                prof.setNombre(rs.getString("nombre"));
                prof.setCurp(rs.getString("curp"));
                prof.setDepartamento(rs.getString("puesto"));
                prof.setSalario(rs.getDouble("sueldo"));
                profesores.add(prof);
            }
        } catch (SQLException err) {
            System.out.println("Error al extraer profesores: " + err.getMessage());
        }

        return profesores;
    }
    public boolean darBajaProfesor(int numEmpleado) {
        boolean eliminado = false;
        String sql = "DELETE FROM profesores WHERE nomEmpleado = ?";

        try (
                Connection conexion = Conexion.conectar();
                PreparedStatement stm = conexion.prepareStatement(sql);
        ) {
            stm.setInt(1, numEmpleado);

            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0) {
                eliminado = true;
                System.out.println("Profesor dado de baja correctamente.");
            } else {
                System.out.println("No se encontró ningún profesor con ese número de empleado.");
            }
        } catch (SQLException err) {
            System.out.println("Error al dar de baja al profesor: " + err.getMessage());
        }

        return eliminado;
    }
    public Profesor buscarProfesorPorEmpleado(int numEmpleado) {
        Profesor profesor = null;
        String sql = "SELECT * FROM profesores WHERE numEmpleado = ?";

        try (
                Connection conexion = Conexion.conectar();
                PreparedStatement stm = conexion.prepareStatement(sql);
        ) {
            stm.setInt(1, numEmpleado);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                profesor = new Profesor();
                profesor.setNumEmpleado(rs.getInt("numEmpleado"));
                profesor.setNombre(rs.getString("nombre"));
                profesor.setCurp(rs.getString("curp"));
                profesor.setDepartamento(rs.getString("departamento"));
                profesor.setSalario(rs.getDouble("salario"));
            }
        } catch (SQLException err) {
            System.out.println("Error al buscar profesor: " + err.getMessage());
        }

        return profesor; // Retorna el objeto lleno, o null si no lo encontró
    }
}