package ma.fstm.ilisi.projects.webmvc.dto;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

private int id;
private String nom;
private String prenom;
private String email;
private String adresse;
private float salaire;
private EntrepriseDTO entreprise;
}