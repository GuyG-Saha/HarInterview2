package harel.hometest.springboot.controller;

import harel.hometest.springboot.dto.OperationDTO;
import harel.hometest.springboot.entity.Operation;
import harel.hometest.springboot.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/")
public class RestController {
    @Autowired
    OperationService operationService;

    @GetMapping("/{operationId}")
    public ResponseEntity<?> getById(@PathVariable String operationId) {
        try {
            UUID operationUuId = UUID.fromString(operationId);
            return ResponseEntity.ok(operationService.findOperation(operationUuId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NullPointerException ne) {
            return ResponseEntity.badRequest().body("Operation Id not found");
        }
    }

    @GetMapping("/")
    public ResponseEntity<Page<Operation>> getAllOperations() {
        return ResponseEntity.ok(operationService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<Operation> postOperationResult(@RequestBody OperationDTO operationDTO) {
        Operation operation = operationService.saveOperation(operationDTO);
        if (Objects.nonNull(operation))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(operation);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
