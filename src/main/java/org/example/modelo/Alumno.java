//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.example.modelo;

public class Alumno extends PersonaUT {
    private int numExpediente;
    private String grupo;
    private double promedio;

    public Alumno() {

        super();
    }

    public Alumno( int numExpediente, String nombre, String curp, String grupo, double promedio) {
        super(nombre, curp);
        this.setNumExpediente(numExpediente);
        this.setGrupo(grupo);
        this.setPromedio(promedio);
    }

    public int getNumExpediente() {
        return this.numExpediente;
    }

    public void setNumExpediente(int numExpediente) {
        if (numExpediente > 2000000000 && numExpediente < 2140000000) {
            this.numExpediente = numExpediente;
        } else {
            System.out.println("Numero de Expediente invalido");
        }

    }

    public String getGrupo() {
        if (!this.grupo.isEmpty() && !this.grupo.isBlank()) {
            return this.grupo;
        } else {
            System.out.println("El grupo es requeridp");
            return "";
        }
    }

    public void setGrupo(String grupo) {
        if (!grupo.trim().isEmpty() && !grupo.isBlank()) {
            this.grupo = grupo;
        } else {
            System.out.println("El grupo es requeridp");
        }

    }

    public double getPromedio() {
        String promedioFormato = String.format("%.1f", this.promedio);
        return Double.parseDouble(promedioFormato);
    }

    public void setPromedio(double promedio) {
        if (promedio >= 0.0 && promedio <= 10.0) {
            this.promedio = promedio;
        } else {
            System.out.println("Promedio Invalido");
        }

    }

    public String toString() {
        int var10000 = this.getNumExpediente();
        return "Numero de Expediente: " + var10000 + "\n" + super.toString() + "\nGrupo: " + this.getGrupo() + "\nPromedio: " + this.getPromedio() + "\n=====================================";
    }

    //public void setNombre(String nombre) {
   // }
}