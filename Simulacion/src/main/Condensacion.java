/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Usuario
 */
public class Condensacion {
    
    private Particion particion1;
    private Particion particion2;
    private Particion particionfinal;

    public Condensacion(Particion particion1, Particion particion2, Particion particionfinal) {
        this.particion1 = particion1;
        this.particion2 = particion2;
        this.particionfinal = particionfinal;
    }

    public Particion getParticion1() {
        return particion1;
    }

    public Particion getParticion2() {
        return particion2;
    }

    public Particion getParticionfinal() {
        return particionfinal;
    }
    
    
}
