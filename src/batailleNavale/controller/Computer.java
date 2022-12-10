/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batailleNavale.controller;

import batailleNavale.model.Board;


public class Computer extends Player implements java.io.Serializable{

    /**
     * Constructeur computer
     * @param p board
     */
    public Computer(Board p) { 
        super(false,p);
    }    
    
}
