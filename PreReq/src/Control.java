import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Control {
	private Concentration concentration;
	private BorderPane main;
	private Pane centerPane;
	private RadioButton _radioButton1;
	private RadioButton _radioButton2;
	private RadioButton _radioButton3;
	private RadioButton _radioButton4;
	private RadioButton _radioButton5;

	public Control() {
		centerPane = new Pane();
		main = new BorderPane();
		main.setCenter(centerPane);
		setUpQuestionOne();
	}

	public Pane getPane() {
		return main;

	}

	public void setUpQuestionOne() {
		final ToggleGroup Classes = new ToggleGroup();
		_radioButton1 = new RadioButton();
		_radioButton2 = new RadioButton();
		_radioButton3 = new RadioButton();
		_radioButton4 = new RadioButton();
		_radioButton5 = new RadioButton();
		_radioButton1.setToggleGroup(Classes);
		_radioButton2.setToggleGroup(Classes);
		_radioButton3.setToggleGroup(Classes);
		_radioButton4.setToggleGroup(Classes);
		_radioButton5.setToggleGroup(Classes);

		HBox Organizer = new HBox(50);
		Organizer.setLayoutY(150);
		VBox APMABox = new VBox(20);
		APMABox.setPrefWidth(Constants.VBOX_WIDTH);
		VBox CSCIBox = new VBox(20);
		CSCIBox.setPrefWidth(Constants.VBOX_WIDTH);
		VBox ENGNBox = new VBox(20);
		ENGNBox.setPrefWidth(Constants.VBOX_WIDTH);
		VBox MATHBox = new VBox(20);
		MATHBox.setPrefWidth(Constants.VBOX_WIDTH);
		VBox BIOLBox = new VBox(20);
		BIOLBox.setPrefWidth(Constants.VBOX_WIDTH);
		Label APMALabel = new Label("APMA");
		Label CSCILabel = new Label("CompSci");
		Label ENGNLabel = new Label("Engineering");
		Label MATHLabel = new Label("Math");
		Label BIOLLabel = new Label("Bio");

		Button button1 = new Button("Confirm");
		button1.setOnAction(new clickHandlerOne());

		APMABox.getChildren().addAll(_radioButton1, APMALabel);
		CSCIBox.getChildren().addAll(_radioButton2, CSCILabel);
		ENGNBox.getChildren().addAll(_radioButton3, ENGNLabel, button1);
		MATHBox.getChildren().addAll(_radioButton4, MATHLabel);
		BIOLBox.getChildren().addAll(_radioButton5, BIOLLabel);

		Organizer.getChildren().addAll(APMABox, CSCIBox, ENGNBox, MATHBox, BIOLBox);
		centerPane.getChildren().add(Organizer);

	}

	private class clickHandlerOne implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent e) {
			if(_radioButton1.isSelected()) {
				concentration = new CSConcentration();
				
			}
			centerPane.getChildren().removeAll();
			
			

		}
	}
}
  
