import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.Statement;

public class CreateTable {

    private static final String dropCustomerTable = "drop table if exists customer";
    private static final String sqlCreateCustomerTable = "create table if not exists customer (\n" +
            "id serial not null primary key,\n" +
            "first_name varchar(50) not null,\n" +
            "last_name varchar(50) not null,\n" +
            "gender varchar(6) not null,\n" +
            "address varchar(200) not null,\n" +
            "phone varchar(20) not null,\n" +
            "email varchar(100),\n" +
            "city varchar(20) not null,\n" +
            "country varchar(50) not null\n" +
            ")";
    private static final String dropOrderTable = "drop table if exists orders";
    private static final String sqlCreateOrderTable = "create table if not exists orders (\n" +
            "id serial not null primary key,\n" +
            "product_id bigint unique not null,\n" +
            "payment_id bigint unique not null,\n" +
            "fulfilled_by_employee_id bigint unique not null,\n" +
            "address varchar(200) not null,\n" +
            "date_required date not null,\n" +
            "date_shipped date\n" +
            ")";
    private static final String dropPaymentsTable = "drop table if exists payments";
    private static final String sqlCreatePaymentsTable = "create table if not exists payments (\n" +
            "customer_id bigint not null,\n" +
            "payment_id bigint not null,\n" +
            "payment_date bigint not null,\n" +
            "amount numeric(19,2) not null\n" +
            ")";

    public static void createTable(){
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/umuzi", "user", "pass123");
            Statement statement = connection.createStatement()){
            statement.execute(dropCustomerTable);
            statement.execute(sqlCreateCustomerTable);

            statement.execute(dropOrderTable);
            statement.execute( sqlCreateOrderTable);
            statement.execute(dropPaymentsTable);
            statement.execute(sqlCreatePaymentsTable);
        }
        catch (SQLDataException e){
            System.err.format("SQL STATE: %s\n%s", e.getSQLState(), e.getMessage());
            System.out.println("ERROR: NOT connected to a database,  please connect to a database.");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
