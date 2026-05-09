package hu.pte.mik.prog4.zh2.repository;

import hu.pte.mik.prog4.zh2.entity.FoodEntity;
import hu.pte.mik.prog4.zh2.entity.UserEntity;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository extends Repository {
    private static final Logger LOGGER = Logger.getLogger(FoodRepository.class);

    public UserEntity findByUsername(String username) {
        try(Connection connection = this.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT ID, username, password FROM user WHERE username = ?"))
        {
            stmt.setString(1,username);

            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                UserEntity ue = new UserEntity(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
                return ue;
            }else
            {
                return null;
            }
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
