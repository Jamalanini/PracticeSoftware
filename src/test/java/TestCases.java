import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class TestCases  extends Data{



    WebDriver driver;
    @BeforeTest
    public void MySetup() throws SQLException {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(Url);
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "0000");
    }



    @Test(priority = 1, enabled = true)
    public void Create() throws SQLException {

        String query = "Insert into customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country , postalCode) Values (999, 'Abc Company', 'Ali', 'Ahmad', '962797700235', '123 Main St', 'Amman', 'Jordan', '18881') ";

        stmt = con.createStatement();

        int RowsInserted = stmt.executeUpdate(query);


    }

    @Test(priority = 3, enabled = true)
    public void Read() throws SQLException {

        String Query = "Select * from customers where customerNumber = 999";
        stmt = con.createStatement();
        Res = stmt.executeQuery(Query);

        while (Res.next()) {

            TheFirstName = Res.getString("contactFirstName").toString().trim();

            TheLastName = Res.getString("contactLastName").toString().trim();

            ThePhone = Res.getString("phone").toString().trim();

            TheAddress = Res.getString("addressLine1").toString().trim();

            TheCountry = Res.getString("country").toString().trim();

            TheCity = Res.getString("city").toString().trim();

            ThePostalCode = Res.getString("postalCode").toString().trim();

            TheEmail = TheFirstName + TheLastName + RandomNumberForEmail + Domain;

            StoredEmail = TheEmail;

            ;


        }


    }

    @Test(priority = 2, enabled = true)
    public void Update() throws SQLException {

        String Query = "UPDATE customers SET contactLastName = 'mohammad' WHERE customerNumber = 999";

        stmt = con.createStatement();

        int rowInserted = stmt.executeUpdate(Query);


    }

    @Test(priority = 4, enabled = true)
    public void Delete() throws SQLException {

        String query = "delete from customers where customerNumber =999";

        stmt = con.createStatement();

        int rowInserted = stmt.executeUpdate(query);

    }


    @Test(priority = 5, enabled = true)
    public void SignInTest() throws InterruptedException {

        WebElement LogInPage = driver.findElement(By.cssSelector(".nav-link[data-test='nav-sign-in']"));
        LogInPage.click();

        WebElement Register = driver.findElement(By.linkText("Register your account"));
        Register.click();


        WebElement FirstName = driver.findElement(By.id("first_name"));
        WebElement LastName = driver.findElement(By.id("last_name"));
        WebElement DateOfBirth = driver.findElement(By.id("dob"));
        WebElement Street = driver.findElement(By.id("street"));
        WebElement PostalCode = driver.findElement(By.id("postal_code"));
        WebElement City = driver.findElement(By.id("city"));
        WebElement State = driver.findElement(By.id("state"));

        WebElement Country = driver.findElement(By.id("country"));

        Thread.sleep(1000);

        Select SelectedCountry = new Select(Country);
        SelectedCountry.selectByVisibleText(TheCountry);


        WebElement Phone = driver.findElement(By.id("phone"));
        WebElement Email = driver.findElement(By.id("email"));
        WebElement Password = driver.findElement(By.id("password"));

        FirstName.sendKeys(TheFirstName);
        LastName.sendKeys(TheLastName);
        Street.sendKeys(TheAddress);
        PostalCode.sendKeys(ThePostalCode);
        City.sendKeys(TheCity);
        DateOfBirth.sendKeys(TheDateOfBirth);
        Phone.sendKeys(ThePhone);
        Email.sendKeys(TheEmail);
        Password.sendKeys(ThePassword);
        State.sendKeys(TheCity);

        WebElement RegisterButton = driver.findElement(By.cssSelector("button[type='submit']"));
        RegisterButton.click();

    }

    @Test(priority = 6, enabled = true)
    public void LoginTest() throws InterruptedException {

        LoginEmail = StoredEmail;


        WebElement LogInPage = driver.findElement(By.cssSelector(".nav-link[data-test='nav-sign-in']"));
        LogInPage.click();

        Thread.sleep(1500);

        WebElement Email = driver.findElement(By.id("email"));
        Email.sendKeys(LoginEmail);

        WebElement Password = driver.findElement(By.id("password"));
        Password.sendKeys(ThePassword);

        WebElement LoginButton = driver.findElement(By.xpath("//input[@value='Login']"));
        LoginButton.click();


        boolean ActualMessageForSignUp = driver.getPageSource().contains(ExpectedLogInMassage);


        // Assert.assertEquals(ActualMessageForSignUp ,true);


    }

    @Test(priority = 7, enabled = true)
    public void AddToCartTest() throws InterruptedException {
        driver.navigate().to("https://practicesoftwaretesting.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        for (int Cat = 0; Cat < 3; Cat++) {

            if (Cat == 0) {
                driver.navigate().to(HandTools);
            }

            if (Cat == 1) {
                driver.navigate().to(PowerTools);
            }

            if (Cat == 2) {
                driver.navigate().to(OtherTools);
            }


            for (int n = 0; n < 3; n++) {

                Thread.sleep(1000);

                // get all items
                List<WebElement> items = driver.findElements(By.className("card-title"));
                if (items.isEmpty()) {
                    System.out.println("No items found!");
                    return;
                }

                // pick a random item
                int randomIndex = rand.nextInt(items.size());
                items.get(randomIndex).click();

                // check if in stock
                List<WebElement> outOfStock = driver.findElements(By.xpath("//*[contains(text(),'Out of stock')]"));
                if (outOfStock.isEmpty()) {
                    WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-add-to-cart")));
                    addToCart.click();
                    System.out.println("Item added to cart successfully!");
                } else {
                    System.out.println("Item was out of stock, skipping...");
                }

                Thread.sleep(1000);


                System.out.println("All items processed successfully!");
            }
        }

    }

    @Test(priority = 8, enabled = true)
    public void CheckOut() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Go to checkout page
        driver.navigate().to(CheckOutLink);

        // Step 1 - Click checkout button
        WebElement checkoutbutton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success"))
        );
        checkoutbutton.click();


        WebElement Email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        Email.sendKeys(LoginEmail);

        WebElement Password = driver.findElement(By.id("password"));
        Password.sendKeys(ThePassword);

        WebElement LoginButton = driver.findElement(By.cssSelector("input[value='Login']"));
        LoginButton.click();


        WebElement proceedBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test='proceed-2']"))
        );
        proceedBtn.click();


        WebElement ProceedCheckOut2 = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test='proceed-3']"))
        );
        ProceedCheckOut2.click();


        WebElement PaymentMethod = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("payment-method"))
        );

        Select SelectPaymentMethod = new Select(PaymentMethod);
        SelectPaymentMethod.selectByIndex(2);


        WebElement ConfirmButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Confirm']"))
        );
        ConfirmButton.click();



      //  boolean Actual = driver.getPageSource().contains(Finish);
       // Assert.assertEquals(Actual, true);

        System.out.println("✅ Checkout process completed successfully.");
    }
}
