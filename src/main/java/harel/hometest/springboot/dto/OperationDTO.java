package harel.hometest.springboot.dto;

import harel.hometest.springboot.entity.OperationType;
import lombok.Data;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

@Data
/* The input to the service will have 3 parameters
   @param a: first element for math operation
 * @param b: second element for math operation
 * @param operationType: type of operation (plus, minus, multiplication, division */
public class OperationDTO {
    private OperationType operationType;
    private double a;
    private double b;
    public final static Map<String, OperationType> COMMON_MATH_OPS = new HashMap<>();

    public OperationDTO(@NonNull String operationType, double a, double b) {
        COMMON_MATH_OPS.put("+", OperationType.PLUS);
        COMMON_MATH_OPS.put("-", OperationType.MINUS);
        COMMON_MATH_OPS.put("*", OperationType.MULTIPLICATION);
        COMMON_MATH_OPS.put("/", OperationType.DIVISION);
        try {
            this.operationType = OperationType.valueOf(operationType.toUpperCase());
            this.a = a;
            this.b = b;
        } catch (IllegalArgumentException e) {
            this.operationType = COMMON_MATH_OPS.getOrDefault(operationType, OperationType.PLUS);
            this.a = a;
            this.b = b;
        }
    }

}
