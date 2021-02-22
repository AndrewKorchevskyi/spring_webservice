package other.booksapi;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

//@RestController
public class BooksController {
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return Arrays.asList(new Book(1, "Book Name", "Book Author"));
    }
}
