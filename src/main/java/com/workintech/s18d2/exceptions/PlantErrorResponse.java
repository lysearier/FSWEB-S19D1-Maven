package com.workintech.s18d2.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantErrorResponse {
    private String message;
    private int status;
    private long timestamp;
}
