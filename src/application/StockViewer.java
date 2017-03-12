package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class StockViewer extends Application{

	private final double sceneWidth = 535;
	private final double sceneHeight = 635;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(getGUI(), sceneWidth, sceneHeight);
		
		primaryStage.setTitle("Simple Stock Viewer");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private Parent getGUI() {
		AnchorPane pane = new AnchorPane();
		
		TableView<StockData> tab = new TableView<>(getItems());
		tab.setLayoutX(20);
		tab.setLayoutY(20);
		
		tab.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		        StockViewerDetails stokDetail = new StockViewerDetails(newSelection, tab.getSelectionModel().getSelectedIndex());
		        stokDetail.initOwner(null);
		        stokDetail.initModality(Modality.APPLICATION_MODAL); 
		        stokDetail.showAndWait();
		    }
		});
		
		TableColumn symbolCol = new TableColumn("SYMBOL");
		symbolCol.setCellValueFactory(new PropertyValueFactory("stockSymbol"));
		symbolCol.setCellFactory(getCellFactory());
		TableColumn nameCol = new TableColumn("Company Name");
		nameCol.setCellValueFactory(new PropertyValueFactory("companyName"));
		TableColumn fileCol = new TableColumn("Latest Share Price");
		fileCol.setCellValueFactory(new PropertyValueFactory("latestSharePrice"));
		fileCol.setCellFactory(getCellFactory());
		tab.getColumns().setAll(symbolCol, nameCol, fileCol);
		tab.setPrefWidth(500);
		tab.setPrefHeight(600);
		tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		pane.getChildren().add(tab);
		return pane;
	}

	private Callback<TableColumn, TableCell> getCellFactory() {
		return new Callback<TableColumn, TableCell>() {
	        public TableCell call(TableColumn param) {
	            return new TableCell<StockData, String>() {

	                @Override
	                public void updateItem(String item, boolean empty) {
	                    super.updateItem(item, empty);
	                    if (!isEmpty()) {
	                    	setFont(new Font(14));
	                        this.setTextFill(Color.RED);
	                        if(item.contains(".csv")) 
	                            this.setTextFill(Color.DEEPSKYBLUE);
	                        setText(item);
	                    }
	                }
	            };
	        }
	    };
	}

	private ObservableList<StockData> getItems() {
		List<StockData> data = new ArrayList<>();
		data.add(new StockData("AHT.L", "Ashtead Group plc", getValue("AHT.csv"), "AHT.csv"));
		data.add(new StockData("ANTO.L", "Antofagasta plc", getValue("ANTO.csv"), "ANTO.csv"));
		data.add(new StockData("BA.L", "BAE Systems plc", getValue("BA.csv"), "BA.csv"));
		data.add(new StockData("BATS.L", "British American Tobacco plc", getValue("BATS.csv"), "BATS.csv"));
		data.add(new StockData("CCH.L", "Coca-Cola HBC AG", getValue("CCH.csv"), "CCH.csv"));
		data.add(new StockData("CCL.L", "Carnival plc", getValue("CCL.csv"), "CCL.csv"));
		data.add(new StockData("CNA.L", "Centrica plc", getValue("CNA.csv"), "CNA.csv"));
		data.add(new StockData("CPG.L", "Compass Group plc", getValue("CPG.csv"), "CPG.csv"));
		data.add(new StockData("EXPN.L", "Experian plc", getValue("EXPN.csv"), "EXPN.csv"));
		data.add(new StockData("EZJ.L", "EasyJet plc", getValue("EZJ.csv"), "EZJ.csv"));
		data.add(new StockData("GKN.L", "GKN plc", getValue("GKN.csv"), "GKN.csv"));
		data.add(new StockData("MDC.L", "Mediclinic International plc", getValue("MDC.csv"), "MDC.csv"));
		data.add(new StockData("PFG.L", "Provident Financial plc", getValue("PFG.csv"), "PFG.csv"));
		data.add(new StockData("PPB.L", "Paddy Power Betfair plc", getValue("PPB.csv"), "PPB.csv"));
		data.add(new StockData("PRU.L", "Prudential plc", getValue("PRU.csv"), "PRU.csv"));
		
		return FXCollections.observableArrayList(data);
	}
	
	private String getValue(String csvFileName) {
		List<StockDataDetails> values = new ArrayList<>();
		try {
			File f1 = new File("src/application/"+csvFileName);
			FileReader reader = new FileReader(f1);
			BufferedReader bufferedReader = new BufferedReader(reader);
			
			String line;
			int first = 0;
            while ((line = bufferedReader.readLine()) != null) {
            	if(line == null || line.isEmpty()){
            		continue;
            	}
            	if(first == 0){ //skips the first line
            		first++;
            		continue;
            	}
            	String dt = line.split(",")[0].trim();
            	String op = line.split(",")[1].trim();
            	String hg = line.split(",")[2].trim();
            	String lw = line.split(",")[3].trim();
            	String cl = line.split(",")[4].trim();
            	String vl = line.split(",")[5].trim();
            	String ac = line.split(",")[6].trim();
            	
            	values.add(new StockDataDetails(dt, doubleVal(op), doubleVal(hg), doubleVal(lw), doubleVal(cl), doubleVal(vl), doubleVal(ac)));
            }            
            reader.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Collections.sort(values, new Comparator<StockDataDetails>() {
			  public int compare(StockDataDetails o1, StockDataDetails o2) {
			      return o2.getDate().compareTo(o1.getDate());
			  }
			});
		return values.get(0).getAdjClose().toString();
	}

	private Double doubleVal(String val) {
		return Double.parseDouble(val);
	}

	public static void main(String[] args) {
		launch(args);
	}

}

