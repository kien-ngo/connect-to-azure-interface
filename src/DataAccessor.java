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

                String game_title = rs.getString("game_title");
                games.add(game_title);
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
