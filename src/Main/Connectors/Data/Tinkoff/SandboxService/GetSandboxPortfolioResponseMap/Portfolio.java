package Main.Connectors.Data.Tinkoff.SandboxService.GetSandboxPortfolioResponseMap;

import Main.Configs.DatabaseHandler;
import Main.Connectors.Data.Tinkoff.InsrumentService.Shares.CurrencyUnitsNano;
import Main.Connectors.Data.Tinkoff.PortfolioPositions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Portfolio {
    protected CurrencyUnitsNano totalAmountShares;
    protected CurrencyUnitsNano totalAmountBonds;
    protected CurrencyUnitsNano totalAmountEtf;
    protected CurrencyUnitsNano totalAmountCurrencies;
    protected CurrencyUnitsNano totalAmountFutures;
    //protected UnitsNano expectedYield // field for not sandbox method?
    protected PortfolioPositions[] positions;
    protected String accountId;
    //protected CurrencyUnitsNano totalAmountOptions;
    //protected CurrencyUnitsNano totalAmountSp;
    //protected CurrencyUnitsNano totalAmountPortfolio;
    protected PortfolioPositions[] virtualPositions;

    public Portfolio() {
    }

    public Portfolio(CurrencyUnitsNano totalAmountShares, CurrencyUnitsNano totalAmountBonds, CurrencyUnitsNano totalAmountEtf, CurrencyUnitsNano totalAmountCurrencies, CurrencyUnitsNano totalAmountFutures, PortfolioPositions[] positions, String accountId, PortfolioPositions[] virtualPositions) {
        this.totalAmountShares = totalAmountShares;
        this.totalAmountBonds = totalAmountBonds;
        this.totalAmountEtf = totalAmountEtf;
        this.totalAmountCurrencies = totalAmountCurrencies;
        this.totalAmountFutures = totalAmountFutures;
        this.positions = positions;
        this.accountId = accountId;
        this.virtualPositions = virtualPositions;
    }

    public int notNullTotalAmountCount() {

        return notNullTotalAmount().length;
    }

    public CurrencyUnitsNano[] notNullTotalAmount() {
        CurrencyUnitsNano[] resultCUN = null;
        CurrencyUnitsNano[] allCUN = {this.totalAmountBonds, this.totalAmountCurrencies, this.totalAmountEtf,
                this.totalAmountFutures, this.totalAmountShares}; //copy all CurrencyUnitsNano field
        List<CurrencyUnitsNano> CUNList = new ArrayList<>();

        for (CurrencyUnitsNano c : allCUN) {
            if (c.getUnits().equalsIgnoreCase("0") || c.getNano() != 0) // if not null set into list
                CUNList.add(c);
        }

        resultCUN = new CurrencyUnitsNano[CUNList.size()];
        return CUNList.toArray(resultCUN);
    }

    /**
     * @return map wth key - instrument type, value - sum of instrument values of this type
     */
    public HashMap<String, Double> getInstrumentsTypesAndValue() {
        HashMap<String, Double> result = new HashMap<>();
        CurrencyUnitsNano[] allCUN = {this.totalAmountBonds, this.totalAmountCurrencies, this.totalAmountEtf,
                this.totalAmountFutures, this.totalAmountShares}; //copy all CurrencyUnitsNano field

        for (int i = 0; i < allCUN.length; i++) {
            if (!allCUN[i].getUnits().equalsIgnoreCase("0") || allCUN[i].getNano() != 0) { // if not null set into map
                String name = null;
                switch (i) {
                    case 0:
                        name = "Bonds";
                        break;
                    case 1:
                        name = "Currencies";
                        break;
                    case 2:
                        name = "Etf";
                        break;
                    case 3:
                        name = "Futures";
                        break;
                    case 4:
                        name = "Shares";
                        break;
                }
                result.put(name, allCUN[i].getValue());
            }
        }
        return result;
    }

    public int getInstrumentsTypesCount() {
        return getInstrumentsTypesAndValue().size();
    }

    public int getCountOfSectors() {
        return getSectors().length;
    }

    public String[] getSectors() {
        String[] result = null;

        for (PortfolioPositions p : this.getPositions()) {
            String sector = DatabaseHandler.getValueByPositionUID("SECTOR", p.getFigi());

        }
        return result;
    }

    /**
     * @return map wth key - sector, value - sum of instrument values of this sector type
     */
    public HashMap<String, Double> getSumValueBySector() {
        HashMap<String, Double> result = new HashMap<>();
        for (PortfolioPositions p : this.getPositions()) {
            String sector = DatabaseHandler.getValueByPositionUID("SECTOR", p.getFigi());
            if (sector == null)
                sector = p.getInstrumentType();
            Double newValue = null;
            if (p.getAveragePositionPrice() != null) { // we had average price for element
                // sum price for all elements
                newValue = p.getAveragePositionPrice().getValue() * p.getQuantity().getValue() * p.getQuantityLots().getValue(); // price for one element * count of lots * elements in lot
            } else { // we also had price for all
                newValue = p.getQuantity().getValue();
            }
            if (result.containsKey(sector))
                newValue += result.get(sector).doubleValue();

            result.put(sector, newValue);
        }
        return result;
    }

    public CurrencyUnitsNano getTotalAmountShares() {
        return totalAmountShares;
    }

    public CurrencyUnitsNano getTotalAmountBonds() {
        return totalAmountBonds;
    }

    public CurrencyUnitsNano getTotalAmountEtf() {
        return totalAmountEtf;
    }

    public CurrencyUnitsNano getTotalAmountCurrencies() {
        return totalAmountCurrencies;
    }

    public CurrencyUnitsNano getTotalAmountFutures() {
        return totalAmountFutures;
    }

    public PortfolioPositions[] getPositions() {
        return positions;
    }

    public String getAccountId() {
        return accountId;
    }

    public PortfolioPositions[] getVirtualPositions() {
        return virtualPositions;
    }
}
