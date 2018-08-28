package app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @NotNull
    private String brand;

    @NotNull
    private String model;

    private String color;

    private Integer maxSpeed;
}