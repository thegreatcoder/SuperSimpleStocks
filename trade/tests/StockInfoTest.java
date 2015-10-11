package trade.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import trade.CommonStockInfo;
import trade.PreferredStockInfo;
import trade.StockInfo;

public class StockInfoTest
{

	@Test
	public void testCommonStockGetDividendYield()
	{
		StockInfo s = new CommonStockInfo(8, 100);

		assertEquals( 0.5, s.computeDividendYield(16), 0.0001 );
	}

	@Test
	public void testPreferredStockGetDividendYield()
	{
		StockInfo s = new PreferredStockInfo(8, 0.08, 100);

		assertEquals( 0.5, s.computeDividendYield(16), 0.0001 );
	}

	@Test
	public void testGetPERatio()
	{
		StockInfo s = new CommonStockInfo(8, 100);

		assertEquals( 2, s.computePERatio(16), 0.0001 );
	}

}
