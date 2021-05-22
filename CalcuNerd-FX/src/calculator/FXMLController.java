package calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLController {
	private Stage meineStage;
	@FXML private Button eins;
	@FXML private Button zwei;
	@FXML private Button drei;
	@FXML private Button vier;
	@FXML private Button fuenf;
	@FXML private Button sechs;
	@FXML private Button sieben;
	@FXML private Button acht;
	@FXML private Button neun;
	@FXML private Button zero;
	@FXML private Button plus;
	@FXML private Button minus;
	@FXML private Button mal;
	@FXML private Button geteilt;
	@FXML private Button punkt;
	@FXML private Button resultat;
	@FXML private Button reset;
	@FXML private TextField anzeige;
	@FXML private SplitPane window;
	private boolean validDouble;
	private String zahlenPuffer=null;
	private char operanti;
	private int zahl1, zahl2, ergebnis;
	private double zahlx1, zahlx2, ergebnisx;
	private ActionEvent event;
	
	//Übergibt Bühne
	public void setMeineStage(Stage meineStage) {
		this.meineStage = meineStage;
	}
	//Werkseinstellungen
	@FXML private void setReset(ActionEvent event) {
		zahl1=0;
		zahl2=0;
		ergebnis=0;
		operanti=0;
		validDouble=false;
		zahlenPuffer=null;
		anzeige.setDisable(false);
		window.setDisable(false);
		anzeige.setText("0");
	}
	
	//Zifferblockoperanten und vorrübergehende Anzeige setzen
	@FXML private void getZahlen(ActionEvent event) {	
		
		String wert = null;

		if (event.getSource().toString().contains("eins"))
			wert="1";
		if (event.getSource().toString().contains("zwei"))
			wert="2";
		if (event.getSource().toString().contains("drei"))
			wert="3";
		if (event.getSource().toString().contains("vier"))
			wert="4";
		if (event.getSource().toString().contains("fuenf"))
			wert="5";
		if (event.getSource().toString().contains("sechs"))
			wert="6";
		if (event.getSource().toString().contains("sieben"))
			wert="7";
		if (event.getSource().toString().contains("acht"))
			wert="8";
		if (event.getSource().toString().contains("neun"))
			wert="9";
		if (event.getSource().toString().contains("zero"))
			wert="0";
		if (event.getSource().toString().contains("punkt")) {
			wert=".";
			validDouble=true;
		}
		if (zahlenPuffer == null)
			zahlenPuffer = wert;
		else
			zahlenPuffer = zahlenPuffer + wert;
			anzeige.setText(zahlenPuffer);
		}
	
	//Rechenartoperanten und Anzeige setzen
	@FXML private void getRechenart(ActionEvent event) {
		
		zahlenPuffer = null;
		if(validDouble == true)
			zahlx1= Double.parseDouble(anzeige.getText());
		else
			zahl1= Integer.parseInt(anzeige.getText());
		anzeige.setText("0");
		
		if (event.getSource().toString().contains("plus"))
			operanti='+';
		if (event.getSource().toString().contains("minus"))
			operanti='-';
		if (event.getSource().toString().contains("mal"))
			operanti='*';
		if (event.getSource().toString().contains("geteilt"))
			operanti='/';
			anzeige.setText("0");
	}
	
	//Rechnung durchführen und ausgeben
	@FXML private void getErgebnis(ActionEvent event) {	
		if(validDouble == true) {
			zahlx2= Double.parseDouble(anzeige.getText());
			getErgebnisDouble();
			return;
		}
		else
			zahl2= Integer.parseInt(anzeige.getText());
		
		//Reicht Division weiter an Methode
		if (operanti == '/') {
			validiereDivision(zahl1,zahl2);
			return;
		}
		if (operanti == '+');
			ergebnis = zahl1 + zahl2;
		if (operanti == '-')
			ergebnis = zahl1 - zahl2;
		if (operanti == '*')
			ergebnis = zahl1 * zahl2;
		
		anzeige.setText(Integer.toString(ergebnis));
		anzeige.setFocusTraversable(true);
	}
	
	//Rechnung durchführen und ausgeben
		private void validiereDivision(int zahl1, int zahl2) {
		//Fängt eine Division durch 0 ab !!
		try {
			if(zahl1 % zahl2 == 0) {
				ergebnis = zahl1 / zahl2;
				anzeige.setText(Integer.toString(ergebnis));
		}
			else {
				ergebnisx = (double)zahl1 / (double) zahl2;
				anzeige.setText(Double.toString(ergebnisx));
			}
			}
		catch(ArithmeticException e) {
			anzeige.setText("ERROR-> / 0");
			anzeige.setDisable(true);
			window.setDisable(true);
			System.out.println(e);
			}
		}
		
		
	private void getErgebnisDouble() {
		//Reicht Division weiter an Methode
				if (operanti == '/') {
					validiereDivisionDouble(zahlx1,zahlx2);
					return;
				}
				if (operanti == '+');
					ergebnisx = zahlx1 + zahlx2;
				if (operanti == '-')
					ergebnisx = zahlx1 - zahlx2;
				if (operanti == '*')
					ergebnisx = zahlx1 * zahlx2;
	
				anzeige.setText(Double.toString(ergebnisx));
	}
	//Rechnung durchführen und ausgeben
	private void validiereDivisionDouble(double zahl1, double zahl2) {
		//Fängt eine Division durch 0 ab !!
		try {
			ergebnisx = zahl1 / zahl2;
				anzeige.setText(Double.toString(ergebnisx));
				if(anzeige.getText().equals("Infinity"))
					anzeige.setDisable(true);
					window.setDisable(true);
					
			}
		catch(ArithmeticException e) {
			anzeige.setText("ERROR-> / 0.0");
			anzeige.setDisable(true);
			window.setDisable(true);
			System.out.println(e);
		}
	}
}

	