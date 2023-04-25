package unitTests;

import apiModals.RegresListUsers;
import apiModals.RegresSingleUser;
import apiModals.RegresUser;
import autoFramework.AutoTestBase;
import autoFramework.TestInfo;
import listeners.BaseInvokedMethodListener;
import listeners.BaseTestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({BaseTestListener.class, BaseInvokedMethodListener.class})
public class APITestSuite extends AutoTestBase{

    String id;

    @BeforeMethod
    public void TestSetUp()
    {
    }

    @AfterMethod
    public void TestTearDown()
    {
    }

    @Test
    @TestInfo(description = "Verify GET call returns a single user")
    public void TestGetSingleUser() throws Exception {

        String resource = "https://reqres.in/api/users/9";
        int id = 9;
        String email = "tobias.funke@reqres.in";
        String firstName ="Tobias";
        String lastName = "Funke";
        String avatar = "https://reqres.in/img/faces/9-image.jpg";

        Step("Make a GET call for a single user");
            RegresSingleUser response;

            try {
                response = APIs.regresApi.GetSingleRegres(resource);
            }catch (Exception e)
            {
                throw new Exception("Exception hit", e);
            }

        Step(String.format("Verify id is %s", id));
            Verify.That(response.getID()).Equals(id);

        Step(String.format("Verify email is %s", email));
            Verify.That(response.getEmail()).Equals(email);

        Step(String.format("Verify first name is %s", firstName));
            Verify.That(response.getFirstName()).Equals(firstName);

        Step(String.format("Verify last name is %s", lastName));
            Verify.That(response.getLastName()).Equals(lastName);

        Step(String.format("Verify avatar is %s", avatar));
            Verify.That(response.getAvatar()).Equals(avatar);

    }

    @Test
    @TestInfo(description = "Verify GET call returns users on page 2")
    public void TestGetListUsers() throws Exception {

        String resource = "https://reqres.in/api/users?page=2";
        int pageNumber = 2;
        int perPage = 6;
        int total = 12;
        int totalPages = 2;
        int dataID= 9;
        String dataEmail = "tobias.funke@reqres.in";
        String dataFirstName = "Tobias";
        String dataLastName = "Funke";
        String dataAvatar = "https://reqres.in/img/faces/9-image.jpg";
        String supportURL = "https://reqres.in/#support-heading";
        String supportText = "To keep ReqRes free, contributions towards server costs are appreciated!";

        Step("Make a GET call for a list of users");
            RegresListUsers response;

            try {
                response = APIs.regresApi.GetListRegres(resource);
            }catch (Exception e)
            {
                throw new Exception("Exception hit", e);
            }

        Step(String.format("Verify page number is %s", pageNumber));
            Verify.That(response.getPage()).Equals(pageNumber);

        Step(String.format("Verify per page is %s", perPage));
            Verify.That(response.getPerPage()).Equals(perPage);

        Step(String.format("Verify total is %s", total));
            Verify.That(response.getTotal()).Equals(total);

        Step(String.format("Verify total pages is %s", totalPages));
            Verify.That(response.getTotalPages()).Equals(totalPages);

        Step(String.format("Verify id %s exists", dataID));
            Verify.That(response.getDataID(dataID)).Equals(dataID);

        Step(String.format("Verify email %s exist", dataEmail));
            Verify.That(response.getDataEmail(dataEmail)).Equals(dataEmail);

        Step(String.format("Verify first name %s exists", dataFirstName));
            Verify.That(response.getDataFirstName(dataFirstName)).Equals(dataFirstName);

        Step(String.format("Verify last name %s exists", dataLastName));
            Verify.That(response.getDataLastName(dataLastName)).Equals(dataLastName);

        Step(String.format("Verify avatar %s exists", dataAvatar));
            Verify.That(response.getDataAvatar(dataAvatar)).Equals(dataAvatar);

        Step(String.format("Verify support url is %s", supportURL));
            Verify.That(response.getSupport().getUrl()).Equals(supportURL);

        Step("Verify that support text matches");
            Verify.That(response.getSupport().getText()).Equals(supportText);
    }

    @Test(priority = 1)
    @TestInfo(description = "Verify POST works.")
    public void TestPostRequest() throws Exception {

        String resource = "https://reqres.in/api/users";
        String name = "morpheus";
        String job = "leader";

        Step("Set up ReqresUser and the body");
            RegresUser request = new RegresUser();
            request.setName(name);
            request.setJob(job);

        Step("Make a GET call");
            RegresUser response;

            try {
                response = APIs.regresApi.PostRegres(request, resource);
            }catch (Exception e)
            {
                throw new Exception("Exception hit", e);
            }

        Step(String.format("Verify name is %s", name));
            Verify.That(response.getName()).Equals(name);

        Step(String.format("Verify job is %s", job));
            Verify.That(response.getJob()).Equals(job);

        Step("Save new user id");
            id = response.getId();
            Info(String.format("Id is %s", id));
    }

    @Test(priority = 2, dependsOnMethods = {"TestPostRequest"})
    @TestInfo(description = "Verify PUT works")
    public void TestUpdateRequest() throws Exception {

        // Use id that was created in TestPostRequest()
        String resource = "https://reqres.in/api/users/" + id;
        String name = "morpheus";
        String job = "follower";

        Step("Set up ReqresUser and the body");
            RegresUser request = new RegresUser();
            request.setName(name);
            request.setJob(job);

        Step("Make a PUT call to update user");
            RegresUser response;

            try {
                response = APIs.regresApi.PutRegres(request, resource);
            }catch (Exception e)
            {
                throw new Exception("Exception hit", e);
            }

        Info(String.format("Id is %s", id));

        Step(String.format("Verify job is %s", job));
            Verify.That(response.getJob()).Equals(job);

        Step(String.format("Verify name is %s", name));
            Verify.That(response.getName()).Equals(name);
    }

    @Test(priority = 3, dependsOnMethods = {"TestPostRequest"})
    @TestInfo(description = "Verify Delete works")
    public void TestDeleteRequest()
    {
        String resource = "https://reqres.in/api/users/" + id;

        Step(String.format("Delete %s", resource));

            try {
                APIs.regresApi.DeleteRegres(resource);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

}
