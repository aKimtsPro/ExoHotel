package bstorm.akimts.exohotel.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity<T extends Serializable>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
    @Column(name = "last_update")
    private LocalDate lastUpdate;
    @Column(name = "active", nullable = false)
    private boolean active = true;


    @PreUpdate
    public void preUpdate() {
        lastUpdate = LocalDate.now();
    }

    @PrePersist
    public void prePersist(){
        createdAt = LocalDate.now();
    }
}
