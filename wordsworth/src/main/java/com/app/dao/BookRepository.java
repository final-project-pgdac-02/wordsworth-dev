package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

	
	@Modifying
	@Query("select Distinct(b.category) from Book b")
	List<String> getCategories();


	@Query("select b from Book b where "+"(b.category=:cat or :cat is null) and"+"(b.averageRating >= :rat or :rat is null) and"+"(b.price >= :min or :min is null) and"+"(b.price <= :max or :max is null)")
	List<Book> filterBooks(@Param("cat") Category category, @Param("rat") Double rating,@Param("min") Double minPrice,@Param("max") Double maxPrice);

}
