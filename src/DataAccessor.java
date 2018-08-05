import javafx.collections.ObservableList;

import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;
import java.sql.Statement ;
import java.sql.ResultSet ;

import java.util.List ;
import java.util.ArrayList ;

public class DataAccessor {
    private Connection connection;

    public DataAccessor(String connectionString) throws SQLException, ClassNotFoundException {
        connection = DriverManager.getConnection(connectionString);
        System.out.println("> Connection success");
        System.out.println("> Database schema: " + connection.getSchema());
        System.out.println("> Catalog: "+ connection.getCatalog());
        System.out.println("> Client info: " + connection.getClass().toString());
    }

    // Get Game table
    public List<Game> getGameList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from game");
        ){
            List<Game> games = new ArrayList<>();
            while (rs.next()) {

                String game_id = rs.getString("game_id");
                String game_title = rs.getString("game_title");
                System.out.println(game_id + " - " + game_title);
                Game game = new Game(game_id, game_title);
                games.add(game);
            }
            return games ;
        }
    }

    public List getGameTitle() throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("select * from Game");
        ) {
            List games = new ArrayList();
            while (rs.next()) {
                String order = rs.getString("game_id");
                String game_title = rs.getString("game_title");
                String log = String.format(order+ " " + game_title);
                games.add(log);
            }
            return games;
        }

    }

    // PLAYER
    public List<Player> getPlayerList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from player");
                )
        {

            List<Player> players = new ArrayList<>();
            while (rs.next()) {

                // Init
                String player_id = rs.getString("player_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String address = rs.getString("address");
                String postal_code = rs.getString("postal_code");
                String province = rs.getString("province");
                String phone_number = rs.getString("phone_number");
                // Log
                System.out.println(player_id + " - " + first_name + " " + last_name);
                Player player = new Player(player_id,
                        first_name,
                        last_name,
                        address,
                        postal_code,
                        province,
                        phone_number);

                players.add(player);
            }

            return players;
        }
    }

    public List getPlayerName() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from Player");
        ) {
            List games = new ArrayList();
            while (rs.next()) {
                String order = rs.getString("player_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String log = String.format(order + " " + firstName + " " + lastName);
                games.add(log);
            }
            return games;
        }
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("Connection closed");
        }
    }

}
