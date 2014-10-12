package source;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    /**
     * Method to find by name.
     * 
     * @param name name to find role
     * @return list of all roles with given name.
     */
    List<Role> findByName(String name);
}
