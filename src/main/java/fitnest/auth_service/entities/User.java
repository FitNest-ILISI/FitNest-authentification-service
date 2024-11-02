package fitnest.auth_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

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
    private String IDFace;
    private String IDBack;
    private  String avatar;
    private Long phonenumber ;
    private Date birthdate;
    private String gender ;
    private String description;

    @ElementCollection(targetClass = Interest.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "interests",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @Column(name = "interest")
    private List<Interest> interests;


}
