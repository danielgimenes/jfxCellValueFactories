package br.com.dgimenes.jfxCellValueFactories.factories;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import br.com.dgimenes.jfxCellValueFactories.util.SimpleObservableValue;

public class FormattedBigDecimalValueFactory<EntityType> implements Callback<TableColumn.CellDataFeatures<EntityType, String>, ObservableValue<String>> {
	private String getterName;
	private NumberFormat formatter;

	public FormattedBigDecimalValueFactory(String fieldName, String bigDecimalFormatPattern) {
		this.getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
		this.formatter = new DecimalFormat(bigDecimalFormatPattern);
	}

	public ObservableValue<String> call(CellDataFeatures<EntityType, String> features) {
		try {
			EntityType entity = features.getValue();
			Method m = entity.getClass().getMethod(getterName);
			BigDecimal bigDecimal = (BigDecimal) m.invoke(entity);
			String formattedBigDecimal = formatter.format(bigDecimal);
			return new SimpleObservableValue<String>(formattedBigDecimal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
