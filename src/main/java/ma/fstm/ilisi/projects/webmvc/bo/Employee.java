package ma.fstm.ilisi.projects.webmvc.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String nom;
private String prenom;
private String email;
private String adresse;
private float salaire;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "id_entreprise")
private Entreprise entreprise;
}
