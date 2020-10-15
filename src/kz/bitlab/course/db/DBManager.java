package kz.bitlab.course.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/auth?serverTimezone=UTC&useUnicode=true" , "root" , "");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean addUser(User user){
        int rows = 0;
        try {
            PreparedStatement statement  = connection.prepareStatement("INSERT INTO users (email , password , full_name , picture , city_id) VALUES " +
                    "(? , ? , ? , ?,  ?)");
            statement.setString(1 , user.getEmail());
            statement.setString(2 , user.getPassword());
            statement.setString(3 , user.getFullName());
            statement.setString(4 , user.getAvatar());
            statement.setLong(5 , user.getCity().getId());

            rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static User getUser(String email){
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT u.email , u.password , u.full_name , u.picture , u.city_id , c.name AS city_name  , c.country_id , cnt.name AS country_name , cnt.code " +
                    "FROM users u " +
                    "INNER JOIN cities c ON c.id = u.city_id " +
                    "INNER JOIN countries cnt ON cnt.id = c.country_id " +
                    "WHERE u.email = ? ");
            statement.setString(1 , email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("picture"),
                        new Cities(resultSet.getLong("city_id"),
                                resultSet.getString("city_name"),
                                new Countries(resultSet.getLong("country_id") ,
                                        resultSet.getString("country_name"),
                                        resultSet.getString("code")))
                );
                statement.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static boolean updateAvatar(User user){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET picture = ? WHERE id= ? ");
            statement.setString(1 , user.getAvatar());
            statement.setLong(2 , user.getId());
            rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static boolean updateFullName(User user){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET full_name = ? WHERE id = ? ");
            statement.setString(1 , user.getFullName());
            statement.setLong(2 , user.getId());
            rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static boolean updatePassword(User user){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET password = ? WHERE id = ? ");
            statement.setString(1 , user.getPassword());
            statement.setLong(2 , user.getId());
            rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static boolean addHotel(Hotel hotel){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO hotels (id , name , author_id , description , stars , price , added_date) " +
                    "VALUES (null  , ? , ? ,? , ? , ? , NOW() )");
            statement.setString(1 , hotel.getName());
            statement.setLong(2 ,hotel.getAuthor().getId());
            statement.setString(3 , hotel.getDescription());
            statement.setInt(4 , hotel.getStars());
            statement.setInt(5 , hotel.getPrice());
            rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static ArrayList<Hotel> getAllHotels(){
        ArrayList<Hotel> hotels = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT h.id , h.name , h.author_id , u.full_name , u.picture , h.description , h.stars , h.price , h.added_date " +
                    "FROM hotels h " +
                    "INNER JOIN users u ON u.id = h.author_id " +
                    "ORDER BY h.price ASC");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                hotels.add(
                        new Hotel(resultSet.getLong("id"),
                                resultSet.getString("name"),
                                new User(resultSet.getLong("author_id"),
                                        null , null,
                                        resultSet.getString("full_name"),
                                        resultSet.getString("picture"),
                                        null),
                                resultSet.getString("description"),
                                resultSet.getInt("stars"),
                                resultSet.getInt("price"),
                                resultSet.getTimestamp("added_date")
                        )
                );
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return hotels;
    }


    public static Hotel getHotel(Long id){
        Hotel hotel = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT h.id , h.name , h.author_id , u.full_name , u.picture , h.description , h.stars , h.price , h.added_date " +
                    "FROM hotels h " +
                    "INNER JOIN users u ON u.id = h.id " +
                    "WHERE h.id = ?");
            statement.setLong(1 , id);
            ResultSet resultSet  = statement.executeQuery();
            while (resultSet.next()){
                hotel = new Hotel(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        new User(
                                resultSet.getLong("author_id"),
                                null , null,
                                resultSet.getString("full_name"),
                                resultSet.getString("picture"),
                                null
                        ),resultSet.getString("description"),
                        resultSet.getInt("stars"),
                        resultSet.getInt("price"),
                        resultSet.getTimestamp("added_date")

                );
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return hotel;
    }

    public static boolean updateHotel(Hotel hotel){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE hotels SET name = ? , description = ? , stars = ? , price = ? WHERE id = ?");
            statement.setString(1 , hotel.getName());
            statement.setString(2 , hotel.getDescription());
            statement.setInt(3 , hotel.getStars());
            statement.setInt(4 , hotel.getPrice());
            statement.setLong(5 , hotel.getId());
            rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static boolean deleteHotel(Long id){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM hotels WHERE id = ?");
            statement.setLong(1 , id);
            rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static boolean addComent(Comment comment){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO comments (hotel_id , user_id , comment , added_date) " +
                    "VALUES (? , ? , ? , NOW())");
            statement.setLong(1 , comment.getHotel().getId());
            statement.setLong(2 , comment.getUser().getId());
            statement.setString(3 , comment.getComment());
            rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static ArrayList<Comment> getAllComments(Long hotelId){
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id , c.user_id , c.hotel_id, c.comment , c.added_date, u.full_name , u.picture " +
                    "FROM comments c " +
                    "INNER JOIN users u ON u.id = c.user_id " +
                    "WHERE c.hotel_id = ? " +
                    "ORDER BY c.added_date DESC ");
            statement.setLong(1 , hotelId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                comments.add(new Comment(
                        resultSet.getLong("id"),
                        new Hotel(
                                resultSet.getLong("id"),
                                null, null ,null, 0 , 0 , null
                        ),
                        new User(resultSet.getLong("hotel_id"),
                                null , null , resultSet.getString("full_name") ,
                                resultSet.getString("picture"),
                                null),
                        resultSet.getString("comment"),
                        resultSet.getTimestamp("added_date")
                ));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return comments;
    }

    public static boolean deleteComment(Long id){
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM comments WHERE id = ?");
            statement.setLong(1 , id);
            rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static Comment getComment(Long id){
        Comment comment = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT c.id , c.user_id , c.hotel_id , c.comment , c.added_date ,  u.full_name , u.picture  " +
                    "FROM comments c " +
                    "INNER JOIN users u ON u.id = c.user_id " +
                    "WHERE c.id = ?");
            statement.setLong(1 , id);
            ResultSet resultSet  = statement.executeQuery();
            while (resultSet.next()){
                comment = new Comment(
                                        resultSet.getLong("id"),
                        new Hotel(
                                                resultSet.getLong("hotel_id"),
                                                null , null , null , 0 , 0 , null
                                        ),
                        new User(
                                                resultSet.getLong("user_id"),
                                                null , null,
                                                resultSet.getString("full_name"),
                                                resultSet.getString("picture"),
                                null
                                        ),
                                        resultSet.getString("comment"),
                                        resultSet.getTimestamp("added_date")
                                );
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return comment;
    }

    public static ArrayList<Countries> getAllCountries(){
        ArrayList<Countries> countries = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM countries");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                countries.add(
                        new Countries(resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("code"))
                );
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return countries;
    }

    public static ArrayList<Cities> getCityByCountryId(Long id){
        ArrayList<Cities> cities = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id , c.name , c.country_id , cnt.name AS country_name , cnt.code " +
                    "FROM cities c " +
                    "INNER JOIN countries cnt ON cnt.id = c.country_id " +
                    "WHERE c.country_id = ? ");
            statement.setLong(1 , id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                cities.add(new Cities(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        new Countries(
                                resultSet.getLong("country_id"),
                                resultSet.getString("country_name"),
                                resultSet.getString("code")
                        )
                ));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return cities;
    }

    public static Cities getCityById(Long id){
        Cities city = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT c.id , c.name , c.country_id , cnt.name AS country_name , cnt.code " +
                    "FROM cities c " +
                    "INNER JOIN countries cnt ON cnt.id = c.country_id " +
                    "WHERE c.id = ? ");
            statement.setLong(1 , id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                city = new Cities(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        new Countries(
                                resultSet.getLong("country_id"),
                                resultSet.getString("country_name"),
                                resultSet.getString("code")
                        )
                );
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return city;
    }
}
