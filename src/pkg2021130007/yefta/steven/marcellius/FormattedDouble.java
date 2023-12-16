/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2021130007.yefta.steven.marcellius;


import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;


/**
 *
 * @author ASUS
 */
public class FormattedDouble <EntityType> implements Callback<TableColumn.CellDataFeatures<EntityType, String>, ObservableValue<String>> {
	private String getterName;
	private NumberFormat formatter;

	public FormattedDouble(String fieldName, String DoubleFormatPattern) {
		this.getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
		this.formatter = new DecimalFormat(DoubleFormatPattern);
	}

	public ObservableValue<String> call(CellDataFeatures<EntityType, String> features) {
		try {
			EntityType entity = features.getValue();
			Method m = entity.getClass().getMethod(getterName);
			Double dt = (Double) m.invoke(entity);
			String formattedDouble = formatter.format(dt);
			return new SimpleObservableValue<String>(formattedDouble);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
