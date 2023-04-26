package apiModals;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegresListUsers {

    // var names must match json key spelling
    private int page;
    @SerializedName("per_page")
    private int perPage;
    private int total;
    @SerializedName("total_pages")
    private int totalPages;
    List< RegressData > data;
    RegresSupport support;

    // Getter Methods

    public int getPage() {
        return page;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    /** Get user's ID from list of users */
    public int getDataID(int id) {
        for (RegressData key: data) {
            if (id == key.getId()) {
                return key.getId();
            }
        }
        return -1;
    }

    /** Get user's email from list of users */
    public String getDataEmail(String email) {
        for (RegressData key: data) {
            if (email.equals(key.getEmail())) {
                return key.getEmail();
            }
        }
        return "NOT FOUND";
    }

    /** Get user's first name from list of users */
    public String getDataFirstName(String firstName) {
        for (RegressData key: data) {
            if (firstName.equals(key.getFirstName())) {
                return key.getFirstName();
            }
        }
        return "NOT FOUND";
    }

    /** Get user's last name from list of users */
    public String getDataLastName(String lastName) {
        for (RegressData key: data) {
            if (lastName.equals(key.getLastName())) {
                return key.getLastName();
            }
        }
        return "NOT FOUND";
    }

    /** Get user's avatar from list of users */
    public String getDataAvatar(String avatar) {
        for (RegressData key: data) {
            if (avatar.equals(key.getAvatar())) {
                return key.getAvatar();
            }
        }
        return "NOT FOUND";
    }

    public RegresSupport getSupport() {
        return support;
    }

    // Setter Methods

    public void setPage(int page) {
        this.page = page;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setSupport(RegresSupport regresSupportObject) {
        this.support = regresSupportObject;
    }
}



