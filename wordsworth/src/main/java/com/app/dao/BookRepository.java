package com.app.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.app.pojos.Book;
import com.app.pojos.Category;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findAll();
	
	Optional<Book> findById(Integer id);
	
	List<Book> findByCategory(Category cat);
	
	List<Book> findByBookTitleLike(String bookTitle);
	
	@Query("Select b from Book b where b.bookTitle like %:title%")
	List<Book> getBookByTitle(@Param("title") String title);
	
}
