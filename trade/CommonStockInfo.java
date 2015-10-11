package trade;

// CommonStockInfo: Represents the main information related to a common stock
public class CommonStockInfo extends StockInfo
{
	public CommonStockInfo(double lastdividend, int parvalue)
	{
		super(lastdividend, parvalue);
	}

	public double computeDividendYield(double tickerprice)
	{
		if (tickerprice <= 0)
			throw new IllegalArgumentException();
		
		return (lastdividend / tickerprice);
	}
}
