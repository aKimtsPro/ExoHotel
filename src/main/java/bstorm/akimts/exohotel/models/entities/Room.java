package bstorm.akimts.exohotel.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Room extends BaseEntity<Long> {


    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false)
    private RoomType type;

    @Column(nullable = false)
    private double price;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Amenities> amenities = new LinkedHashSet<>();

}
