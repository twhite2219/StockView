package application;

public class StockData {
	
	private String stockSymbol;
	private String companyName;
	private String latestSharePrice;
	private String csvFile;
	
	public StockData(String stockSymbol, String companyName,
			String latestSharePrice, String csvFile) {
		this.stockSymbol = stockSymbol;
		this.companyName = companyName;
		this.latestSharePrice = latestSharePrice;
		this.csvFile = csvFile;
	}
	
	public String getCsvFile() {
		return csvFile;
	}
	public void setCsvFile(String csvFile) {
		this.csvFile = csvFile;
	}
	public String getStockSymbol() {
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLatestSharePrice() {
		return latestSharePrice;
	}
	public void setLatestSharePrice(String latestSharePrice) {
		this.latestSharePrice = latestSharePrice;
	}
}
