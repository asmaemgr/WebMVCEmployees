package ma.fstm.ilisi.projects.webmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntrepriseDTO {
    private int id;
    private String nom;
    private String adresse;
    private String email;
    private List<EmployeeDTO> employees;
}
