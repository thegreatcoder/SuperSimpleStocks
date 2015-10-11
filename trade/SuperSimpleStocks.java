package trade;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


public class SuperSimpleStocks
{	
	private static Scanner in = null;;

	public static void main(String[] args)
	{
		HashMap<String, StockInfo> stockmap = new HashMap<String, StockInfo>();
		populateStockMap(stockmap);


		TradeList l = new TradeList();
		populateTradeList(stockmap,l);

		in = new Scanner(System.in);
		while (true)
		{
			printMenu();

			int choice = Integer.parseInt(in.nextLine());

			takeAction(choice, stockmap, l);
		}
	}


	private static void takeAction(int choice, HashMap<String, StockInfo> stockmap, TradeList l)
	{
		switch (choice)
		{
		case 1:
			calcDividendYield(stockmap);
			break;
		case 2:
			calcPERatio(stockmap);
			break;
		case 3:
			recordTrade(stockmap, l);
			break;
		case 4:
			calcStockPrice(stockmap, l);
			break;
		case 5:
			calcGBCE(l);
			break;
		case 6:
			l.print();
			break;
		case 7:
			l.printLast15Minutes();
			break;
		case 8:
			in.close();
			System.exit(0);
		default:
			break;
		}
		System.out.println("");
	}


	private static void printMenu()
	{
		System.out.println("What do you want to do?");
		System.out.println("1) Calculate the dividend yield for a given stock");
		System.out.println("2) Calculate the p/e ratio for a given stock");
		System.out.println("3) Record a trade");
		System.out.println("4) Calculate stock price based on trades recorded in past 15 minutes");
		System.out.println("5) Calculate the GBCE All Share index");
		System.out.println("6) Print all trades recorded");
		System.out.println("7) Print all trades recorded in past 15 minutes");
		System.out.println("8) Exit");
	}


	private static void calcDividendYield(HashMap<String, StockInfo> stockmap)
	{
		System.out.print("Insert stock symbol: ");
		String stocksymbol = in.nextLine();
		StockInfo s = stockmap.get(stocksymbol);
		if (s == null)
		{
			System.out.println("Stock symbol not found!");
			return;
		}

		System.out.print("Insert ticker price: ");
		double tickerprice = Double.parseDouble(in.nextLine());
		if (tickerprice <= 0)
		{
			System.out.println("Invalid ticker price!");
			return;
		}
		
		System.out.println("Dividend Yield is: " + s.computeDividendYield(tickerprice));
	}

	private static void calcPERatio(HashMap<String, StockInfo> stockmap)
	{	
		System.out.print("Insert stock symbol: ");
		String stocksymbol = in.nextLine();
		StockInfo s = stockmap.get(stocksymbol);
		if (s == null)
		{
			System.out.println("Stock symbol not found!");
			return;
		}

		System.out.print("Insert ticker price: ");
		double tickerprice = Double.parseDouble(in.nextLine());
		if (tickerprice <= 0)
		{
			System.out.println("Invalid ticker price!");
			return;
		}
		
		if (s.getLastDividend() <= 0)
		{
			System.out.println("Impossible to evaluate P/E ratio for ths stock symbol");
			return;
		}

		System.out.println("P/E ratio is: " + s.computePERatio(tickerprice));
	}

	private static void recordTrade(HashMap<String, StockInfo> stockmap, TradeList l)
	{
		System.out.print("Enter stock symbol: ");
		String stocksymbol = in.nextLine();
		StockInfo s = stockmap.get(stocksymbol);
		if (s == null)
		{
			System.out.println("Stock symbol not found!");
			return;
		}

		System.out.print("Enter quantity of shares: ");
		int quantity = Integer.parseInt(in.nextLine());
		if (quantity <= 0)
		{
			System.out.println("Invalid quantity of shares!");
			return;
		}

		System.out.print("Buy or Sell? (B / S)  ");
		String bs = in.nextLine().toUpperCase();
		TradeType tradetype;
		if (bs.equals("B") || bs.equals("BUY"))
		{
			tradetype = TradeType.BUY; 
		} else if (bs.equals("S") || bs.equals("SELL"))
		{
			tradetype = TradeType.SELL; 
		} else
		{
			System.out.println("Invalid trade type!");
			return;
		}

		System.out.print("Enter price: ");
		double price = Double.parseDouble(in.nextLine());
		if (quantity <= 0)
		{
			System.out.println("Invalid price!");
			return;
		}

		l.addFirst( new Trade( System.currentTimeMillis(),
				stocksymbol,
				quantity,
				tradetype,
				price ) );

		System.out.println("Trade recorded!");
	}

	private static void calcStockPrice(HashMap<String, StockInfo> stockmap, TradeList l)
	{
		System.out.print("Insert stock symbol: ");
		String stocksymbol = in.nextLine();
		StockInfo s = stockmap.get(stocksymbol);
		if (s == null)
		{
			System.out.println("Stock symbol not found!");
			return;
		}

		try
		{
			System.out.println("Stock price is: " + l.getStockPrice(stocksymbol));
		} catch (TradeListException e)
		{
			System.out.println("Operation is not valid");
		}
	}

	private static void calcGBCE(TradeList l)
	{
		try
		{
			System.out.println("GBCE All Share index is: " + l.getGBCE());
		} catch (TradeListException e)
		{
			System.out.println("Operation is not valid");
		}
	}


	private static void populateStockMap(HashMap<String, StockInfo> stockmap)
	{
		stockmap.put( "TEA", new CommonStockInfo(0, 100) );
		stockmap.put( "POP", new CommonStockInfo(8, 100) );
		stockmap.put( "ALE", new CommonStockInfo(23, 60) );
		stockmap.put( "GIN", new PreferredStockInfo(8, 0.02, 100) );
		stockmap.put( "JOE", new CommonStockInfo(13, 250) );	
	}


	// populateTradeList: Populate the tradelist with trades that happened in the last 20 minutes
	private static void populateTradeList(HashMap<String, StockInfo> stockmap, TradeList l)
	{
		long ts = System.currentTimeMillis();
		int twentyminutes = 20 * 60;	// number of seconds in 20 minutes

		int timespent = 0;

		Set<String> keys = stockmap.keySet();
		String[] stocks = keys.toArray(new String[keys.size()]);


		boolean done = false;
		while (!done)
		{
			timespent += (int)(Math.random() * 11) + 10; // number between 10 and 20
			if (timespent < twentyminutes)
			{
				String stocksymbol = stocks[(int)(Math.random() * stocks.length)];
				int quantity = (int)(Math.random() * 301) + 100; //number between 100 and 400
				TradeType type = ( (int)(Math.random() * 2) == 0 ) ? TradeType.BUY : TradeType.SELL;
				double price = (Math.random() * 10) + 10; //number between 10 and 20

				l.addFirst( new Trade( ts + (timespent - twentyminutes) * 1000,
						stocksymbol,
						quantity,
						type,
						price ) );
			} else
				done = true;
		}
	}
}
