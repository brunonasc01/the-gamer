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
@Table(name = "ACHIEVEMENT")
public class Achievement {

    @Id
    @SequenceGenerator(name = "achievementSequenceGenerator", sequenceName = "ACHIEVEMENT_SQ", initialValue = 1000)
    @GeneratedValue(generator = "achievementSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false)
    private Long id;
    
    @Column(name = "NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIER")
    private AchievementTier tier;

    @Column(name = "POINTS")
    private Long points;
}
