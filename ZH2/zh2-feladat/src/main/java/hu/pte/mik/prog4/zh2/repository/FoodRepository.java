package hu.pte.mik.prog4.zh2.repository;

import hu.pte.mik.prog4.zh2.entity.FoodEntity;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodRepository extends Repository {

    private static final Logger LOGGER = Logger.getLogger(FoodRepository.class);

    public FoodEntity save(FoodEntity food) {
        if(food.getId() == null)
        {
            try(Connection connection = this.getConnection();
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO food(restaurant_name, food_name, price) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS))
            {
                stmt.setString(1, food.getRestaurantName());
                stmt.setString(2, food.getFoodName());
                stmt.setString(3, food.getPrice());

                stmt.executeQuery();

                ResultSet generatedKeys = stmt.getGeneratedKeys();

                return generatedKeys.next() ? this.findById(generatedKeys.getLong(1)) : null;
            } catch (SQLException e)
            {
                LOGGER.error("Ez szar: food save!" + e.getMessage(), e);
                throw new RuntimeException(e);
            } catch (NamingException e)
            {
                LOGGER.error("Ez fos: food save!" + e.getMessage(), e);
                throw new RuntimeException(e);
            }
        } else {
            return this.update(food);
        }
    }

    public FoodEntity update(FoodEntity food) {
        try(Connection connection = this.getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE food SET restaurant_name = ?, food_name = ?, price = ? WHERE ID = ?"))
        {
            stmt.setString(1, food.getRestaurantName());
            stmt.setString(2, food.getFoodName());
            stmt.setString(3, food.getPrice());
            stmt.setLong(4,food.getId());

            stmt.executeUpdate();

            return this.findById(food.getId());
        } catch (SQLException e)
        {
            LOGGER.error("Ez szar: food update!" + e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (NamingException e)
        {
            LOGGER.error("Ez fos: food update!" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public FoodEntity findById(Long id) {
        try(Connection connection = this.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT ID, restaurant_name, food_name, price FROM food WHERE ID = ?"))
        {
            stmt.setLong(1,id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                FoodEntity fe = new FoodEntity(
                        rs.getLong("id"),
                        rs.getString("restaurant_name"),
                        rs.getString("food_name"),
                        rs.getString("price")
                );
                return fe;
            }else
            {
                return null;
            }
        } catch (SQLException e)
        {
            LOGGER.error("Ez szar: food findbyid!" + e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (NamingException e)
        {
            LOGGER.error("Ez fos: food findbyid!" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public List<FoodEntity> listAll() {
        try(Connection connection = this.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT ID, restaurant_name, food_name, price FROM food"))
        {
            ResultSet rs = stmt.executeQuery();

            List<FoodEntity> feList = new ArrayList<>();
            while(rs.next())
            {
                FoodEntity fe = new FoodEntity(
                        rs.getLong("id"),
                        rs.getString("restaurant_name"),
                        rs.getString("food_name"),
                        rs.getString("price")
                );
                feList.add(fe);
            }
            return feList;
        } catch (SQLException e)
        {
            LOGGER.error("Ez szar: food listall!" + e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (NamingException e)
        {
            LOGGER.error("Ez fos: food listall!" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
