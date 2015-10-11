package trade;

// Trade: represents a trade (buy or sell) and its main attributes
public class Trade
{	
	private final long ts;
	private final String stocksymbol;
	private final int quantity;
	private final TradeType type;
	private final double price;

	public Trade(long ts, String stocksymbol, int quantity, TradeType type, double price)
	{
		this.ts = ts;
		this.quantity = quantity;
		this.stocksymbol = stocksymbol;
		this.type = type;
		this.price = price;
	}


	public long getTs()
	{
		return ts;
	}

	public String getStocksymbol()
	{
		return stocksymbol;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public TradeType getType()
	{
		return type;
	}

	public double getPrice()
	{
		return price;
	}

	public String toString()
	{
		return (ts + "\t" + stocksymbol + "\t" + quantity + "\t" + type + "\t" + price);
	}

}
