package br.com.vianna.edu.EasyPets.Model.dashboard;
import br.com.vianna.edu.EasyPets.Dtos.AdminUsersDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class IAdminDashboardImpl implements IAdminDashboard {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public AdminUsersDto getAdminDashboardInfos() {
        Object[] result = (Object[]) entityManager.createNativeQuery(
                "SELECT " +
                        "(SELECT COUNT(a.id) FROM animais a) as qtdAnimais, " +
                        "(SELECT COUNT(c.id) FROM users c WHERE c.tipo_user = 'CUIDADOR') as qtdCuidadores, " +
                        "(SELECT COUNT(c.id) FROM users c WHERE c.tipo_user = 'VETERINARIO') as qtdVeterinarios, " +
                        "(SELECT COUNT(c.id) FROM users c WHERE c.tipo_user = 'ADMINISTRADOR') as qtdAdministradores, " +
                        "(SELECT COUNT(t.id) FROM tarefas t WHERE t.realizada = 0) as qtdAtvAFazer, " +
                        "(SELECT COUNT(t.id) FROM tarefas t WHERE t.realizada = 1) as qtdAtvFeitas"
        ).getSingleResult();

        return new AdminUsersDto(
                ((Number) result[0]).intValue(),
                ((Number) result[1]).intValue(),
                ((Number) result[2]).intValue(),
                ((Number) result[3]).intValue(),
                ((Number) result[4]).intValue(),
                ((Number) result[5]).intValue()
        );
    }

}
