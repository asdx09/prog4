package hu.pte.mik.prog4.zh2.repository;

import hu.pte.mik.prog4.zh2.entity.RoleEntity;
import hu.pte.mik.prog4.zh2.entity.UserEntity;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends Repository {

    public List<RoleEntity> findRolesByUser(UserEntity userEntity) {
        try(Connection connection = this.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT r.ID, r.CODE, r.DESCRIPTION "+
                                                                    "FROM role r JOIN user_role ur on r.id = ur.role_id "+
                                                                    "JOIN user u ON u.id = ur.user_id WHERE u.id = ?"))
        {
            stmt.setLong(1, userEntity.getId());

            ResultSet rs = stmt.executeQuery();
            List<RoleEntity> reList = new ArrayList<>();

            while(rs.next())
            {
                RoleEntity re = new RoleEntity(
                        rs.getLong("ID"),
                        rs.getString("CODE"),
                        rs.getString("DESCRIPTION")
                );
                reList.add(re);
            }
            return reList;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        catch (NamingException e)
        {
            throw new RuntimeException(e);
        }
    }

}
