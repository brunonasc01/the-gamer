package br.com.method.the.gamer.core.api.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "GAMER")
public class Gamer {

    @Id
    @SequenceGenerator(name = "gamerSequenceGenerator", sequenceName = "GAMER_SQ", initialValue = 1000)
    @GeneratedValue(generator = "gamerSequenceGenerator", strategy = GenerationType.SEQUENCE)
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

    @Column(name = "LEVEL")
    private Integer level;

    @Column(name = "EXPERIENCE")
    private Long experience;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "GAMER_ID", referencedColumnName = "ID")
    private Set<Attribute> attributes;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "GAMER_ID", referencedColumnName = "ID")
    private Set<Achievement> achievements;
}
