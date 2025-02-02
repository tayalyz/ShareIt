package ru.company.shareit.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.company.shareit.item.model.Item;

import java.util.List;

@Repository
public interface ItemJpaRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i JOIN FETCH i.owner WHERE i.owner.id = :ownerId")
    List<Item> findAllByOwner_IdFetch(Long ownerId);

    List<Item> findAllByNameContainingIgnoreCaseAndAvailableOrDescriptionContainingIgnoreCaseAndAvailable(String name, Boolean available, String description, Boolean available2);
}
