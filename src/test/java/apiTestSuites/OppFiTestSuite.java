package apiTestSuites;

import apiModals.*;
import autoFramework.AutoTestBase;
import autoFramework.TestInfo;
import listeners.BaseListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(BaseListener.class)
public class OppFiTestSuite extends AutoTestBase {

    private String resource = null;
    private String apiKey = null;

    @BeforeSuite
    public void TestSetUp()
    {
        resource = System.getenv("oppFi_target_url");
        apiKey = System.getenv("oppFi_api_key");
    }

    @AfterMethod
    public void TestTearDown()
    {
    }

    @Test()
    @TestInfo(description = "OffersApiAcceptedTest is testing to return an approved loan.")
    public void OffersApiAcceptedTest() throws Exception {

        Step("Set up API classes for the request body");
            Offer offer = new Offer();
            Address address = new Address();
            PersonalInfo personalInfo = new PersonalInfo();
            BankInfo bankInfo = new BankInfo();
            IncomeInfo incomeInfo = new IncomeInfo();
            EmploymentInfo employmentInfo = new EmploymentInfo();

        Step("Set up Offer API Request Body.");
            offer.setIsProduction(false);
            offer.setLanguage("en");
            offer.setCurrency("USD");
            offer.setCampaignId("11-50-newhope");
            offer.setSocialSecurityNumber("123456780");
            offer.setLeadOfferId("20160912-21EC2020-3AEA-4069-A2DD-08002B30309D");
            offer.setEmail("test_customer@gmail.com");
            offer.setStateCode("FL");
            offer.setGrossMonthlyIncome(100000);

            personalInfo.setFirstName("Jennifer");
            personalInfo.setLastName("Smith");
            personalInfo.setDateOfBirth("19451009");
            address.setStreetAddress("123 Main Street");
            address.setCity("Miami");
            address.setZip("33125");
            address.setCountryCode("US");
            personalInfo.setAddress(address);
            personalInfo.setMobilePhone("3224340098");
            personalInfo.setHomePhone("4523452232");
            offer.setPersonalInfo(personalInfo);

            bankInfo.setBankName("Chase");
            bankInfo.setAbaRoutingNumber("123456789");
            bankInfo.setAccountNumber("012345789");
            bankInfo.setAccountType(1);
            bankInfo.setAccountLength(6);
            offer.setBankInfo(bankInfo);

            incomeInfo.setIncomeType("Employment");
            incomeInfo.setPayrollType("DirectDeposit");
            incomeInfo.setPayrollFrequency(1);
            incomeInfo.setLastPayrollDate("20160915");
            offer.setIncomeInfo(incomeInfo);

            employmentInfo.setEmployerName("ToysRUs");
            employmentInfo.setHireDate("20110516");
            offer.setEmploymentInfo(employmentInfo);
            offer.setRequestedLoanAmount(1500);

            Info("Offer for " + offer.getPersonalInfo().getFullName() + " created");

        Step("Make a post call.");
            Offer response;

            try {
                response =  APIs.offersApi.PostOffers(offer, resource, apiKey);
            }
            catch (Exception e)
            {
                throw new Exception("Exception hit", e);
            }

        Step("Verify loan was accepted.");
            Verify.That(response.getAccepted()).IsTrue();

    }

    @Test()
    @TestInfo(description = "OffersApiGetsDeclinedTest is testing to return a declined loan.")
    public void OffersApiGetsDeclinedTest() throws Exception
    {
        Step("Set up API classes for the request body");
            Offer offer = new Offer();
            Address address = new Address();
            PersonalInfo personalInfo = new PersonalInfo();
            BankInfo bankInfo = new BankInfo();
            IncomeInfo incomeInfo = new IncomeInfo();
            EmploymentInfo employmentInfo = new EmploymentInfo();

        Step("Set up Offer API Request Body.");
            offer.setIsProduction(false);
            offer.setLanguage("en");
            offer.setCurrency("USD");
            offer.setCampaignId("11-50-newhope");
            // SSN should return declined
            offer.setSocialSecurityNumber("123450000");
            offer.setLeadOfferId("20160912-21EC2020-3AEA-4069-A2DD-08002B30309D");
            offer.setEmail("test_customer@gmail.com");
            offer.setStateCode("FL");
            offer.setGrossMonthlyIncome(100000);

            personalInfo.setFirstName("Jennifer");
            personalInfo.setLastName("Smith");
            personalInfo.setDateOfBirth("19451009");
            address.setStreetAddress("123 Main Street");
            address.setCity("Miami");
            address.setZip("33125");
            address.setCountryCode("US");

            personalInfo.setAddress(address);
            personalInfo.setMobilePhone("3224340098");
            personalInfo.setHomePhone("4523452232");
            offer.setPersonalInfo(personalInfo);

            bankInfo.setBankName("Chase");
            bankInfo.setAbaRoutingNumber("123456789");
            bankInfo.setAccountNumber("012345789");
            bankInfo.setAccountType(1);
            bankInfo.setAccountLength(6);
            offer.setBankInfo(bankInfo);

            incomeInfo.setIncomeType("Employment");
            incomeInfo.setPayrollType("DirectDeposit");
            incomeInfo.setPayrollFrequency(1);
            incomeInfo.setLastPayrollDate("20160915");
            offer.setIncomeInfo(incomeInfo);

            employmentInfo.setEmployerName("ToysRUs");
            employmentInfo.setHireDate("20110516");
            offer.setEmploymentInfo(employmentInfo);
            offer.setRequestedLoanAmount(1500);

            Info("Offer for " + offer.getPersonalInfo().getFullName() + " created");

        Step("Make a post call.");
            Offer response;

            try {
                response =  APIs.offersApi.PostOffers(offer, resource, apiKey);
            }
            catch (Exception e)
            {
                throw new Exception("Exception hit", e);
            }

        Step("Verify loan was not accepted.");
            Verify.That(response.getAccepted()).IsFalse();
    }

    @Test()
    @TestInfo(description = "OffersApiCallGetsBadDataTest is testing to return an a bad API call.")
    public void OffersApiCallGetsBadDataTest() throws Exception
    {
        Step("Set up bad API Request Body.");
            String badSampleRequest = """
                    {
                    "isProduction": false,
                    "language": "en",
                    "currency": "USD",
                    "campaignId": "11-50-newhope", "socialSecurityNumber":"123456780",
                    "leadOfferId": "20160912-21EC2020-3AEA-4069-A2DD-08002B30309D", "email": "test_customer@gmail.com",
                    "stateCode": "FL",
                    \s
                    "grossMonthlyIncome": "hello", "personalInfo": {
                    "firstName": "Jennifer", "lastName": "Smith", "dateOfBirth": "19451009", "address": {
                    "streetAddress": "123 Main Street", "city": "Miami",
                    "zip": "33125",
                    "countryCode":"US"
                    },
                    "mobilePhone": "3224340098", "homePhone": "4523452232"
                    }, "bankInfo": {
                    "bankName": "Chase", "abaRoutingNumber": "123456789", "accountNumber": "012345789", "accountType": 1, "accountLength": 6
                    }, "incomeInfo": {
                    "incomeType": "Employment", "payrollType": "DirectDeposit", "payrollFrequency": 1, "lastPayrollDate": "20160915"
                    }, "employmentInfo": {
                    "employerName": "ToysRUs",
                    "hireDate": "20110516" },
                    "requestedLoanAmount": 1500}""";

        Step("Make a post call.");
            int response;

            try {
                response =  APIs.offersApi.PostOffersInt(badSampleRequest, resource, apiKey);
            }
            catch (Exception e)
            {
                throw new Exception("Exception hit", e);
            }

        Step("Verify bad data was returned: status code 500.");
            Verify.That(response).Equals(500);

    }


}
