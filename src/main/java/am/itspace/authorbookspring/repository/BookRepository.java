package am.itspace.authorbookspring.repository;

import am.itspace.authorbookspring.model.Author;
import am.itspace.authorbookspring.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {


}
