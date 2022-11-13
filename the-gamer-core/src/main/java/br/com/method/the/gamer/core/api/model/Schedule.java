package br.com.method.the.gamer.core.api.model;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "SCHEDULE")
public class Schedule {

    @Id
    @SequenceGenerator(name = "scheduleSequenceGenerator", sequenceName = "SCHEDULE_SQ", initialValue = 1000)
    @GeneratedValue(generator = "scheduleSequenceGenerator", strategy = GenerationType.SEQUENCE)
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

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    @JoinColumn(name = "GAMER_ID", referencedColumnName = "ID")
    private Gamer gamer;
    
    @Column(name = "DAY")
    private LocalDate day;

    //TODO Fix Lazy Loading
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "TASK_ID", referencedColumnName = "ID")
    private Set<Task> tasks;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ScheduleStatus status;
}
