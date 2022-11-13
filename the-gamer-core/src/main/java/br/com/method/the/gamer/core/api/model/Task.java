package br.com.method.the.gamer.core.api.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "TASK")
public class Task {

    @Id
    @SequenceGenerator(name = "taskSequenceGenerator", sequenceName = "TASK_SQ", initialValue = 1000)
    @GeneratedValue(generator = "taskSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false)
    private Long id;

    @CreatedBy
    @Column(name = "CREATED_BY", updatable = false, nullable = false, length = 36)
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE", updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY", insertable = false)
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE", insertable = false)
    private LocalDateTime lastModifiedDate;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DURATION")
    private Long duration;

    @Column(name = "DIFFICULT")
    private Integer difficult;

    @Column(name = "START")
    private LocalDateTime start;

    @Column(name = "END")
    private LocalDateTime end;

    //TODO alterar para lista
    @Enumerated(EnumType.STRING)
    @Column(name = "ATTRIBUTES")
    private AttributeType attributes;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private TaskStatus status;
}
