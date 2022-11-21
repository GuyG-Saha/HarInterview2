package harel.hometest.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
public class Operation {
    @Id
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID uuid;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    private Timestamp timestamp;
    private double result;

    public Operation() {
        uuid = UUID.randomUUID();
    }
}
