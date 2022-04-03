package com.app.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.pojos.Book;
import com.app.pojos.Category;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findAll();
	
	Optional<Book> findById(Integer id);
	
	List<Book> findByCategory(Category cat);
	
	
}
