package de.adrianaschepers.shishabuddies.repo;

import de.adrianaschepers.shishabuddies.model.Photo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PhotoRepository extends PagingAndSortingRepository<Photo,String> {
    List<Photo> findAll();
}
