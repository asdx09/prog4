package hu.pte.mik.prog4.zh2.repository;

import hu.pte.mik.prog4.zh2.entity.FoodEntity;
import hu.pte.mik.prog4.zh2.entity.RoleEntity;
import hu.pte.mik.prog4.zh2.entity.UserEntity;
import org.apache.log4j.Logger;

import javax.management.relation.Role;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends Repository {
    private static final Logger LOGGER = Logger.getLogger(FoodRepository.class);

    public List<RoleEntity> findRolesByUser(UserEntity userEntity) {
        try(Connection connection = this.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT r.ID, r.CODE, r.DESCRIPTION "+
                                                                     "FROM role r JOIN user_role ur ON r.id = ur.role_id "+
                                                                     "JOIN user u ON u.id = ur.user_id WHERE u.id = ?"))
        {
            stmt.setLong(1,userEntity.getId());

            ResultSet rs = stmt.executeQuery();
            List<RoleEntity> reList = new ArrayList<>();

            while(rs.next())
            {
                RoleEntity re = new RoleEntity();
                re.setId(rs.getLong("ID"));
                re.setCode(rs.getString("CODE"));
                re.setDescription(rs.getString("DESCRIPTION"));
                reList.add(re);
            }

            return reList;
        } catch (SQLException e)
        {
            LOGGER.error("Ez szar " + e.getMessage(),e);
            throw new RuntimeException(e);
        } catch (NamingException e)
        {
            LOGGER.error("Ez fos " + e.getMessage(),e);
            throw new RuntimeException(e);
        }
    }

}
