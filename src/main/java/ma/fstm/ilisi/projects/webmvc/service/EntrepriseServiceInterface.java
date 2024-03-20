package ma.fstm.ilisi.projects.webmvc.service;

import ma.fstm.ilisi.projects.webmvc.dto.EntrepriseDTO;

import java.util.List;

public interface EntrepriseServiceInterface {
    List<EntrepriseDTO> getAllEntreprise();
    EntrepriseDTO createEntreprise(EntrepriseDTO entreprisedto);
    EntrepriseDTO updateEntreprise(int id,EntrepriseDTO new_entreprisedto);
    void deleteEntreprise(int id);
    EntrepriseDTO getEntreprise(int id);
}
