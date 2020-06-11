import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.Date;

public class Insert {
    public static void insertData() throws SQLException {

        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/umuzi","user","pass123");
            Statement statement = connection.createStatement()){

            statement.execute(sqlInsertIntoCustomerTable(1,"Thabiso","Seekane", "Male","1718 pompano Street","061784773","S.T@gmail.com","Johannesburg", "South Africa"));
            statement.execute(sqlInsertIntoCustomerTable(2,"Kamogelo","lethole", "Female","56A Lerutho Street","08471126","Kamo.L@gmail.com","Johannesburg", "South Africa"));
            statement.execute(sqlInsertIntoCustomerTable(3,"Thembinkosi","Reginald", "Male","1605 pirannah Street","073482567","Regi.T@Yahoo.com","South Bronx", "U.S.A"));
            statement.execute(sqlInsertIntoCustomerTable(4,"Tlou","Maponya", "Male","57 julies ","081443178","Maponya@gmail.com","Pretoria", "South Africa"));
            statement.execute(sqlInsertIntoCustomerTable(5,"Mbali","Soja", "Female","1820 river Street","073558947","Sojambali@gmail.com","Johannesburg", "South Africa"));

            statement.execute(sqlInsertIntoOrdersTable(1,1,3, Date.from(Instant.parse("05-09-2018")),Date.from(Instant.parse("")),"Not Shipped"));
            statement.execute(sqlInsertIntoOrdersTable(1,2,2, Date.from(Instant.parse("04-09-2018")),Date.from(Instant.parse("04-09-2018")),"Shipped"));
            statement.execute(sqlInsertIntoOrdersTable(2,3,3, Date.from(Instant.parse("05-09-2018")),Date.from(Instant.parse("")),"Not Shipped"));

            statement.execute(sqlInsertIntoPaymentsTable(1,1,Date.from(Instant.parse("03-09-2018")),150.75));
            statement.execute(sqlInsertIntoPaymentsTable(5,2,Date.from(Instant.parse("03-09-2018")),150.75));
            statement.execute(sqlInsertIntoPaymentsTable(4,3,Date.from(Instant.parse("03-09-2018")),700.60));

        } catch (SQLException e){
            System.err.format("SQL State: %s\n%s", e.getSQLState(),e.getMessage());
            System.out.println("ERROR: Currently NOT connected to the database, please connect to the database.");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private static String sqlInsertIntoCustomerTable(int id,String firstName, String lastName, String gender, String address, String phone, String email, String city, String country) {
        return "insert into customer (id,first_name,last_name,gender,address,phone,email,city,country)" +
                "values ('"+id+"','" + firstName + "','" + lastName + "','" +
                gender + "','" + address + "','" + phone + "','" + email + "','" + city + "','" + country + "')";
    }

    private static String sqlInsertIntoPaymentsTable(int customerId, int paymentsId, Date paymentsDate, double amount) {
        return "insert into customer (customer_id,payments_id,payments_date,amount)" +
                "values ('"+customerId+"','" +paymentsId+ "','" +paymentsDate+ "','" + amount+ "')";

    }

    private static String sqlInsertIntoOrdersTable(int product_id, int payments_id, int fulfiiledByEmployeeId, Date dateRequired, Date dateShipped, String status) {
        return "insert into customer (product_id,payments_id,fulfiiled_by_employee_id,date_required,date_shipped,status)" +
                "values ('"+product_id+"','" +payments_id+ "','" +fulfiiledByEmployeeId+ "','" +
                dateRequired+ "','" + dateShipped + "','" +status+ "')";
    }


}
