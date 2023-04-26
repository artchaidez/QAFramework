package apiModals;

import com.google.gson.annotations.SerializedName;

public class Offer {
    private boolean isProduction;
    private String language;
    private String currency;
    private String campaignId;
    private String socialSecurityNumber;
    private String leadOfferId;
    private String email;
    private String stateCode;
    private float grossMonthlyIncome;
    PersonalInfo personalInfo;
    BankInfo bankInfo;
    IncomeInfo incomeInfo;
    EmploymentInfo employmentInfo;
    private float requestedLoanAmount;

    Request Request;
    private boolean accepted;
    private float partnerId;
    @SerializedName("reference_id")
    private String referenceId;
    private String status;
    private String apiVersion;

    // Getter Methods

    public boolean getIsProduction() {
        return isProduction;
    }

    public String getLanguage() {
        return language;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getLeadOfferId() {
        return leadOfferId;
    }

    public String getEmail() {
        return email;
    }

    public String getStateCode() {
        return stateCode;
    }

    public float getGrossMonthlyIncome() {
        return grossMonthlyIncome;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public BankInfo getBankInfo() {
        return bankInfo;
    }

    public IncomeInfo getIncomeInfo() {
        return incomeInfo;
    }

    public EmploymentInfo getEmploymentInfo() {
        return employmentInfo;
    }

    public float getRequestedLoanAmount() {
        return requestedLoanAmount;
    }

    // Setter Methods

    public void setIsProduction(boolean isProduction) {
        this.isProduction = isProduction;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public void setLeadOfferId(String leadOfferId) {
        this.leadOfferId = leadOfferId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public void setGrossMonthlyIncome(float grossMonthlyIncome) {
        this.grossMonthlyIncome = grossMonthlyIncome;
    }

    public void setPersonalInfo(PersonalInfo personalInfoObject) {
        this.personalInfo = personalInfoObject;
    }

    public void setBankInfo(BankInfo bankInfoObject) {
        this.bankInfo = bankInfoObject;
    }

    public void setIncomeInfo(IncomeInfo incomeInfoObject) {
        this.incomeInfo = incomeInfoObject;
    }

    public void setEmploymentInfo(EmploymentInfo employmentInfoObject) {
        this.employmentInfo = employmentInfoObject;
    }

    public void setRequestedLoanAmount(float requestedLoanAmount) {
        this.requestedLoanAmount = requestedLoanAmount;
    }

    // Getter Methods

    public Request getRequest() {
        return Request;
    }

    public boolean getAccepted() {
        return accepted;
    }

    public float getPartnerId() {
        return partnerId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public String getStatus() {
        return status;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    // Setter Methods

    public void setRequest(Request requestObject) {
        this.Request = requestObject;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setPartnerId(float partnerId) {
        this.partnerId = partnerId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }
}
