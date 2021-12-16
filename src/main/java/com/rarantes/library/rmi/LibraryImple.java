/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rarantes.library.rmi;

import com.rarantes.library.rmi.model.Book;
import com.rarantes.library.rmi.service.BookService;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author Raissa
 */
public class LibraryImple extends UnicastRemoteObject implements ILibrary {
    private static final long serialVersionUID = 1L;
    BookService bookService = new BookService();
    
    protected LibraryImple() throws RemoteException {
        super();
        bookService.readJson();
    }
    public String getBooks(String name) throws RemoteException {
        String response = "";
        List<Book> books = bookService.findBooksByTitle(name);
        if (books.isEmpty()) {
            response = "Não foram encontrados livros conforme este título.\n\n";
        } else {
            for (Book book: books) {
                response += book.toString();
            }
        }
        return response;
    }
    public String getBook(String isbn) throws RemoteException {
        Book book = bookService.findBookByIsbn(isbn);
        return book.toString();
    }
    public String postBook(Book book) throws RemoteException {
        try {
            bookService.addNewBook(book);
            return "\n\nLivro adicionado com sucesso!\n\nInformações do livro:\n"
                + book.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Ocorreu um erro ao adicionar o livro.";
        }
    }
    public String putBook(Book request) throws RemoteException {
        try {
            Book book = bookService.findBookByIsbn(request.getIsbn());
            System.out.println("ok");
            if (book != null) {
                book.setTitle(request.getTitle());
                book.setGenre(request.getGenre());
                book.setEdition(request.getEdition());
                book.setYear(request.getYear());
                book.setPublishingCompany(request.getPublishingCompany());
                book.setAuthors(request.getAuthors());
                return "\n\nLivro atualizado com sucesso!\n\nInformações do livro:\n"
                        + book.toString();
            } else {
                return "Livro não encontrado.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Ocorreu um erro durante a busca.";
        }
    }
    public String deleteBook(String isbn) throws RemoteException {
        Book book = bookService.findBookByIsbn(isbn);
        if (book != null) {
            bookService.deleteBookByIsbn(isbn);
            return "\n\nLivro removido com sucesso!\n\n";
        } else {
            return "Ocorreu um erro ao remover o livro.";
        }
    }

    public void save() {
        bookService.writeJson();
    }
}
