/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rarantes.library.rmi;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Raissa
 */
public class LibraryServer {
    LibraryServer() {
        try {
                System.setProperty("java.rmi.server.hostname", "localhost");
                LocateRegistry.createRegistry(1099);
                ILibrary b = new LibraryImple();
                
                Naming.bind("BookService", (Remote) b);
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    public static void main(String [] args) {
        new LibraryServer();
    }
}