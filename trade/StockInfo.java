package trade;

// StockInfo: Represents the main information related to a generic stock
public abstract class StockInfo
{
	protected final double lastdividend;
	protected final int parvalue;


	public StockInfo(double lastdividend, int parvalue)
	{
		this.lastdividend = lastdividend;
		this.parvalue = parvalue;
	}

	public double getLastDividend()
	{
		return lastdividend;
	}

	public int getParValue()
	{
		return parvalue;
	}

	public abstract double computeDividendYield(double tickerprice);

	public double computePERatio(double tickerprice)
	{
		if (lastdividend <= 0)
			throw new IllegalStateException();

		return (tickerprice / lastdividend);
	}
}
