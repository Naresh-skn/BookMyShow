package com.project.BookMyShow.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.BookMyShow.Entity.Theatre;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
	
	
	@Query("select t.theatreId from Theatre t where t.city.cityId = :cityId")
	List<Long> findByCityId(@Param("cityId")Long cityId);

}
