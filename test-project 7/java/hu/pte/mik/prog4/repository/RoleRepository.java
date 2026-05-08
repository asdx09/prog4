package hu.pte.mik.prog4.repository;

import hu.pte.mik.prog4.entity.RoleEntity;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends Repository {

    private static final Logger LOGGER = Logger.getLogger(RoleRepository.class);

    public List<RoleEntity> findRolesByUser(Long userId) {
        try(Connection connection = this.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT r.id, r.code, r.description " +
                            "FROM role r JOIN user_role ur ON r.id = ur.role_id " +
                            "JOIN user u ON u.id = ur.user_id WHERE u.id = ?"
            )) {
            stmt.setLong(1, userId);

            ResultSet rs = stmt.executeQuery();
            List<RoleEntity> roleEntityList = new ArrayList<>();
            while (rs.next()) {
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setId(rs.getLong("id"));
                roleEntity.setCode(rs.getString("code"));
                roleEntity.setDescription(rs.getString("description"));
                roleEntityList.add(roleEntity);
            }

            return roleEntityList;
        } catch (SQLException e) {
            LOGGER.error("Szerepkörök lekérdezése közben hiba történt!" + e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (NamingException e) {
            LOGGER.error("Egyéb hiba történt a szerepkörök lekérdezésekor!" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
