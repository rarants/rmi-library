/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rarantes.library.rmi;

import com.rarantes.library.rmi.model.Book;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Raissa
 */
public interface ILibrary extends Remote {
    public void save() throws RemoteException;
    public String getBooks(String name) throws RemoteException;
    public String getBook(String isbn) throws RemoteException;
    public String postBook(Book book) throws RemoteException;
    public String putBook(Book book) throws RemoteException;
    public String deleteBook(String isbn) throws RemoteException;
}
