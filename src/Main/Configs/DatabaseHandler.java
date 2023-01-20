package Main.Configs;

import Main.Connectors.Data.Tinkoff.InsrumentService.Instrument;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Call to save instrument into DB
     *
     * @param instrument
     * @throws ClassNotFoundException
     */
    public void saveShare(Instrument instrument) throws ClassNotFoundException {
        String insert = "INSERT INTO " + InstrumentConst.SHARE_TABLE + "(" + InstrumentConst.SHARE_FIGI + "," + InstrumentConst.SHARE_COUNTRY_OF_RISK
                + "," + InstrumentConst.SHARE_COUNTRY_OF_RISK_NAME + "," + InstrumentConst.SHARE_SECTOR + "," + InstrumentConst.SHARE_NAME + ")" + "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert); // set our request
            // set our values
            prSt.setString(1, instrument.getFigi());
            prSt.setString(2, instrument.getCountryOfRisk());
            prSt.setString(3, instrument.getCountryOfRiskName());
            prSt.setString(4, instrument.getSector());
            prSt.setString(5, instrument.getName());


            prSt.executeUpdate(); // to set info in DB
        } catch (SQLException e) {
            System.out.println("Error in saveShares: " + e);
        }
    }

    /**
     * Call to update Instruments in DB
     *
     * @param instruments
     */
    public void updateInstruments(Instrument[] instruments) {
        for (Instrument i : instruments) {
            updateInstrument(i);
        }
    }

    public void updateInstrument(Instrument instrument) {
        String update = "UPDATE " + InstrumentConst.SHARE_TABLE + " SET " +
                InstrumentConst.SHARE_TICKER + " =? , " + InstrumentConst.SHARE_CLASSCODE + " =? , " +
                InstrumentConst.SHARE_ISIN + " =? , " + InstrumentConst.SHARE_LOT + " =? , " +
                InstrumentConst.SHARE_CURRENCY + " =? , " + InstrumentConst.SHARE_SHORT_ENABLED_FLAG + " =? , " +
                InstrumentConst.SHARE_NAME + " =? , " + InstrumentConst.SHARE_EXCHANGE + " =? , " +
                InstrumentConst.SHARE_IPO_DATE + " =? , " + InstrumentConst.SHARE_ISSUE_SIZE + " =? , " +
                InstrumentConst.SHARE_COUNTRY_OF_RISK + " =? , " + InstrumentConst.SHARE_COUNTRY_OF_RISK_NAME + " =? , " +
                InstrumentConst.SHARE_SECTOR + " =? , " + InstrumentConst.SHARE_ISSUE_SIZE_PLAN + " =? , " +
                InstrumentConst.SHARE_TRADING_STATUS + " =? , " +
                InstrumentConst.SHARE_OTC_FLAG + " =? , " + InstrumentConst.SHARE_BUY_AVAILABLE_FLAG + " =? , " +
                InstrumentConst.SHARE_SELL_AVAILABLE_FLAG + " =? , " + InstrumentConst.SHARE_DIV_YIELD_FLAG + " =? , " +
                InstrumentConst.SHARE_SHARE_TYPE + " =? , " + InstrumentConst.SHARE_API_TRADE_AVAILABLE_FLAG + " =? , " +
                InstrumentConst.SHARE_UID + " =? , " + InstrumentConst.SHARE_REAL_EXCHANGE + " =? , " +
                InstrumentConst.SHARE_POSITION_UID + " =? , " + InstrumentConst.SHARE_FOR_IIS_FLAG + " =? , " +
                InstrumentConst.SHARE_FOR_QUAL_INVESTOR_FLAG + " =? , " + InstrumentConst.SHARE_WEEKEND_FLAG + " =? , " +
                InstrumentConst.SHARE_BLOCKED_TCA_FLAG + " =? , " + InstrumentConst.SHARE_FIRST_MIN_CANDLE + " =? , " +
                InstrumentConst.SHARE_FIRST_DAY_CANDLE + " =? " + " WHERE " + InstrumentConst.SHARE_FIGI + " = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update); // set our request
            // set our values
            prSt.setString(1, instrument.getTicker());
            prSt.setString(2, instrument.getClassCode());
            prSt.setString(3, instrument.getIsin());
            prSt.setInt(4, instrument.getLot());
            prSt.setString(5, instrument.getCurrency());
            prSt.setBoolean(6, instrument.isShortEnabledFlag());
            prSt.setString(7, instrument.getName());
            prSt.setString(8, instrument.getExchange());
            prSt.setString(9, instrument.getIpoDate());
            prSt.setString(10, instrument.getIssueSize());
            prSt.setString(11, instrument.getCountryOfRisk());
            prSt.setString(12, instrument.getCountryOfRiskName());
            prSt.setString(13, instrument.getSector());
            prSt.setString(14, instrument.getIssueSizePlan());
            prSt.setString(15, instrument.getTradingStatus());
            prSt.setBoolean(16, instrument.isOtcFlag());
            prSt.setBoolean(17, instrument.isBuyAvailableFlag());
            prSt.setBoolean(18, instrument.isSellAvailableFlag());
            prSt.setBoolean(19, instrument.isDivYieldFlag());
            prSt.setString(20, instrument.getShareType());
            prSt.setBoolean(21, instrument.isApiTradeAvailable());
            prSt.setString(22, instrument.getUid());
            prSt.setString(23, instrument.getRealExchange());
            prSt.setString(24, instrument.getPositionUid());
            prSt.setBoolean(25, instrument.isForIisFlag());
            prSt.setBoolean(26, instrument.isForQualInvestorFlag());
            prSt.setBoolean(27, instrument.isWeekendFlag());
            prSt.setBoolean(28, instrument.isBlockedTcaFlag());
            prSt.setString(29, instrument.getFirst1minCandleDate());
            prSt.setString(30, instrument.getFirst1dayCandleDate());
            prSt.setString(31, instrument.getFigi());

            prSt.executeUpdate(); // to set info in DB
        } catch (SQLException e) {
            System.out.println("Error in updateInstrument: " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getNamesByCountryOfRisk(String country) {
        ResultSet rs = null;
        String[] resultString = null;
        List<String> stringList = new ArrayList<>();

        String select = "SELECT " + InstrumentConst.SHARE_NAME + " FROM " + InstrumentConst.SHARE_TABLE + " WHERE "
                + InstrumentConst.SHARE_COUNTRY_OF_RISK + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // set our request
            prSt.setString(1, country);
            rs = prSt.executeQuery(); // to get info from DB
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error in getNamesByCountryOfRisk: " + e);
        }

        try {
            while (rs.next()) { // for each string
                stringList.add(rs.getString(1)); // we take first string in line, cos it's our country of risk
            }
        } catch (SQLException e) {
            System.out.println("Error in getNamesByCountryOfRisk: " + e);
        }

        // if we have results - fill it in array
        if (stringList.size() > 0) {
            resultString = new String[stringList.size()];
            stringList.toArray(resultString);
        }

        return resultString;
    }

    public String getFigiByName(String name) {
        ResultSet rs = null;
        String resultString = null;

        String select = "SELECT " + InstrumentConst.SHARE_FIGI + " FROM " + InstrumentConst.SHARE_TABLE + " WHERE "
                + InstrumentConst.SHARE_NAME + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // set our request
            prSt.setString(1, name);
            rs = prSt.executeQuery(); // to get info from DB
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error in getFigiByName: " + e);
        }

        try {
            while (rs.next()) { // for each string
                resultString = rs.getString(1); // we take first string in line, cos it's our param
            }
        } catch (SQLException e) {
            System.out.println("Error in getNamesByCountryOfRisk: " + e);
        }

        return resultString;
    }

    /**
     * Method to get any value from shares by figi
     *
     * @param value
     * @param figi
     * @return value as string or null if error in request
     */
    public String getValueByFigi(String value, String figi) {
        ResultSet rs = null;
        String resultString = null;

        String select = "SELECT " + /* set value name */ "?" + " FROM " + InstrumentConst.SHARE_TABLE + " WHERE "
                + InstrumentConst.SHARE_FIGI + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // set our request
            prSt.setString(1, value);
            prSt.setString(2, figi);
            rs = prSt.executeQuery(); // to get info from DB
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error in getParamByFigi: " + e);
        }

        try {
            while (rs.next()) { // for each string
                resultString = rs.getString(1); // we take first string in line, cos it's our value
            }
        } catch (SQLException e) {
            System.out.println("Error in getParamByFigi: " + e);
        }

        return resultString;
    }
}
