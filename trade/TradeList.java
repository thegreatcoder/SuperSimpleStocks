package trade;
import java.util.LinkedList;
import java.util.ListIterator;
import java.lang.Math;

// TradeList: List of Trade objects
public class TradeList extends LinkedList<Trade>
{
	public void print()
	{
		System.out.println("#\ttimestamp\tSYM\tquant\ttype\tprice");

		ListIterator<Trade> it = listIterator();
		int i = 0;
		while (it.hasNext())
		{
			System.out.println(i + ")\t" +it.next());
			i++;
		}
	}

	public void printLast15Minutes()
	{
		System.out.println("#\ttimestamp\tSYM\tquant\ttype\tprice");

		long ts = System.currentTimeMillis();

		ListIterator<Trade> it = listIterator();

		int i = 0;
		while (it.hasNext())
		{
			Trade t = it.next();
			if ( t.getTs() > (ts - 15 * 60 * 1000) )
				System.out.println(i + ")\t" +t);
			i++;
		}
	}

	public double getStockPrice(String stocksymbol) throws TradeListException
	{
		long ts = System.currentTimeMillis();

		ListIterator<Trade> it = listIterator();
		double sum_prod = 0;
		double sum_quantity = 0;
		boolean moretrades = true;
		while ( moretrades && it.hasNext())
		{
			Trade t = it.next();
			if ( t.getTs() > (ts - 15 * 60 * 1000) )
			{
				if (t.getStocksymbol().equals(stocksymbol))
				{
					sum_prod += (t.getPrice() * t.getQuantity());
					sum_quantity += t.getQuantity();
				}
			} else
				moretrades = false;
		}

		if (sum_quantity <= 0 || sum_prod < 0)
			throw new TradeListException();

		return sum_prod / sum_quantity;
	}

	public double getGBCE() throws TradeListException
	{	
		int prod = 1;
		int count = 0;

		ListIterator<Trade> it = listIterator();
		while (it.hasNext())
		{
			prod *= (it.next().getPrice());
			count++;
		}
		if (count <= 0)
			throw new TradeListException();

		return Math.pow(prod, 1 / (double)count);
	}
}
