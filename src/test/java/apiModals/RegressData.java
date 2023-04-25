package apiModals;

public class RegressData {

    // var names must match json key spelling
    private int id;

    private String email;

    // must be snake case to get/set
    private String first_name;

    // must be snake case to get/set
    private String last_name;

    private String avatar;

    // Getter Methods

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getAvatar() {
        return avatar;
    }


    // Setter methods

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirst_name(String first_name) {
        this.first_name = this.first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
