package fitnest.auth_service.entities;

import fitnest.auth_service.models.AuthEvent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_user")
@Builder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String idFace;
    private String idBack;
    private String profilePicture;
    private Long phoneNumber;
    private Date birthDate;
    private String gender ;
    private String description;
    @ElementCollection
    @Transient
    private List<AuthEvent> createdEvents;

    @ElementCollection(targetClass = Interest.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "interests",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @Column(name = "interest")
    private List<Interest> interests;

    @OneToOne(cascade = CascadeType.ALL) // Cascade allows changes to be automatically reflected in the related entity
    @JoinColumn(name = "account_id", referencedColumnName = "id") // Specifies the foreign key column in User table
    private Account account;
}
