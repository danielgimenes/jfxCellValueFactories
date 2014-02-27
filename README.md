jfxCellValueFactories
=====================

Classes for easily format data on JavaFX's TableView component.

Example of using FormattedDateValueFactory:

```java
public class MyEntity {
	private Date date;

	public Date getDate() {
		return this.date;
	}
}

public class MyController {
	@FXML
	private TableView<MyEntity> tableView;

	@FXML
	private TableColumn<MyEntity, String> dateColumn;

	/* ... */

	private void configureTableView() {
		dateColumn.setCellValueFactory(new FormattedDateValueFactory<InvoiceTableEntry>("date", "dd/MM/yyyy"));
	}

	/* ... */

	private void queryData() {
		List<MyEntity> data = myService.getData();
		tableView.setItems(FXCollections.observableList(data));
	}
}
```
