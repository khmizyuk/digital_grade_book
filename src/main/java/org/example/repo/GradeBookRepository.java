package org.example.repo;

import org.example.Entity.GradeBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeBookRepository extends CrudRepository<GradeBook, Long> {
}
