package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Author;
import models.Book;

import javax.swing.*;

public class AuthorBookController {

    private int authorIDCounter = 100;

    // The author table part
    @FXML
    private TableView<Author> authortable;
    @FXML
    private TableColumn<Author, Integer> author_id;
    @FXML
    private TableColumn<Author, String> author_firstname;
    @FXML
    private TableColumn<Author, String> author_lastname;
    @FXML
    private TableColumn<Author, String> author_country;

    // The book table part
    @FXML
    private TableView<Book> booktable;
    @FXML
    private TableColumn<Book, Integer> book_id;
    @FXML
    private TableColumn<Book, String> book_title;
    @FXML
    private TableColumn<Book, String> book_place;
    @FXML
    private TableColumn<Book, String> book_year;
    @FXML
    private TableColumn<Book, String> book_autor;

    // The registration of a new book
    @FXML
    private TextField reg_title;
    @FXML
    private TextField reg_place;
    @FXML
    private TextField reg_year;
    @FXML
    private ComboBox<Author> reg_author;
    @FXML
    private Button reg_save;

    // The registration of a new author
    @FXML
    private TextField reg_firstname;
    @FXML
    private TextField reg_country;
    @FXML
    private TextField reg_lastname;
    @FXML
    private Button reg_save_author;

    // The data
    private ObservableList<Author> authorData = FXCollections.observableArrayList();
    private ObservableList<Book> bookData = FXCollections.observableArrayList();

    @FXML
    void addBook(ActionEvent event) {

        //Book book = new Book();

    }

    @FXML
    void addAuthor(ActionEvent event) {

        // create the author
        Author author = new Author(
                this.authorIDCounter++,
                reg_firstname.getText(),
                reg_lastname.getText(),
                reg_country.getText()
        );

        // add the author to the list
        this.authorData.add(author);

        // blank the text fields
        reg_firstname.setText("");
        reg_lastname.setText("");
        reg_country.setText("");
    }

    @FXML
    void removeAuthor(ActionEvent event) {
        Author authorToRemove = this.authortable.getSelectionModel().getSelectedItem();

        this.authorData.remove(authorToRemove);
    }

    /**
     * The public constructor, to add some data
     */
    public AuthorBookController() {

        authorData.add(new Author(this.authorIDCounter++, "Meier", "Rolf", "Deutschland"));
        authorData.add(new Author(this.authorIDCounter++, "Brigger", "Eugen A.", "Schweiz"));
        authorData.add(new Author(this.authorIDCounter++, "Famos", "Luisa", "Schweiz"));
        authorData.add(new Author(this.authorIDCounter++, "Super", "Ruedi", "Österreich"));
        authorData.add(new Author(this.authorIDCounter++, "Shaqiri", "Bljerim", "Albanien"));
    }

    @FXML
    private void initialize() {
        // Set up the author table
        author_id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        author_firstname.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        author_lastname.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        author_country.setCellValueFactory(cellData -> cellData.getValue().countryProperty());

        SortedList<Author> sortedData = new SortedList<Author>(authorData);

        sortedData.comparatorProperty().bind(authortable.comparatorProperty());

        authortable.setItems(sortedData);

        // Set up the book table

        // Set up the registration of books
        reg_author.setItems(authorData);
    }

}
