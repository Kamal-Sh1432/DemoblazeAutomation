package data;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "purchaseData")
    public static Object[][] getPurchaseData() {

        return new Object[][]{

                {"user_perftest", "pass1234", "Kamal Sharma", "India", "Delhi", "4111111111111111", "12", "2026"},
                {"user_perftest", "pass1234", "Rahul Verma", "India", "Mumbai", "4111111111111112", "11", "2027"},
                {"user_perftest", "pass1234", "Anita Singh", "India", "Pune", "4111111111111113", "10", "2028"},
                {"user_perftest", "pass1234", "Rohit Mehta", "India", "Noida", "4111111111111114", "09", "2029"},
                {"user_perftest", "pass1234", "Neha Gupta", "India", "Gurgaon", "4111111111111115", "08", "2030"},
                {"user_perftest", "pass1234", "Amit Jain", "India", "Jaipur", "4111111111111116", "07", "2031"},
                {"user_perftest", "pass1234", "Pooja Sharma", "India", "Bangalore", "4111111111111117", "06", "2032"},
                {"user_perftest", "pass1234", "Suresh Iyer", "India", "Chennai", "4111111111111118", "05", "2033"},
                {"user_perftest", "pass1234", "Kiran Rao", "India", "Hyderabad", "4111111111111119", "04", "2034"},
                {"user_perftest", "pass1234", "Deepak Malhotra", "India", "Kolkata", "4111111111111120", "03", "2035"}

        };
    }
}
