import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnection {

    public static Connection connect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/house_login_register",
                "root",
                ""
            );
            System.out.println("Database Connected Successfully");
        } catch (Exception e) {
            System.out.println("Database Connection Error: " + e.getMessage());
        }
        return con;
    }

    public static boolean addTenant(
        String tenantName,
        String email,
        String phone,
        String movingDate,
        double deposit,
        String houseNumber
    ) {
        String sql = "INSERT INTO tenant(tenant_name, email, phone, moving_date, deposit, house_number) VALUES(?,?,?,?,?,?)";
        try {
            Connection con = connect();
            if (con == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed!");
                return false;
            }

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tenantName);
            pst.setString(2, email);
            pst.setString(3, phone);
            pst.setString(4, movingDate);
            pst.setDouble(5, deposit);
            pst.setString(6, houseNumber);

            int rows = pst.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding tenant: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteTenant(String houseNumber) {
        String sql = "DELETE FROM tenant WHERE house_number = ?";
        try {
            Connection con = connect();
            if (con == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed!");
                return false;
            }

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, houseNumber);

            int rows = pst.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting tenant: " + e.getMessage());
            return false;
        }
    }

    public static boolean addMovingOut(
        String tenantName,
        String email,
        String phone,
        String movingOutDate,
        String houseNumber
    ) {
        String sql = "INSERT INTO moving_out(tenant_name, email, phone, moving_out_date, house_number) VALUES(?,?,?,?,?)";
        
        try {
            Connection con = connect();
            if (con == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed!");
                return false;
            }

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tenantName);
            pst.setString(2, email);
            pst.setString(3, phone);
            pst.setString(4, movingOutDate);
            pst.setString(5, houseNumber);

            int rows = pst.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error moving-out record: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        Connection con = connect();
        if (con != null) {
            System.out.println("Connection is working!");

        } else {
            System.out.println("Connection is null. Check your DB settings.");
        }
    }
}
