package ma.fstm.ilisi.projects.webmvc.repository;

import ma.fstm.ilisi.projects.webmvc.bo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    Optional<List<Employee>> findByEntrepriseId(int id);

}
