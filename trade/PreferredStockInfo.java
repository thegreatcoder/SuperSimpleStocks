package trade;

// PreferredStockInfo: Represents the main information related to a common stock
public class PreferredStockInfo extends StockInfo
{
	protected final double fixeddividend;


	public PreferredStockInfo(double lastdividend, double fixeddividend, int parvalue)
	{
		super(lastdividend, parvalue);

		this.fixeddividend = fixeddividend;
	}


	public double getFixeddividend()
	{
		return fixeddividend;
	}


	public double computeDividendYield(double tickerprice)
	{
		if (tickerprice <= 0)
			throw new IllegalArgumentException();

		return (fixeddividend * parvalue / tickerprice);
	}

}
