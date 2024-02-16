package ru.sberbank.jd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Cost.
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_cost")
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    private Double cost;
    
    @ToString.Exclude
    @ManyToOne
    @JoinColumn
    private Category category;

    @ToString.Exclude    
    @ManyToOne
    @JoinColumn
    private Account account;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn
    private User user;
}
