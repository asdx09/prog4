package hu.pte.mik.prog4.repository;

import hu.pte.mik.prog4.entity.UserEntity;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository extends Repository {

    private static final Logger LOGGER = Logger.getLogger(UserRepository.class);

    public UserEntity findByUsername(String username) {
        try(Connection connection = this.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT id, username, pass FROM user WHERE username = ?")) {
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setId(rs.getLong("id"));
                userEntity.setUsername(rs.getString("username"));
                userEntity.setPass(rs.getString("pass"));

                return userEntity;
            } else {
                return null;
            }

        } catch (SQLException e) {
            LOGGER.error("Felhasználó lekérdezése közben hiba történt!" + e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (NamingException e) {
            LOGGER.error("Egyéb hiba történt a felhasználó lekérdezésekor!" + e.getMessage(), e);
            throw new RuntimeException(e);
        }

    }

}
