package br.com.vianna.edu.EasyPets.Service;

import br.com.vianna.edu.EasyPets.Dtos.AdminUsersDto;
import br.com.vianna.edu.EasyPets.Model.dashboard.IAdminDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminDashboardService {
    @Autowired
    private IAdminDashboard repository;

    public AdminUsersDto getAdminUsersStatistics() {
        return repository.getAdminDashboardInfos();
    }
}