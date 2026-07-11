package org.example.vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.example.dao.AlumnoDAO;
import org.example.dao.ProfesorDAO;
import org.example.modelo.Alumno;
import org.example.modelo.Profesor;

public class Menu {
    static AlumnoDAO alumnoDAO = new AlumnoDAO();
    static ProfesorDAO profesorDAO = new ProfesorDAO(); // DAO de Profesores
    static BufferedReader leer;

    public Menu() {
        super();
    }

    // ================= MÓDULO ALUMNOS =================
    private static void inscribir() throws IOException {
        Alumno alumno = new Alumno();
        System.out.print("Nombre: ");
        alumno.setNombre(leer.readLine());
        System.out.print("Curp: ");
        alumno.setCurp(leer.readLine());
        System.out.print("Número de Expediente: ");
        alumno.setNumExpediente(Integer.parseInt(leer.readLine()));
        System.out.print("Grupo: ");
        alumno.setGrupo(leer.readLine());
        System.out.print("Promedio: ");
        alumno.setPromedio(Double.parseDouble(leer.readLine()));
        alumnoDAO.inscribirAlumno(alumno);
    }

    private static void mostrarAlumnos() {
        ArrayList<Alumno> alumnos = alumnoDAO.extraerAlumno();
        System.out.println("========== LISTA DE ALUMNOS ==========");
        for(Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }

    private static void actualizarALumno() {}





    private static void registrarProfesor() throws IOException {
        Profesor profesor = new Profesor();
        System.out.println("=== Registro de Nuevo Profesor ===");
        System.out.print("Nombre: ");
        profesor.setNombre(leer.readLine());
        System.out.print("Curp: ");
        profesor.setCurp(leer.readLine());
        System.out.print("Número de Empleado: ");
        profesor.setNumEmpleado(Integer.parseInt(leer.readLine()));
        System.out.print("Departamento: ");
        profesor.setDepartamento(leer.readLine());
        System.out.print("Salario: ");
        profesor.setSalario(Double.parseDouble(leer.readLine()));

        profesorDAO.registrarProfesor(profesor);
    }

    private static void mostrarProfesores() {
        ArrayList<Profesor> profesores = profesorDAO.extraerProfesores();
        System.out.println("========== LISTA DE PROFESORES ==========");
        if(profesores.isEmpty()) {
            System.out.println("No hay profesores registrados.");
        } else {
            for(Profesor prof : profesores) {
                System.out.println(prof);
            }
        }
    }
    private static void actualizarProfesor() throws IOException {
        System.out.println("=== Modificar Datos de Profesor ===");
        System.out.print("Ingresa el Número de Empleado del profesor a modificar: ");
        int numEmpleado = Integer.parseInt(leer.readLine());


        Profesor profesor = new Profesor();
        profesor.setNumEmpleado(numEmpleado);


        if (profesor.getNumEmpleado() == numEmpleado) {
            System.out.print("Nuevo Nombre: ");
            profesor.setNombre(leer.readLine());
            System.out.print("Nuevo Curp: ");
            profesor.setCurp(leer.readLine());
            System.out.print("Nuevo Departamento: ");
            profesor.setDepartamento(leer.readLine());
            System.out.print("Nuevo Salario: ");
            profesor.setSalario(Double.parseDouble(leer.readLine()));


            profesorDAO.modificarProfesor(profesor);
        }
    }
    private static void bajaAlumno() throws IOException {
        System.out.println("=== Baja de Alumno ===");
        System.out.print("Ingresa el Número de Expediente del alumno a eliminar: ");
        try {
            int numExpediente = Integer.parseInt(leer.readLine());
            // Llamamos al DAO para ejecutar el DELETE
            alumnoDAO.darBajaAlumno(numExpediente);
        } catch (NumberFormatException e) {
            System.out.println("Error: El número de expediente debe ser un valor numérico.");
        }
    }

    private static void bajaProfesor() throws IOException {
        System.out.println("=== Baja de Profesor ===");
        System.out.print("Ingresa el Número de Empleado del profesor a eliminar: ");
        try {
            int numEmpleado = Integer.parseInt(leer.readLine());
            // Llamamos al DAO para ejecutar el DELETE
            profesorDAO.darBajaProfesor(numEmpleado);
        } catch (NumberFormatException e) {
            System.out.println("Error: El número de empleado debe ser un valor numérico.");
        }
    }
    private static void buscarAlumno() throws IOException {
        System.out.println("=== Buscar Alumno ===");
        System.out.print("Ingresa el Número de Expediente: ");
        try {
            int numExpediente = Integer.parseInt(leer.readLine());
            Alumno alumno = alumnoDAO.buscarAlumnoPorExpediente(numExpediente);

            if (alumno != null) {
                System.out.println("\n--- Datos del Alumno Encontrado ---");
                System.out.println(alumno);
            } else {
                System.out.println("No se encontró ningún alumno con el expediente: " + numExpediente);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un número de expediente válido.");
        }
    }

    private static void buscarProfesor() throws IOException {
        System.out.println("=== Buscar Profesor ===");
        System.out.print("Ingresa el Número de Empleado: ");
        try {
            int numEmpleado = Integer.parseInt(leer.readLine());
            Profesor profesor = profesorDAO.buscarProfesorPorEmpleado(numEmpleado);

            if (profesor != null) {
                System.out.println("\n--- Datos del Profesor Encontrado ---");
                System.out.println(profesor);
            } else {
                System.out.println("No se encontró ningún profesor con el número de empleado: " + numEmpleado);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa un número de empleado válido.");
        }
    }
    // ================= VISTA PRINCIPAL =================
    public static void menu() throws IOException {
        int salir = 0;

        do {
            System.out.println("\n========== MENU PRINCIPAL ==========");
            System.out.println("1.- Inscribir Alumno");
            System.out.println("2.- Mostrar Alumnos");
            System.out.println("3.- Dar de Baja Alumno");
            System.out.println("4.- Registrar Profesor");
            System.out.println("5.- Mostrar Profesores");
            System.out.println("6.- Actualizar Profesor");
            System.out.println("7.- Dar de Baja Profesor");
            System.out.println("8.- Buscar Alumno");      // Modificado / Habilitado
            System.out.println("9.- Buscar Profesor");    // Nueva opción agregada
            System.out.println("10.- Actualizar Alumno");
            System.out.println("11.- Salir");
            System.out.println("====================================");
            System.out.print("Elige tu Opción: ");

            try {
                salir = Integer.parseInt(leer.readLine());
                switch (salir) {
                    case 1: inscribir(); break;
                    case 2: mostrarAlumnos(); break;
                    case 3: bajaAlumno(); break;
                    case 4: registrarProfesor(); break;
                    case 5: mostrarProfesores(); break;
                    case 6: actualizarProfesor(); break;
                    case 7: bajaProfesor(); break;
                    case 8: buscarAlumno(); break;     // Llama a la búsqueda de alumnos
                    case 9: buscarProfesor(); break;   // Llama a la búsqueda de profesores
                    case 10: actualizarALumno(); break;
                    case 11: System.out.println("Saliendo del programa..."); break;
                    default: System.out.println("Opción Inválida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido.");
                salir = 0;
            }
        } while(salir != 11);
    }

    static {
        leer = new BufferedReader(new InputStreamReader(System.in));
    }
}