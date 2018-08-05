import javafx.beans.property.StringProperty ;
import javafx.beans.property.SimpleStringProperty ;

public class Game {
/*
    private final StringProperty game_id = new SimpleStringProperty(this, "game_id");

    public StringProperty gameIdProperty() {
        return game_id ;
    }
    public final String getGameId() {
        return gameIdProperty().get();
    }
    public final void setGameId(String GameId) {
        gameIdProperty().set(GameId);
    }

    private final StringProperty game_title = new SimpleStringProperty(this, "game_title");
    public StringProperty gameTitleProperty() {
        return game_title ;
    }
    public final String getGameTitle() {
        return gameTitleProperty().get();
    }
    public final void setGameTitle(String GameTitle) {
        gameTitleProperty().set(GameTitle);
    }

    public Game(String gameId, String gameTitle) {
        setGameId(gameId);
        setGameTitle(gameTitle);
    }

*/
    private final SimpleStringProperty game_id;
    private final SimpleStringProperty game_title;

    public Game(String game_id, String game_title) {
        this.game_id = new SimpleStringProperty(game_id);
        this.game_title = new SimpleStringProperty(game_title);

    }

    // Getters
    public String getGameId() {
        return game_id.get();
    }
    public String getGameTitle(){
        return game_title.get();
    }
    // Setters
    public void setGameId(String gameId) {
        game_id.set(gameId);
    }
    public void setGameTitle(String gameTitle) {
        game_title.set(gameTitle);
    }
}
