package fitnest.auth_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_user")
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

    @ElementCollection(targetClass = Interest.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "interests",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @Column(name = "interest")
    private List<Interest> interests;


}
