package apiModals;

public class BankInfo {
    private String bankName;
    private String abaRoutingNumber;
    private String accountNumber;
    private float accountType;
    private float accountLength;

    // Getter Methods

    public String getBankName() {
        return bankName;
    }

    public String getAbaRoutingNumber() {
        return abaRoutingNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public float getAccountType() {
        return accountType;
    }

    public float getAccountLength() {
        return accountLength;
    }

    // Setter Methods

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setAbaRoutingNumber(String abaRoutingNumber) {
        this.abaRoutingNumber = abaRoutingNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountType(float accountType) {
        this.accountType = accountType;
    }

    public void setAccountLength(float accountLength) {
        this.accountLength = accountLength;
    }
}
