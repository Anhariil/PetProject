package Connectors.Data.Tinkoff.MarketDataService.GetCandles;

import Connectors.Data.Tinkoff.InsrumentService.UnitsNano;

public class Candle {
    protected UnitsNano open;
    protected UnitsNano high;
    protected UnitsNano low;
    protected UnitsNano close;
    protected String volume;
    protected String time;
    protected boolean isComplete;

    public Candle(UnitsNano open, UnitsNano high, UnitsNano low, UnitsNano close, String volume, String time, boolean isComplete) {
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.time = time;
        this.isComplete = isComplete;
    }

    public UnitsNano getOpen() {
        return open;
    }

    public UnitsNano getHigh() {
        return high;
    }

    public UnitsNano getLow() {
        return low;
    }

    public UnitsNano getClose() {
        return close;
    }

    public String getVolume() {
        return volume;
    }

    public String getTime() {
        return time;
    }

    public boolean isComplete() {
        return isComplete;
    }
}
