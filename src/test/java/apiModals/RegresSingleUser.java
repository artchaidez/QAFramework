package apiModals;

import com.google.gson.annotations.SerializedName;

/** Modal for API GET call for a single user.
 * https://reqres.in/ */
public class RegresSingleUser
{
    // var names must match json key spelling
    private int page;
    @SerializedName("per_page")
    private int perPage;
    private int total;
    @SerializedName("total_pages")
    private int totalPages;
    RegressData data;
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

    public int getID() {
        return data.getId();
    }
    public String getEmail() {
        return data.getEmail();
    }

    public String getFirstName() {
        return data.getFirstName();
    }

    public String getLastName() {
        return data.getLastName();
    }

    public String getAvatar() {
        return data.getAvatar();
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
