package br.com.method.the.gamer.core.api.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Task {
    
    private String name;
    
    private String description;
    
    private Long duration;
    
    private Integer dificult;
    
    private LocalDateTime start;
    
    private LocalDateTime end;
    
    private List<String> attributes;
    
    private String status;
}
