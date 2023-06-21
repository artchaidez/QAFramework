package unitTests;

import apiModals.RegresListUsers;
import apiModals.RegresSingleUser;
import apiModals.RegresUser;
import autoFramework.AutoTestBase;
import autoFramework.TestInfo;
import listeners.BaseListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.MessageFormat;

@Listeners(BaseListener.class)
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
    @TestInfo(description = "Verify GET call returns a single user.")
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

        Step("Verify id is {0}", String.valueOf(id));
            Verify.That(response.getID()).Equals(id);

        Step("Verify email is {0}", email);
            Verify.That(response.getEmail()).Equals(email);

        Step("Verify first name is {0}", firstName);
            Verify.That(response.getFirstName()).Equals(firstName);

        Step("Verify last name is {0}", lastName);
            Verify.That(response.getLastName()).Equals(lastName);

        Step("Verify avatar is {0}", avatar);
            Verify.That(response.getAvatar()).Equals(avatar);
    }

    @Test
    @TestInfo(description = "Verify GET call returns users on page 2.")
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

        Step("Verify page number is {0}", String.valueOf(pageNumber));
            Verify.That(response.getPage()).Equals(pageNumber);

        Step("Verify per page is {0}", String.valueOf(perPage));
            Verify.That(response.getPerPage()).Equals(perPage);

        Step("Verify total is {0}", String.valueOf(total));
            Verify.That(response.getTotal()).Equals(total);

        Step("Verify total pages is {0}", String.valueOf(totalPages));
            Verify.That(response.getTotalPages()).Equals(totalPages);

        Step("Verify id is {0}", String.valueOf(dataID));
            Verify.That(response.getDataID(dataID)).Equals(dataID);

        Step("Verify email is {0}", dataEmail);
            Verify.That(response.getDataEmail(dataEmail)).Equals(dataEmail);

        Step("Verify first name is {0}", dataFirstName);
            Verify.That(response.getDataFirstName(dataFirstName)).Equals(dataFirstName);

        Step("Verify last name is {0}", dataLastName);
            Verify.That(response.getDataLastName(dataLastName)).Equals(dataLastName);

        Step("Verify avatar is {0}", dataAvatar);
            Verify.That(response.getDataAvatar(dataAvatar)).Equals(dataAvatar);

        Step("Verify support url is {0}", supportURL);
            Verify.That(response.getSupport().getUrl()).Equals(supportURL);

        Step("Verify that the support text matches");
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

        Step("Make a POST call");
            RegresUser response;

            try {
                response = APIs.regresApi.PostRegres(request, resource);
            }catch (Exception e)
            {
                throw new Exception("Exception hit", e);
            }

        Step("Verify name is {0}", name);
            Verify.That(response.getName()).Equals(name);

        Step("Verify job is {0}", job);
            Verify.That(response.getJob()).Equals(job);

        Step("Save new user id");
            id = response.getId();
            Info(MessageFormat.format("Id is {0}", id));
    }

    @Test(priority = 2, dependsOnMethods = {"TestPostRequest"})
    @TestInfo(description = "Verify PUT works.")
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

        Info(MessageFormat.format("Id is {0}", id));

        Step("Verify job is {0}", job);
            Verify.That(response.getJob()).Equals(job);

        Step("Verify name is {0}", name);
            Verify.That(response.getName()).Equals(name);
    }

    @Test(priority = 3, dependsOnMethods = {"TestPostRequest"})
    @TestInfo(description = "Verify Delete works.")
    public void TestDeleteRequest()
    {
        String resource = "https://reqres.in/api/users/" + id;

        Step(MessageFormat.format("Delete {0}", resource));

            try {
                APIs.regresApi.DeleteRegres(resource);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            Info(MessageFormat.format("Deleted user: {0}", id ));
    }

}
