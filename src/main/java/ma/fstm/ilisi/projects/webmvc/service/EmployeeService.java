package ma.fstm.ilisi.projects.webmvc.service;

import ma.fstm.ilisi.projects.webmvc.bo.Employee;
import ma.fstm.ilisi.projects.webmvc.dto.EmployeeDTO;
import ma.fstm.ilisi.projects.webmvc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class EmployeeService implements EmployeeServiceInterface{
	@Autowired
private EmployeeRepository employeerepository;

@Override
public List<EmployeeDTO> getAllEmployee() {
	return employeerepository.findAll().stream()
			.map(e->this.fromEmployee(e))
			.collect(Collectors.toList());
}

public void saveEmployeeDTO(EmployeeDTO dto) {
	employeerepository.save(this.toEmployee(dto));
}
@Override
public EmployeeDTO createEmployee(EmployeeDTO employeedto) {

return this.fromEmployee(employeerepository.save(this.toEmployee(employeedto)));
}

@Override
public EmployeeDTO updateEmployee(int id, EmployeeDTO new_employeedto) {
	Optional<Employee> old_employee=employeerepository.findById(id);
	
	if(old_employee.isPresent()) {
		Employee updatable_employee=old_employee.get();
		
		updatable_employee.setNom(new_employeedto.getNom());
		updatable_employee.setPrenom(new_employeedto.getPrenom());
		updatable_employee.setEmail(new_employeedto.getEmail());
		updatable_employee.setAdresse(new_employeedto.getAdresse());
		updatable_employee.setSalaire(new_employeedto.getSalaire());
		updatable_employee.setEntreprise(new EntrepriseService().toEntreprise(new_employeedto.getEntreprise()));
		return this.fromEmployee(employeerepository.save(updatable_employee));
	}
	else
		throw new RuntimeException("Employee not found");
}

@Override
public void deleteEmployee(int id) {
	
	 employeerepository.deleteById(id);
}

public void deleteEmployeeDTO(EmployeeDTO dto) {
	employeerepository.delete(this.toEmployee(dto));

}

@Override
public EmployeeDTO getEmployee(int id) {

	Optional<Employee> employee=employeerepository.findById(id);
	if(employee.isPresent())
	return this.fromEmployee(employee.get());
	else
		return null;
}

	@Override
	public List<EmployeeDTO> getEmployeesByEntreprise(int id) {
	    Optional<List<Employee>> employees=employeerepository.findByEntrepriseId(id);
		if(employees.isPresent())
	        return employees.get().stream()
	                .map(e->this.fromEmployee(e))
	                .collect(Collectors.toList());
		return null;
	}

	public Employee toEmployee(EmployeeDTO employeedto) {
		
		return Employee.builder()
		 .id(employeedto.getId())
		.prenom(employeedto.getPrenom())
		.nom(employeedto.getNom())
		.email(employeedto.getEmail())
		.adresse(employeedto.getAdresse())
		.salaire(employeedto.getSalaire())
				.entreprise(new EntrepriseService().toEntreprise(employeedto.getEntreprise()))
				.build();
		
		
	}
public EmployeeDTO fromEmployee(Employee employee) {
		
	return	EmployeeDTO.builder()
			.id(employee.getId())
			.prenom(employee.getPrenom())
			.nom(employee.getNom())
			.email(employee.getEmail())
			.adresse(employee.getAdresse())
			.salaire(employee.getSalaire())
			.entreprise(new EntrepriseService().fromEntreprise(employee.getEntreprise()))
			.build();
	}
}
