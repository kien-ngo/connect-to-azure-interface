import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.TableView ;
import javafx.scene.control.TableColumn ;
import javafx.scene.control.cell.PropertyValueFactory ;

import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

public class Lab5Test extends Application {
    private DataAccessor data;
    public String gameIdForQuery = "0";
    public String game_id_insert = "";
    public String game_title_insert = "";
    public String connectionString = "jdbc:sqlserver://kienngolab5.database.windows.net:1433;" +
            "database=KienNgo_Lab5COMP228;" +
            "user=kienngo@kienngolab5;" +
            "password=#Absinthe23;" +
            "encrypt=true;" +
            "trustServerCertificate=false;" +
            "hostNameInCertificate=*.database.windows.net;" +
            "loginTimeout=30;";

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Get data


        data = new DataAccessor(connectionString);


        primaryStage.setTitle("Lab 5 - Java");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 600, Color.WHITE);

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        BorderPane borderPane = new BorderPane();



        // Create 3 tabs : AddGame, AddPlayer and ViewTab


        Tab addGame = new Tab();
        addGame.setText("Add a game");
        GridPane addGamePane = new GridPane();
        addGamePane.setPadding(new Insets(10,10,10,10));
        addGamePane.setHgap(5);
        addGamePane.setVgap(5);
        // Add content
        Label gameIdLabel = new Label("Game ID: ");
        TextField gameIdTextField = new TextField();
        Label gameTitleLabel = new Label("Game title: ");
        TextField gameTitleTextField = new TextField();
        Button addGameButton = new Button("Add game");
        Button resetGameButton = new Button("Reset");

        // Action
        addGameButton.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (gameIdTextField.getText() != null && gameIdTextField.getText() !=""
                            && gameTitleTextField.getText() != null && gameTitleTextField.getText() != "") {
                        game_id_insert = gameIdTextField.getText();
                        game_title_insert = gameTitleTextField.getText();
                        data.addGame(game_id_insert,game_title_insert);

                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Input error");
                        alert.setHeaderText(null);
                        alert.setContentText("Please input at least something");
                        alert.showAndWait();
                    }
                    System.out.println("Clicked");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        resetGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameIdTextField.clear();
                gameTitleTextField.clear();
            }
        });


        // Add content to Pane
        addGamePane.add(gameIdLabel,0,0);
        addGamePane.add(gameIdTextField,1,0);
        addGamePane.add(gameTitleLabel,0,1);
        addGamePane.add(gameTitleTextField,1,1);
        addGamePane.add(addGameButton,0,2);
        addGamePane.add(resetGameButton,1,2);

        addGamePane.setAlignment(Pos.CENTER);
        // Add Pane to Tab
        addGame.setContent(addGamePane);



        //-----------------------ADD PLAYER




        Tab addPlayer = new Tab();
        addPlayer.setText("Add a player");
        GridPane addPlayerPane = new GridPane();
        addPlayerPane.setPadding(new Insets(10,10,10,10));
        addPlayerPane.setVgap(5);
        addPlayerPane.setHgap(5);
        // Add content to pane
        Label pAndGIdLabel = new Label("P & G ID: ");
        TextField pAndGTextField = new TextField();
        Label playerIdLabel = new Label("Player ID: ");
        TextField playerIdTextField = new TextField();
        Label firstNameLabel = new Label("First name: ");
        TextField firstNameTextField = new TextField();
        Label lastNameLabel = new Label("Last name: ");
        TextField lastNameTextField = new TextField();
        Label addressLabel = new Label("Address: ");
        TextField addressTextField = new TextField();
        Label postalCodeLabel = new Label("Postal code: ");
        TextField postalCodeTextField = new TextField();
        Label provinceLabel = new Label("Province: ");
        TextField provinceTextField = new TextField();
        Label phoneLabel = new Label("Phone number: ");
        TextField phoneTextField = new TextField();
        Label gamePlayedLabel = new Label("Game played: ");
        ObservableList games = FXCollections.observableList(data.getGameTitle());
        ComboBox gamePlayedComboBox = new ComboBox(games);
        gamePlayedComboBox.setPromptText("Select one from database");
        Label scoreLabel = new Label("Score: ");
        TextField scoreTextField = new TextField();
        Label dateLabel = new Label("Playing date: ");
        DatePicker playingDatePicker = new DatePicker();
        Button addPlayerButton = new Button("Add player");
        Button resetPlayerButton = new Button("Reset");

        resetPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playerIdTextField.clear();
                firstNameTextField.clear();
                lastNameTextField.clear();
                addressTextField.clear();
                postalCodeTextField.clear();
                provinceTextField.clear();
                phoneTextField.clear();
                gamePlayedComboBox.getSelectionModel().clearSelection();
                playingDatePicker.setValue(null);
                playingDatePicker.getEditor().clear();
            }
        });

        addPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // player table
                    String player_id = playerIdTextField.getText();
                    String first_name = firstNameTextField.getText();
                    String last_name = lastNameTextField.getText();
                    String address = addressTextField.getText();
                    String postalCode = postalCodeTextField.getText();
                    String province = provinceTextField.getText();
                    String phone_number = phoneTextField.getText();
                    // Player&Game table
                    String p_g_id = pAndGTextField.getText();
                    String game_id = Integer.toString(gamePlayedComboBox.getSelectionModel().getSelectedIndex() +1);
                    String score = scoreTextField.getText();
                    String playing_date = playingDatePicker.getValue().toString();

                    data.addPlayer(player_id, first_name, last_name, address, postalCode, province, phone_number,p_g_id, game_id, score, playing_date);

                } catch (SQLException e) {

                }
            }
        });
        //


        addPlayerPane.add(pAndGIdLabel,0,0);
        addPlayerPane.add(playerIdLabel,0,1);
        addPlayerPane.add(firstNameLabel,0,2);
        addPlayerPane.add(lastNameLabel,0,3);
        addPlayerPane.add(addressLabel,0,4);
        addPlayerPane.add(postalCodeLabel,0,5);
        addPlayerPane.add(provinceLabel,0,6);
        addPlayerPane.add(phoneLabel,0,7);
        addPlayerPane.add(gamePlayedLabel,0,8);

        addPlayerPane.add(scoreLabel,0,9);
        addPlayerPane.add(dateLabel,0,10);
        addPlayerPane.add(addPlayerButton,0,11);
        //------ Text fields
        addPlayerPane.add(pAndGTextField,1,0);
        addPlayerPane.add(playerIdTextField,1,1);
        addPlayerPane.add(firstNameTextField,1,2);
        addPlayerPane.add(lastNameTextField,1,3);
        addPlayerPane.add(addressTextField,1,4);
        addPlayerPane.add(postalCodeTextField,1,5);
        addPlayerPane.add(provinceTextField,1,6);
        addPlayerPane.add(phoneTextField,1,7);
        addPlayerPane.add(gamePlayedComboBox,1,8);

        addPlayerPane.add(scoreTextField,1,9);
        addPlayerPane.add(playingDatePicker,1,10);
        addPlayerPane.add(resetPlayerButton,1,11);

        addPlayerPane.setAlignment(Pos.CENTER);
        // Add pane to Tab
        addPlayer.setContent(addPlayerPane);


        // --------------------ADD A RECORD
        Tab addRecord = new Tab();
        addRecord.setText("Add/Update a record");
        GridPane addRecordPane = new GridPane();
        addRecordPane.setAlignment(Pos.CENTER);
        addRecordPane.setVgap(10);
        Label justALabel = new Label("Add new score and playing date to a game");
        ComboBox recordGameCB = new ComboBox(FXCollections.observableList(data.getGameTitle()));
        ComboBox recordPlayerCB = new ComboBox((FXCollections.observableList(data.getPlayerName())));
        Label scoreLB = new Label("Score: ");
        Label dateLB = new Label("Playing date: ");
        TextField scoreTF = new TextField();
        TextField dateTF = new TextField();

        addRecordPane.add(justALabel,1,0);
        addRecordPane.add(recordGameCB,1,1);
        addRecordPane.add(recordPlayerCB,1,2);
        addRecordPane.add(scoreLB,0,3);
        addRecordPane.add(scoreTF,1,3);
        addRecordPane.add(dateLB,0,4);
        addRecordPane.add(dateTF,1,4);

        addRecord.setContent(addRecordPane);

        //----------------------- VIEW PLAYER LADDER
        Tab playerLadder = new Tab();
        playerLadder.setText("Player ladder");
        GridPane playerLadderGridPane = new GridPane();
        playerLadderGridPane.setAlignment(Pos.CENTER);
        Label selectGameOnLadder = new Label("Select a game to view ladder");
        ListView<String> laddersListView = new ListView<String>();
        ComboBox gameList = new ComboBox(games);

        Button viewLadderButton = new Button("View");


        viewLadderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    String game_id = Integer.toString(gameList.getSelectionModel().getSelectedIndex() +1);
                    laddersListView.setItems(data.getGameLadder(game_id));
                    System.out.println("Clicked");
                } catch (SQLException e) {

                }
            }
        });


        playerLadderGridPane.add(selectGameOnLadder,0,0);
        playerLadderGridPane.add(gameList,0,1);
        playerLadderGridPane.add(viewLadderButton,1,1);
        playerLadderGridPane.add(laddersListView,0,2);
        playerLadder.setContent(playerLadderGridPane);








        //----------------------- DATABASE LOG
        Tab viewInfo = new Tab();
        viewInfo.setText("Database log");
        GridPane viewPane = new GridPane();
        // LOG - List of game
        System.out.println("DATABASE>> LIST OF GAME >> GAME Table");
        List<Game> gameTEMP = data.getGameList();
        System.out.println();
        System.out.println("DATABASE>> LIST OF PLAYERS >> PLAYER Table");
        List<Player> playerTEMP = data.getPlayerList();

        ObservableList gameForListView = FXCollections.observableList(data.getGameTitle());
        ObservableList playerForListView = FXCollections.observableList(data.getPlayerName());

        ListView<String> gameListView = new ListView<>(gameForListView);
        ListView<String> playerListView = new ListView<>(playerForListView);
        Label gameListViewLabel = new Label("List of games");
        Label playerListViewLabel = new Label("List of players");

        Button updateDatabase = new Button("Update log");
        updateDatabase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    gameListView.setItems(FXCollections.observableArrayList(data.getGameTitle()));
                    playerListView.setItems(FXCollections.observableArrayList(data.getPlayerName()));

                } catch (SQLException e) {

                }
            }
        });

        viewPane.add(gameListViewLabel,0,0);
        viewPane.add(playerListViewLabel,1,0);
        viewPane.add(gameListView,0,1);
        viewPane.add(playerListView,1,1);
        viewPane.add(updateDatabase, 0,3);

        viewPane.setAlignment(Pos.CENTER);
        viewInfo.setContent(viewPane);

        tabPane.getTabs().addAll(addGame,addPlayer,addRecord,playerLadder,viewInfo);



        // bind to take available space
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());

        borderPane.setCenter(tabPane);
        root.getChildren().add(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @Override
    public void stop() throws Exception {
        if (data != null) {
            data.shutdown();
        }
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
