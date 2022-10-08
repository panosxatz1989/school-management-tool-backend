package school.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "provisions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(name = "facility_services", joinColumns = {
            @JoinColumn(name = "service_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "facility_id")
    })
    private List<Facility> facilities;

    @ManyToOne(optional = false)
    private ProvisionType provisionType;

    @OneToMany(mappedBy = "service")
    private List<Subscription> subscriptions;

    @OneToMany(mappedBy = "service")
    private List<Level> levels;

    @OneToMany(mappedBy = "service")
    private List<Lesson> lessons;
}
