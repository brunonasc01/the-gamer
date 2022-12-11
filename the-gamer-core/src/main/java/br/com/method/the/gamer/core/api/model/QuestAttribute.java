package br.com.method.the.gamer.core.api.model;

import lombok.Data;

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
@Table(name = "QUEST_ATTRIBUTE")
public class QuestAttribute extends AuditEntity{

    @Id
    @SequenceGenerator(name = "questAttributeSequenceGenerator", sequenceName = "QUEST_ATTRIBUTE_SQ", initialValue = 1000)
    @GeneratedValue(generator = "questAttributeSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private AttributeType type;

    @Column(name = "WEIGHT")
    private Integer weight;
}
