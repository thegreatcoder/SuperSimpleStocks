package trade;

public class TradeListException extends Exception
{
	public TradeListException()
	{
		super();
	}

	public TradeListException(String message)
	{
		super(message);
	}

	public TradeListException(Throwable cause)
	{
		super(cause);
	}

	public TradeListException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public TradeListException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
