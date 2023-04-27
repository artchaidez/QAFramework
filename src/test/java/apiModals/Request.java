package apiModals;

public class Request {
    private String email;
    IncomeInfo IncomeInfoObject;
    private String campaignId;
    private float requestedLoanAmount;
    private float grossMonthlyIncome;
    private String leadOfferId;
    private String socialSecurityNumber;
    private boolean isProduction;
    PersonalInfo PersonalInfoObject;
    private String currency;
    private String language;
    private String stateCode;
    EmploymentInfo EmploymentInfoObject;
    BankInfo BankInfoObject;

    // Getter Methods

    public String getEmail() {
        return email;
    }

    public IncomeInfo getIncomeInfo() {
        return IncomeInfoObject;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public float getRequestedLoanAmount() {
        return requestedLoanAmount;
    }

    public float getGrossMonthlyIncome() {
        return grossMonthlyIncome;
    }

    public String getLeadOfferId() {
        return leadOfferId;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public boolean getIsProduction() {
        return isProduction;
    }

    public PersonalInfo getPersonalInfo() {
        return PersonalInfoObject;
    }

    public String getCurrency() {
        return currency;
    }

    public String getLanguage() {
        return language;
    }

    public String getStateCode() {
        return stateCode;
    }

    public EmploymentInfo getEmploymentInfo() {
        return EmploymentInfoObject;
    }

    public BankInfo getBankInfo() {
        return BankInfoObject;
    }

    // Setter Methods

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIncomeInfo(IncomeInfo incomeInfoObject) {
        this.IncomeInfoObject = incomeInfoObject;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public void setRequestedLoanAmount(float requestedLoanAmount) {
        this.requestedLoanAmount = requestedLoanAmount;
    }

    public void setGrossMonthlyIncome(float grossMonthlyIncome) {
        this.grossMonthlyIncome = grossMonthlyIncome;
    }

    public void setLeadOfferId(String leadOfferId) {
        this.leadOfferId = leadOfferId;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public void setIsProduction(boolean isProduction) {
        this.isProduction = isProduction;
    }

    public void setPersonalInfo(PersonalInfo personalInfoObject) {
        this.PersonalInfoObject = personalInfoObject;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public void setEmploymentInfo(EmploymentInfo employmentInfoObject) {
        this.EmploymentInfoObject = employmentInfoObject;
    }

    public void setBankInfo(BankInfo bankInfoObject) {
        this.BankInfoObject = bankInfoObject;
    }
}
