package harel.hometest.springboot.service;

import com.sun.istack.NotNull;
import harel.hometest.springboot.dto.OperationDTO;
import harel.hometest.springboot.entity.Operation;
import harel.hometest.springboot.entity.OperationType;
import harel.hometest.springboot.repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OperationService {
    @Autowired
    private OperationsRepository operationsRepository;

    public Operation saveOperation(final OperationDTO operationDTO) {
        try {
            Operation operation = new Operation();
            operation.setOperationType(operationDTO.getOperationType());
            operation.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            operation.setResult(calculateResult(operationDTO));
            return operationsRepository.save(operation);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Page<Operation> findAll() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "timestamp");
        return operationsRepository.findAll(pageable);
    }

    public Operation findOperation(UUID id) {
        return operationsRepository.findByUuid(id)
                .orElse(null);
    }
    private Double calculateResult(@NotNull final OperationDTO operationDTO) {
        switch (operationDTO.getOperationType().name().toUpperCase()) {
            case "PLUS":
                return operationDTO.getA() + operationDTO.getB();
            case "MINUS":
                return operationDTO.getA() - operationDTO.getB();
            case "MULTIPLICATION":
                return operationDTO.getA() * operationDTO.getB();
            case "DIVISION":
                return operationDTO.getA() / operationDTO.getB();
            default:
                break;
        }
        return null;
    }

}
