package ru.sberbank.jd.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    List<Category> findByName(String name);
}