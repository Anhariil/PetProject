package Main.Configs;

import Main.Connectors.Data.Tinkoff.InsrumentService.Instrument;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    /**
     * method for connection to ours DB on local pc
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void saveShares(Instrument instruments) throws ClassNotFoundException {
        String insert = "INSERT INTO " + Const.SHARE_TABLE + "(" + Const.SHARE_FIGI + "," + Const.SHARE_COUNTRY_OF_RISK
                + "," + Const.SHARE_COUNTRY_OF_RISK_NAME + "," + Const.SHARE_SECTOR + ")" + "VALUES(?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert); // set our request
            // set our values
            prSt.setString(1, instruments.getFigi());
            prSt.setString(2, instruments.getCountryOfRisk());
            prSt.setString(3, instruments.getCountryOfRiskName());
            prSt.setString(4, instruments.getSector());

            prSt.executeUpdate(); // to set info in DB
        } catch (SQLException e) {
            System.out.println("Error in saveShares: " + e);
        }
    }

    public String[] getNamesByCountryOfRisk(String country) {
        ResultSet rs = null;
        String[] resultString = null;
        String select = "SELECT " + Const.SHARE_COUNTRY_OF_RISK + " FROM " + Const.SHARE_TABLE + " WHERE "
                + Const.SHARE_COUNTRY_OF_RISK + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // set our request
            prSt.setString(1, country);
            rs = prSt.executeQuery(); // to get info from DB
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error in getNamesByCountryOfRisk: " + e);
        }

        int counter = 0;

        try {
            while (rs.next()) {
                counter++;
            }
        } catch (SQLException e) {
            System.out.println("Error in getNamesByCountryOfRisk: " + e);
        }

        if (counter > 0) {

        }

        return resultString;
    }
}
