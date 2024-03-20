package ma.fstm.ilisi.projects.webmvc.controller;

import ma.fstm.ilisi.projects.webmvc.dto.*;
import ma.fstm.ilisi.projects.webmvc.service.EmployeeService;
import ma.fstm.ilisi.projects.webmvc.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins="*")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeservice;
	@Autowired
	private EntrepriseService entrepriseservice;

	@GetMapping("/entreprises/{id}/employees")
	public String getEmployeesByEntreprise(@PathVariable(name = "id") int id, Model model) {
		model.addAttribute("entreprise", entrepriseservice.getEntreprise(id));
		model.addAttribute("employees", employeeservice.getEmployeesByEntreprise(id));
		return "employees";
	}

	@GetMapping("/entreprises/{id}/employees/new")
	public String NouvelEmployee(@PathVariable(name = "id") int id, Model model) {
		model.addAttribute("empdto",new EmployeeDTO());
		model.addAttribute("entreprise", entrepriseservice.getEntreprise(id));
		return "new_employee";
	}

	@PostMapping("/entreprises/{id}/employees/save")
	public String saveEmployee(@PathVariable(name = "id") int id, @ModelAttribute EmployeeDTO dto) {
		dto.setEntreprise(entrepriseservice.getEntreprise(id));
		employeeservice.saveEmployeeDTO(dto);
		return "redirect:/entreprises/"+id+"/employees";
	}


	@GetMapping("/entreprises/{id}/employees/edit/{idemp}")
	public String updateEmployee(@PathVariable(name = "id") int id, @PathVariable(name = "idemp") int idemp, Model model) {
		model.addAttribute("empdto",employeeservice.getEmployee(idemp));
		model.addAttribute("entreprise", entrepriseservice.getEntreprise(id));
		return "update_employee";
	}

	@GetMapping("/entreprises/{id}/employees/delete/{idemp}")
	public String deleteEmployee(@PathVariable(name = "id") int id, @PathVariable(name = "idemp") int idemp) {
		EmployeeDTO dto=employeeservice.getEmployee(idemp);
		employeeservice.deleteEmployeeDTO(dto);
		return "redirect:/entreprises/"+id+"/employees";
	}
}
