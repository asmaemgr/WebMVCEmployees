package ma.fstm.ilisi.projects.webmvc.service;

import ma.fstm.ilisi.projects.webmvc.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeServiceInterface {
 List<EmployeeDTO> getAllEmployee();
 EmployeeDTO createEmployee(EmployeeDTO employeedto);
 EmployeeDTO updateEmployee(int id,EmployeeDTO new_employeedto);
 void deleteEmployee(int id); 
 EmployeeDTO getEmployee(int id);

 List<EmployeeDTO> getEmployeesByEntreprise(int id);

}
