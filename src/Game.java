import javafx.beans.property.StringProperty ;
import javafx.beans.property.SimpleStringProperty ;

public class Game {

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
        gameIdProperty().set(GameTitle);
    }

    public Game(String gameId, String gameTitle) {
        setGameId(gameId);
        setGameTitle(gameTitle);
    }


}
