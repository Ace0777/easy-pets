package br.com.vianna.edu.EasyPets.Controller;

import br.com.vianna.edu.EasyPets.Model.Dtos.AdminUsersDto;
import br.com.vianna.edu.EasyPets.Service.AdminDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class AdminDashboardController {

    private final AdminDashboardService service;

    @Autowired
    public AdminDashboardController(AdminDashboardService service) {
        this.service = service;
    }

    @GetMapping("/admin")
    public AdminUsersDto getAdminUsersStatistics() {
        return service.getAdminUsersStatistics();
    }
}
