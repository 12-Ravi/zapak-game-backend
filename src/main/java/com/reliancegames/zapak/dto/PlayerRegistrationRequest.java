// src/main/java/com/reliancegames/zapak/dto/PlayerRegistrationRequest.java
package com.reliancegames.zapak.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlayerRegistrationRequest {
    @NotBlank(message = "Device ID is required")
    private String deviceId;
    
    @NotBlank(message = "Username is required")
    private String username;
    
    @NotBlank(message = "Platform is required")
    private String platform;
    
    private String country;
}