import javafx.beans.property.StringProperty ;
import javafx.beans.property.SimpleStringProperty ;


public class Player {

    private final StringProperty player_id;
    private final StringProperty first_name;
    private final StringProperty last_name;
    private final StringProperty address;
    private final StringProperty postal_code;
    private final StringProperty province;
    private final StringProperty phone_number;

    // Init
    public Player(String player_id,
                  String first_name,
                  String last_name,
                  String address,
                  String postal_code,
                  String province,
                  String phone_number) {
        this.player_id = new SimpleStringProperty(player_id);
        this.last_name = new SimpleStringProperty(last_name);
        this.first_name = new SimpleStringProperty(first_name);
        this.address = new SimpleStringProperty(address);
        this.postal_code = new SimpleStringProperty(postal_code);
        this.province = new SimpleStringProperty(province);
        this.phone_number = new SimpleStringProperty(phone_number);
    }

    // Getters

    public String getPlayerId() {
        return player_id.get();
    }

    public String getFirst_name() {
        return first_name.get();
    }

    public String getLastName() {
        return last_name.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getPostalCode() {
        return postal_code.get();
    }

    public String getProvince() {
        return province.get();
    }

    public String getPhoneNumber() {
        return phone_number.get();
    }


    // Setters
    public void setPlayerId(String playerId) {
        player_id.set(playerId);
    }
    public void setFirstName(String firstname) {
        first_name.set(firstname);
    }
    public void setLastName(String lastname) {
        last_name.set(lastname);
    }
    public void setAddress(String address) {
        this.address.set(address);
    }
    public void setPostalCode(String pc) {
        postal_code.set(pc);
    }
    public void setProvince(String province) {
        this.province.set(province);
    }
    public void setPhonenumber(String phone) {
        phone_number.set(phone);
    }
}
