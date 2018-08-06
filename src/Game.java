import javafx.beans.property.StringProperty ;
import javafx.beans.property.SimpleStringProperty ;

public class Game {

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
