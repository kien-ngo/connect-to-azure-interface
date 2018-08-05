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

import java.sql.*;
import java.util.List;

public class Lab5Test extends Application {
    private DataAccessor data;
    private TableView gameTableView = new TableView();
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Get data
        String connectionString = "jdbc:sqlserver://kienngolab5.database.windows.net:1433;" +
                "database=KienNgo_Lab5COMP228;" +
                "user=kienngo@kienngolab5;" +
                "password=#Absinthe23;" +
                "encrypt=true;" +
                "trustServerCertificate=false;" +
                "hostNameInCertificate=*.database.windows.net;" +
                "loginTimeout=30;";

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



        //-----------------------




        Tab addPlayer = new Tab();
        addPlayer.setText("Add a player");
        GridPane addPlayerPane = new GridPane();
        addPlayerPane.setPadding(new Insets(10,10,10,10));
        addPlayerPane.setVgap(5);
        addPlayerPane.setHgap(5);
        // Add content to pane
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
        ObservableList games = FXCollections.observableArrayList(data.getGameTitle());
        ComboBox gamePlayedComboBox = new ComboBox(games);
        gamePlayedComboBox.setPromptText("Select one from database");
        Label scoreLabel = new Label("Score: ");
        TextField scoreTextField = new TextField();
        Label dateLabel = new Label("Playing date: ");
        DatePicker playingDatePicker = new DatePicker();
        Button addPlayerButton = new Button("Add player");
        Button resetPlayerButton = new Button("Reset");


        //



        addPlayerPane.add(playerIdLabel,0,0);
        addPlayerPane.add(firstNameLabel,0,1);
        addPlayerPane.add(lastNameLabel,0,2);
        addPlayerPane.add(addressLabel,0,3);
        addPlayerPane.add(postalCodeLabel,0,4);
        addPlayerPane.add(provinceLabel,0,5);
        addPlayerPane.add(phoneLabel,0,6);
        addPlayerPane.add(gamePlayedLabel,0,7);

        addPlayerPane.add(scoreLabel,0,8);
        addPlayerPane.add(dateLabel,0,9);
        addPlayerPane.add(addPlayerButton,0,10);
        //------ Text fields
        addPlayerPane.add(playerIdTextField,1,0);
        addPlayerPane.add(firstNameTextField,1,1);
        addPlayerPane.add(lastNameTextField,1,2);
        addPlayerPane.add(addressTextField,1,3);
        addPlayerPane.add(postalCodeTextField,1,4);
        addPlayerPane.add(provinceTextField,1,5);
        addPlayerPane.add(phoneTextField,1,6);
        addPlayerPane.add(gamePlayedComboBox,1,7);

        addPlayerPane.add(scoreTextField,1,8);
        addPlayerPane.add(playingDatePicker,1,9);
        addPlayerPane.add(resetPlayerButton,1,10);

        addPlayerPane.setAlignment(Pos.CENTER);
        // Add pane to Tab
        addPlayer.setContent(addPlayerPane);




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

        ObservableList gameForListView = FXCollections.observableArrayList(data.getGameTitle());
        ObservableList playerForListView = FXCollections.observableArrayList(data.getPlayerName());

        ListView<String> gameListView = new ListView<>(gameForListView);
        ListView<String> playerListView = new ListView<>(playerForListView);
        Label gameListViewLabel = new Label("List of games");
        Label playerListViewLabel = new Label("List of players");

        viewPane.add(gameListViewLabel,0,0);
        viewPane.add(playerListViewLabel,1,0);
        viewPane.add(gameListView,0,1);
        viewPane.add(playerListView,1,1);

        viewPane.setAlignment(Pos.CENTER);
        viewInfo.setContent(viewPane);

        tabPane.getTabs().addAll(addGame,addPlayer,viewInfo);



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
