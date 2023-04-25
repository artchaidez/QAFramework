package apiModals;

public class Regres {

    // var names must match json key spelling
    private int page;
    private int per_page;
    private int total;
    private int total_pages;

    // TODO: properly handle single/ list of users in GET call
    //ArrayList < RegressData > data = new ArrayList<>();
    RegressData data;
    RegresSupport support;

    // Getter Methods

    public int getPage() {
        return page;
    }

    public int getPerPage() {
        return per_page;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalPages() {
        return total_pages;
    }

    /** Get user's id*/
    public int getID()
    {
        return data.getId();
    }

    public String getEmail()
    {
        return data.getEmail();
    }

    /** Get user's first name*/
    public String getFirstName()
    {
        return data.getFirstName();
    }

    /** Get user's id*/
    public String getLastName()
    {
        return data.getLastName();
    }

    /** Get user's avatar*/
    public String getAvatar()
    {
        return data.getAvatar();
    }
/**
    // TODO: handle not found
    /** Get user's ID from list of users
    public int getDataID(int id) {
        for (RegressData key: data) {
            if (id == key.getId()) {
                return key.getId();
            }
        }
        return -1;
    }

    // TODO: handle not found
    /** Get user's email from list of users
    public String getDataEmail(String email) {
        for (RegressData key: data) {
            if (email.equals(key.getEmail())) {
                return key.getEmail();
            }
        }
        return "";
    }

    // TODO: handle not found
    /** Get user's first name from list of users
    public String getDataFirstName(String firstName) {
        for (RegressData key: data) {
            if (firstName.equals(key.getFirstName())) {
                return key.getFirstName();
            }
        }
        return "";
    }

    // TODO: handle not found
    /** Get user's last name from list of users
    public String getDataLastName(String lastName) {
        for (RegressData key: data) {
            if (lastName.equals(key.getLastName())) {
                return key.getLastName();
            }
        }
        return "";
    }

    // TODO: handle not found
    /** Get user's avatar from list of users
    public String getDataAvatar(String avatar) {
        for (RegressData key: data) {
            if (avatar.equals(key.getAvatar())) {
                return key.getAvatar();
            }
        }
        return "";
    }
*/
    public RegresSupport getSupport() {
        return support;
    }

    // Setter Methods

    public void setPage(int page) {
        this.page = page;
    }

    public void setPerPage(int per_page) {
        this.per_page = per_page;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotalPages(int total_pages) {
        this.total_pages = total_pages;
    }

    public void setSupport(RegresSupport regresSupportObject) {
        this.support = regresSupportObject;
    }
}



