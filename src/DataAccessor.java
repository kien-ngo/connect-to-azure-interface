import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.rmi.RemoteException;
import java.sql.*;

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

    public ObservableList getGameLadder(String gameIdForLadder) throws SQLException {

        String query = "select first_name, last_name, score from Player, PlayerAndGame " +
                "where Player.player_id = PlayerAndGame.player_id and game_id = "+ gameIdForLadder;

        try (
                Statement stmnt = connection.createStatement();


                ResultSet rs = stmnt.executeQuery(query)
                )

        {
            ObservableList ladders = FXCollections.observableArrayList();
            while(rs.next()) {
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String name = first_name + " " + last_name;
                String score = rs.getString("score");

                String log = String.format("%-35s %s", name, score);
                ladders.add(log);
            }

            return ladders;
        }

    }

    public void addGame(String game_id, String game_title) throws SQLException{
        // Duplication error code = 2601
        int PK_DUPLICATE = 2601;

        //String query = "insert into game" +
                //"values("+ game_id+","+game_title+")";
        PreparedStatement query = connection.prepareStatement("insert into game values(?,?)");
        query.setString(1,game_id);
        query.setString(2,game_title);
        try{
            Statement stmnt = connection.createStatement();
            query.executeUpdate();

        } catch(SQLException e){
            if(e.getErrorCode() == PK_DUPLICATE ){
                //duplicate primary key
                System.out.println("Duplicate primary key");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Input error");
                alert.setHeaderText(null);
                alert.setContentText("Game ID is duplicated, please input another value");
                alert.showAndWait();
            }
        }
    }

    public void addPlayer(String player_id,
                          String first_name,
                          String last_name,
                          String address,
                          String postal_code,
                          String province,
                          String phone_number) throws SQLException {

        // Duplication error code = 2601
        int PK_DUPLICATE = 2601;
        PreparedStatement querry = connection.prepareStatement("insert into player values(?, ?, ?, ?, ?, ?, ? )");
        querry.setString(1, player_id);
        querry.setString(2, first_name);
        querry.setString(3, last_name);
        querry.setString(4, address);
        querry.setString(5, postal_code);
        querry.setString(6, province);
        querry.setString(7, phone_number);

        try{
            Statement stmnt = connection.createStatement();
            querry.executeUpdate();

        } catch(SQLException e){
            if(e.getErrorCode() == PK_DUPLICATE ){
                //duplicate primary key
                System.out.println("Duplicate primary key");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Input error");
                alert.setHeaderText(null);
                alert.setContentText("Game ID is duplicated, please input another value");
                alert.showAndWait();
            }
        }
    }


    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("Connection closed");
        }
    }

}
