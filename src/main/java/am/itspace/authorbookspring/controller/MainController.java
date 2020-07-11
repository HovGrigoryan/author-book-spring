package am.itspace.authorbookspring.controller;

import am.itspace.authorbookspring.model.Author;
import am.itspace.authorbookspring.model.Book;
import am.itspace.authorbookspring.repository.AuthorRepository;
import am.itspace.authorbookspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;


    @GetMapping("/")
    public String homepage(ModelMap modelMap) {
        List<Author> all = authorRepository.findAll();
        modelMap.addAttribute("authors", all);
        return "home";

    }

    @PostMapping("/addAuthor")
    public String addAuthor(@ModelAttribute Author author) {
        authorRepository.save(author);
        return "redirect:/";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }

    @GetMapping("/author")
    public String allAuthor(ModelMap modelMap) {
        List<Author> all = authorRepository.findAll();
        modelMap.addAttribute("authors", all);
        return "author";

    }

    @GetMapping("/book")
    public String allBook(ModelMap modelMap) {
        List<Book> all = bookRepository.findAll();
        List<Author> allauthor = authorRepository.findAll();
        modelMap.addAttribute("books", all);
        modelMap.addAttribute("authors", allauthor);
        return "book";

    }

    @GetMapping("/deleteAuthor")
    public String deleteAuthor(@RequestParam("id") int id) {
        Author one = authorRepository.getOne(id);
        List<Book> allBook = bookRepository.findAll();
        for (Book book : allBook) {
            if (book.getAuthorId() == one.getId())
                bookRepository.delete(book);
        }
        authorRepository.deleteById(id);
        return "redirect:/author";
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam("id") int id) {
        bookRepository.deleteById(id);
        return "redirect:/book";
    }

    @GetMapping("/updateBookById")
    public String updateBookById(@RequestParam("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("bookById", bookRepository.getOne(id));
        return "updateBook";
    }


    @GetMapping("/updateAuthorById")
    public String updateAuthorById(@RequestParam("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("authorById", authorRepository.getOne(id));
        return "updateAuthor";
    }

    @PostMapping("/updateBook")
    public String updateBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/updateBookById?id=" + book.getId();
    }

    @PostMapping("/updateAuthor")
    public String updateBook(@ModelAttribute("author") Author author) {
        authorRepository.save(author);
        return "redirect:/updateAuthorById?id=" + author.getId();
    }

}
