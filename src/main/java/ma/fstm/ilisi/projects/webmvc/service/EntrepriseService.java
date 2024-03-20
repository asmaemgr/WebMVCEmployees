package ma.fstm.ilisi.projects.webmvc.service;

import ma.fstm.ilisi.projects.webmvc.bo.Entreprise;
import ma.fstm.ilisi.projects.webmvc.dto.EntrepriseDTO;
import ma.fstm.ilisi.projects.webmvc.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntrepriseService implements EntrepriseServiceInterface {
    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Override
    public List<EntrepriseDTO> getAllEntreprise() {
        return entrepriseRepository.findAll().stream()
                .map(e->this.fromEntreprise(e))
                .collect(Collectors.toList());
    }

    public void saveEntrepriseDTO(EntrepriseDTO dto) {
        entrepriseRepository.save(this.toEntreprise(dto));
    }
    @Override
    public EntrepriseDTO createEntreprise(EntrepriseDTO entreprisedto) {
        return this.fromEntreprise(entrepriseRepository.save(this.toEntreprise(entreprisedto)));
    }

    @Override
    public EntrepriseDTO updateEntreprise(int id, EntrepriseDTO new_entreprisedto) {
        Optional<Entreprise> old_entreprise=entrepriseRepository.findById(id);

        if(old_entreprise.isPresent()) {
            Entreprise updatable_entreprise=old_entreprise.get();

            updatable_entreprise.setNom(new_entreprisedto.getNom());
            updatable_entreprise.setEmail(new_entreprisedto.getEmail());
            updatable_entreprise.setAdresse(new_entreprisedto.getAdresse());
            return this.fromEntreprise(entrepriseRepository.save(updatable_entreprise));
        }
        else
            throw new RuntimeException("Entreprise not found");
    }

    @Override
    public void deleteEntreprise(int id) {
        entrepriseRepository.deleteById(id);
    }

    public void deleteEntrepriseDTO(EntrepriseDTO dto) {
        entrepriseRepository.delete(this.toEntreprise(dto));

    }

    @Override
    public EntrepriseDTO getEntreprise(int id) {
        Optional<Entreprise> entreprise=entrepriseRepository.findById(id);
        if(entreprise.isPresent())
            return this.fromEntreprise(entreprise.get());
        return null;
    }

    public Entreprise toEntreprise(EntrepriseDTO entreprisedto) {

        return Entreprise.builder()
                .id(entreprisedto.getId())
                .nom(entreprisedto.getNom())
                .email(entreprisedto.getEmail())
                .adresse(entreprisedto.getAdresse())
                .build();


    }
    public EntrepriseDTO fromEntreprise(Entreprise entreprise) {

        return	EntrepriseDTO.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .email(entreprise.getEmail())
                .adresse(entreprise.getAdresse())
                .build();
    }
}
