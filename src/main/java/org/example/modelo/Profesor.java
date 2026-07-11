package org.example.modelo;

public class Profesor extends PersonaUT {
    private int numEmpleado;
    private String departamento;
    private double salario;

    public Profesor() {
        super();
    }

    public Profesor(int numEmpleado, String nombre, String curp, String departamento, double salario) {
        super(nombre, curp);
        this.setNumEmpleado(numEmpleado);
        this.setDepartamento(departamento);
        this.setSalario(salario);
    }

    public int getNumEmpleado() {
        return this.numEmpleado;
    }

    public void setNumEmpleado(int numEmpleado) {
        // Validación: Que sea un número positivo de nómina (ej. entre 1000 y 999999)
        if (numEmpleado >= 1000 && numEmpleado <= 999999) {
            this.numEmpleado = numEmpleado;
        } else {
            System.out.println("Número de Empleado inválido (Debe ser entre 1000 y 999999)");
        }
    }

    public String getDepartamento() {
        if (this.departamento != null && !this.departamento.isBlank()) {
            return this.departamento.toUpperCase(); // Formato: Siempre en mayúsculas
        } else {
            System.out.println("El departamento es requerido");
            return "";
        }
    }

    public void setDepartamento(String departamento) {
        // Validación: Que no esté vacío
        if (departamento != null && !departamento.trim().isEmpty() && !departamento.isBlank()) {
            this.departamento = departamento.trim();
        } else {
            System.out.println("Error: El departamento no puede estar vacío");
        }
    }

    public double getSalario() {
        // Formato: Limitar el retorno a 2 decimales para el salario
        String salarioFormato = String.format("%.2f", this.salario);
        return Double.parseDouble(salarioFormato);
    }

    public void setSalario(double salario) {
        // Validación: El salario debe ser mayor al sueldo mínimo base
        if (salario >= 0.0) {
            this.salario = salario;
        } else {
            System.out.println("Error: El salario no puede ser negativo");
        }
    }

    @Override
    public String toString() {
        return "Número de Empleado: " + this.getNumEmpleado() + "\n" +
                super.toString() + "\n" +
                "Departamento: " + this.getDepartamento() + "\n" +
                "Salario: $" + this.getSalario() + "\n" +
                "=====================================";
    }
}