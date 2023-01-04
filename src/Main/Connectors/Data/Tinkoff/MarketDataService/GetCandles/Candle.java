package Main.Connectors.Data.Tinkoff.MarketDataService.GetCandles;

import Main.Connectors.Data.Tinkoff.InsrumentService.UnitsNano;
import Main.DateTime;

public class Candle {
    protected UnitsNano open;
    protected UnitsNano high;
    protected UnitsNano low;
    protected UnitsNano close;
    protected String volume;
    protected DateTime time;
    protected boolean isComplete;

    public Candle(UnitsNano open, UnitsNano high, UnitsNano low, UnitsNano close, String volume, DateTime time, boolean isComplete) {
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

    public DateTime getTime() {
        return time;
    }

    public boolean isComplete() {
        return isComplete;
    }
}
