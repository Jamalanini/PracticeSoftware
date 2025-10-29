
import java.sql.*;
import java.util.Random;

public class Data {

    String Url = "https://practicesoftwaretesting.com/";

    Connection con ;

    Statement stmt ;

    ResultSet Res ;

    String TheFirstName ;

    String TheLastName ;

    String ThePhone ;

    String TheAddress;

    String TheCountry ;

    String TheCity ;

    String ThePostalCode;

    String TheEmail;

    String ThePassword = "123!@#P@ssw0rd";

    String Domain = "@gmail.com";

    Random rand = new Random();

    int RandomNumberForEmail = rand.nextInt(3698);

    int Rand = RandomNumberForEmail ;

    int Year = rand.nextInt(1950,2008);

    int Month = rand.nextInt(1,13);


    int Day = rand.nextInt(1,31);

    String TheDateOfBirth = String.format("%04d-%02d-%02d", Year, Month, Day);

    String ExpectedLogInMassage = "My account";

    String LoginEmail ;

    String StoredEmail ;

    String HandTools ="https://practicesoftwaretesting.com/category/hand-tools" ;

    String PowerTools ="https://practicesoftwaretesting.com/category/power-tools" ;

    String OtherTools ="https://practicesoftwaretesting.com/category/other" ;

    String CheckOutLink = "https://practicesoftwaretesting.com/checkout";

    String Finish = "Payment was successful";







    }

