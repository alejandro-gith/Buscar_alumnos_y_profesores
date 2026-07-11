//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.example.modelo;

public class PersonaUT {
    private String nombre;
    private String curp;

    public PersonaUT() {
        super();
    }

    public PersonaUT(String nombre, String curp) {
        super();
        this.setNombre(nombre);
        this.setCurp(curp);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            System.out.println("Dato Invalido ");
        }

    }

    public String getCurp() {
        return this.curp.toUpperCase();
    }

    public void setCurp(String curp) {
        if (curp != null && curp.trim().length() == 18 && !curp.isBlank()) {
            this.curp = curp;
        } else {
            System.out.println("CURP invalido");
        }

    }
    @Override
    public String toString() {
        String nom = this.getNombre();
        return "Nombre: " + nom + "\nCurp: " + this.getCurp();
    }
}
