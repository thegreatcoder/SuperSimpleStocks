package trade.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import trade.Trade;
import trade.TradeList;
import trade.TradeListException;
import trade.TradeType;

public class TradeListTests
{

	@Test
	public void testOneTradeGetStockPrice() throws TradeListException
	{
		long ts = System.currentTimeMillis();

		TradeList tl = new TradeList();
		tl.addFirst( new Trade( ts-2000, "TEA", 200, TradeType.BUY, 10));

		assertEquals( 10, tl.getStockPrice("TEA"), 0.0001 );
	}

	@Test
	public void testTwoDifferentTradesGetStockPrice() throws TradeListException
	{
		long ts = System.currentTimeMillis();

		TradeList tl = new TradeList();
		tl.addFirst( new Trade( ts-2000, "TEA", 200, TradeType.BUY, 10));
		tl.addFirst( new Trade( ts-1800, "POP", 200, TradeType.SELL, 40));

		assertEquals( 10, tl.getStockPrice("TEA"), 0.0001 );
	}

	@Test
	public void testTwoSameTradesGetStockPrice() throws TradeListException
	{
		long ts = System.currentTimeMillis();

		TradeList tl = new TradeList();
		tl.addFirst( new Trade( ts-2000, "TEA", 200, TradeType.BUY, 10));
		tl.addFirst( new Trade( ts-1800, "TEA", 200, TradeType.SELL, 40));

		assertEquals( 25, tl.getStockPrice("TEA"), 0.0001 );
	}

	@Test
	public void testThreeSameTradesGetStockPrice() throws TradeListException
	{
		long ts = System.currentTimeMillis();

		TradeList tl = new TradeList();
		tl.addFirst( new Trade( ts-2000, "TEA", 200, TradeType.BUY, 20));
		tl.addFirst( new Trade( ts-1800, "TEA", 100, TradeType.SELL, 30));
		tl.addFirst( new Trade( ts-1700, "TEA", 200, TradeType.BUY, 15));

		assertEquals( 20, tl.getStockPrice("TEA"), 0.0001 );
	}


	@Test
	public void testOneTradeGetGBCE() throws TradeListException
	{
		long ts = System.currentTimeMillis();

		TradeList tl = new TradeList();
		tl.addFirst( new Trade( ts-2000, "TEA", 200, TradeType.BUY, 10));

		assertEquals( 10, tl.getGBCE(), 0.0001 );
	}

	@Test
	public void testTwoTradesGetGBCE() throws TradeListException
	{
		long ts = System.currentTimeMillis();

		TradeList tl = new TradeList();
		tl.addFirst( new Trade( ts-2000, "TEA", 200, TradeType.BUY, 10));
		tl.addFirst( new Trade( ts-1800, "POP", 200, TradeType.SELL, 40));

		assertEquals( 20, tl.getGBCE(), 0.0001 );
	}

	@Test
	public void testThreeTradesGetGBCE() throws TradeListException
	{
		long ts = System.currentTimeMillis();

		TradeList tl = new TradeList();
		tl.addFirst( new Trade( ts-2000, "TEA", 200, TradeType.BUY, 18));
		tl.addFirst( new Trade( ts-1700, "POP", 200, TradeType.SELL, 27));
		tl.addFirst( new Trade( ts-1500, "GIN", 200, TradeType.SELL, 96));

		assertEquals( 36, tl.getGBCE(), 0.0001 );
	}

}
