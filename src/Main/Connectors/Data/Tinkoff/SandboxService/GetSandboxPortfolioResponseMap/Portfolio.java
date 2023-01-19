package Main.Connectors.Data.Tinkoff.SandboxService.GetSandboxPortfolioResponseMap;

import Main.Connectors.Data.Tinkoff.InsrumentService.Shares.CurrencyUnitsNano;
import Main.Connectors.Data.Tinkoff.PortfolioPositions;

public class Portfolio {
    protected CurrencyUnitsNano totalAmountShares;
    protected CurrencyUnitsNano totalAmountBonds;
    protected CurrencyUnitsNano totalAmountEtf;
    protected CurrencyUnitsNano totalAmountCurrencies;
    protected CurrencyUnitsNano totalAmountFutures;
    //protected UnitsNano expectedYield //
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
}
