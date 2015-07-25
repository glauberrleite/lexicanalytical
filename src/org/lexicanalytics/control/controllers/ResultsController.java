package org.lexicanalytics.control.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;

import org.lexicanalytics.model.BaseController;
import org.lexicanalytics.model.BaseFrame;
import org.lexicanalytics.model.ResultsType;
import org.lexicanalytics.view.ResultsGeneralFrame;
import org.lexicanalytics.view.ResultsTTRFrame;

/**
 * 
 * @author glauberrleite
 *
 */

public class ResultsController extends BaseController implements Initializable {

	@FXML
	public ComboBox<String> resultsComboBox;

	@FXML
	public SplitPane splitPane;
	
	private BaseFrame general, ttr;
	
	private class ComboBoxListener implements ChangeListener<String> {	

		@Override
		public void changed(ObservableValue<? extends String> observable,
				String oldValue, String newValue) {
			
			if (newValue.equals("General")) {
				splitPane.getItems().set(1, general.getAnchorPane());
			} else if (newValue.equals("TTR")) {
				splitPane.getItems().set(1, ttr.getAnchorPane());
			}
			
		}
	}

	/**
	 * This method gives the Controller for a screen result, notice that you'll
	 * need to cast the controller for specific operation, e.g.
	 * (ResultsTTRController) getResultsTypeController(ResultsType.TTR);
	 * 
	 * @param type
	 *            A ResultsType enum object representing the type of result
	 * @return The controller for a resultType
	 */
	public BaseController getResultsTypeController(ResultsType type) {
		BaseController controller = null;

		switch (type) {
		case GENERAL:
			controller = general.getController();
			break;
		case TTR:
			controller = ttr.getController();
		}

		return controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Fill ComboBox
		ObservableList<String> options = FXCollections.observableArrayList(
				"General", "TTR");

		resultsComboBox.setItems(options);

		// Instantiate frames
		ttr = new ResultsTTRFrame();
		general = new ResultsGeneralFrame();
		
		// Listener to the ComboBox
		resultsComboBox.valueProperty().addListener(new ComboBoxListener());
		resultsComboBox.setValue("General");


	}
	

	
}
