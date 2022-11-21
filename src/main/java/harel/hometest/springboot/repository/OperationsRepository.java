package harel.hometest.springboot.repository;

import harel.hometest.springboot.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OperationsRepository extends JpaRepository<Operation, UUID> {

    Optional<Operation> findByUuid(UUID Uuid);

    Optional<List<Operation>> findByOperationType(String operationType);

}
