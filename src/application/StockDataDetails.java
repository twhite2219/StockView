package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StockDataDetails {
	
	private Date date;// The date for the stock market data
	private Double open;// The opening price of the stock on the given date
	private Double high;// The highest price of the stock on the given date
	private Double low;// The lowest price of the stock on the given date
	private Double close;// The closing price of the stock on the given date
	private Double volume;// The volume of the stock traded on the given date
	private Double adjClose;// The stock's closing price on the given day, amended to include any
					   //	distributions and corporate actions that occurred at any time prior
					   //	to the next day's open.
	
	public StockDataDetails(String date, Double open,
			Double high, Double low, Double close,
			Double volume, Double adjClose) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.date = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.adjClose = adjClose;
	}
	
	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);
	}	
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getOpen() {
		return open;
	}
	public void setOpen(Double open) {
		this.open = open;
	}
	public Double getHigh() {
		return high;
	}
	public void setHigh(Double high) {
		this.high = high;
	}
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
	}
	public Double getClose() {
		return close;
	}
	public void setClose(Double close) {
		this.close = close;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public Double getAdjClose() {
		return adjClose;
	}
	public void setAdjClose(Double adjClose) {
		this.adjClose = adjClose;
	}
}
