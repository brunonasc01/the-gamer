package br.com.method.the.gamer.core.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "QUEST")
public class Quest {

    @Id
    @SequenceGenerator(name = "questSequenceGenerator", sequenceName = "QUEST_SQ", initialValue = 1000)
    @GeneratedValue(generator = "questSequenceGenerator", strategy = GenerationType.SEQUENCE)
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

    @Enumerated(EnumType.STRING)
    @Column(name = "DIFFICULT")
    private Difficult difficult;

    @Column(name = "START")
    private LocalDateTime start;

    @Column(name = "FINISH")
    private LocalDateTime finish;

    @Column(name = "ATTRIBUTES")
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "QUEST_ID", referencedColumnName = "ID")
    private Set<QuestAttribute> attributes;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private QuestStatus status;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "SCHEDULE_ID", referencedColumnName = "ID")
    private Schedule schedule;
}
