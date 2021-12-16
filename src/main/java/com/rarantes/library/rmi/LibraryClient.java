/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rarantes.library.rmi;

import com.rarantes.library.rmi.model.Book;
import com.rarantes.library.rmi.service.BookService;
import java.io.IOException;
import java.rmi.Naming;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author Raissa
 */
public class LibraryClient {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            ILibrary b = (ILibrary) Naming.lookup("rmi://localhost:1099/BookService");
            System.out.print("Seja bem-vindo a esta Biblioteca!\n"
                    + "Você pode realizar as seguintes operações:\n"
                    + "1- Buscar livros pelo título\n"
                    + "2- Adionar novo livro\n"
                    + "3- Editar livro (digitar o isbn para editá-lo)\n"
                    + "4- Excluir livro (digitar o isbn para excluí-lo)\n"
                    + "5- Sair\n\n");       
            int operation;
            //Espera que o cliente informe a operação até que ele deseje sair
            do {
                //Solicita operação
                System.out.println("Digite o número da operação que você quer executar:");
                operation = Integer.parseInt(scanner.nextLine());
                //Realiza ação conforme operação
                switch (operation) {
                    case 1: {
                        System.out.println("Digite o nome do livro que você quer buscar:");
                        String value = scanner.nextLine();
                        System.out.println(b.getBooks(value));
                        break;
                    }
                    case 2: {
                        System.out.println("Digite as informações do livro que você quer adicionar\n");
                        Book book = getBookInformations(false);
                        System.out.println(b.postBook(book));
                        break;
                    }
                    case 3: {
                        System.out.println("Digite o ISBN do livro que você quer editar:");
                        String value = scanner.nextLine();
                        b.getBook(value);
                        System.out.println("Digite as novas informações do livro que você quer editar");
                        Book book = getBookInformations(true);
                        book.setIsbn(value);
                        System.out.println(b.putBook(book));
                        break;
                    }
                    case 4: {
                        System.out.println("Digite o ISBN do livro que você quer excluir:");
                        String value = scanner.nextLine();
                        System.out.println(b.deleteBook(value));
                        break;
                    }
                }
            } while (operation != 5);
            System.out.println("Salvando alterações...");
            b.save();
            System.out.println("Agradecemos por usar nossa biblioteca!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static Book getBookInformations(boolean isEditing) {
        Scanner scanner = new Scanner(System.in);
        Book book = new Book();
        System.out.println("Título:");
        book.setTitle(scanner.nextLine());
        if (!isEditing) {
            System.out.println("ISBN:");
            book.setIsbn(scanner.nextLine());
        }
        System.out.println("Gênero:");
        book.setGenre(scanner.nextLine());
        System.out.println("Edição:");
        book.setEdition(Integer.parseInt(scanner.nextLine()));
        System.out.println("Ano:");
        book.setYear(Integer.parseInt(scanner.nextLine()));
        System.out.println("Editora:");
        book.setPublishingCompany(scanner.nextLine());
        System.out.println("Autores (separe por /):");
        book.setAuthors(Arrays.asList(scanner.nextLine().split("/")));
        // System.out.println(book.getAuthors());
        return book;
    }
}
