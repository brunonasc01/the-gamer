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

@Data
@Entity
@Table(name = "TASK_ATTRIBUTE")
public class TaskAttribute extends AuditEntity{

    @Id
    @SequenceGenerator(name = "taskAttributeSequenceGenerator", sequenceName = "TASK_ATTRIBUTE_SQ", initialValue = 1000)
    @GeneratedValue(generator = "taskAttributeSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private AttributeType type;

    @Column(name = "WEIGHT")
    private Integer weight;
}
