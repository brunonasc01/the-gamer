package br.com.method.the.gamer.core.api.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Schedule {
    
    private Gamer gamer;
    
    private LocalDate day;
    
    private List<Task> tasks;
    
    private String status;
}
