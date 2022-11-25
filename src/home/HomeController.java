package home;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeController implements Initializable {

    @FXML
    private TextField model;
    @FXML
    private TextField brand;
    @FXML
    private TextField color;
    @FXML
    private TextField city;
    @FXML
    private TextField country;
    @FXML
    private DatePicker date;
    @FXML
    private TextField buyerName;

    @FXML
    private Button addCarBtn;
    @FXML
    private Button updateCarBtn;
    @FXML
    private Button deleteCarBtn;
    @FXML
    private Button clearFormBtn;

    @FXML
    private TableView<DeliveriesData> deliveriesDataTableView;
    @FXML
    private TableColumn<DeliveriesData, String> idColumn;
    @FXML
    private TableColumn<DeliveriesData, String> modelColumn;
    @FXML
    private TableColumn<DeliveriesData, String> brandColumn;
    @FXML
    private TableColumn<DeliveriesData, String> colorColumn;
    @FXML
    private TableColumn<DeliveriesData, String> cityColumn;
    @FXML
    private TableColumn<DeliveriesData, String> countryColumn;
    @FXML
    private TableColumn<DeliveriesData, String> dateColumn;
    @FXML
    private TableColumn<DeliveriesData, String> buyerNameColumn;

    // instantiate a model
    HomeModel homeModel = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.homeModel = new HomeModel();
        this.loadDeliveriesData();

        // disable delete and edit buttons
        updateCarBtn.setDisable(true);
        deleteCarBtn.setDisable(true);

        deliveriesDataTableView.setOnMouseClicked(e -> {
            DeliveriesData selected = deliveriesDataTableView.getSelectionModel().getSelectedItem();

            if (selected != null) {
                updateCarBtn.setDisable(false);
                deleteCarBtn.setDisable(false);

                model.setText(selected.getModel());
                brand.setText(selected.getBrand());
                color.setText(selected.getColor());
                city.setText(selected.getCity());
                country.setText(selected.getCountry());
                buyerName.setText(selected.getBuyerName());
            }
        });
    }

    // load Data
    @FXML
    public void loadDeliveriesData() {

        this.idColumn.setCellValueFactory(new PropertyValueFactory<DeliveriesData, String>("id"));
        this.modelColumn.setCellValueFactory(new PropertyValueFactory<DeliveriesData, String>("model"));
        this.brandColumn.setCellValueFactory(new PropertyValueFactory<DeliveriesData, String>("brand"));
        this.colorColumn.setCellValueFactory(new PropertyValueFactory<DeliveriesData, String>("color"));
        this.cityColumn.setCellValueFactory(new PropertyValueFactory<DeliveriesData, String>("city"));
        this.countryColumn.setCellValueFactory(new PropertyValueFactory<DeliveriesData, String>("country"));
        this.dateColumn.setCellValueFactory(new PropertyValueFactory<DeliveriesData, String>("date"));
        this.buyerNameColumn.setCellValueFactory(new PropertyValueFactory<DeliveriesData, String>("buyerName"));

        this.deliveriesDataTableView.setItems(homeModel.getDeliveries());
    }

    // Add Deliverie
    @FXML
    private void addDelivery(ActionEvent event) {
        homeModel.addDelivery(this.model.getText(), this.brand.getText(), this.color.getText(), this.city.getText(),
                this.country.getText(), this.date.getValue().format(DateTimeFormatter.ofPattern("MMM-dd-YYYY")),
                this.buyerName.getText());
        this.loadDeliveriesData();
        this.clearFields(null);
    }

    // Update Deliverie
    @FXML
    private void updateDelivery(ActionEvent event) {
        DeliveriesData deliverie = deliveriesDataTableView.getSelectionModel().getSelectedItem();

        homeModel.updateDelivery(this.model.getText(), this.brand.getText(), this.color.getText(), this.city.getText(),
                this.country.getText(), this.date.getValue().format(DateTimeFormatter.ofPattern("MMM-dd-YYYY")),
                this.buyerName.getText(), deliverie.getId());
        this.loadDeliveriesData();
        this.clearFields(null);
    }

    // Delete Deliverie
    @FXML
    private void deleteDelivery(ActionEvent event) {
        DeliveriesData deliverie = deliveriesDataTableView.getSelectionModel().getSelectedItem();
        homeModel.deleteDelivery(deliverie.getId());
        this.loadDeliveriesData();
        this.clearFields(null);
    }

    // Clear Form Fields
    @FXML
    private void clearFields(ActionEvent event) {
        this.model.setText("");
        this.brand.setText("");
        this.color.setText("");
        this.city.setText("");
        this.country.setText("");
        this.date.getEditor().clear();
        this.buyerName.setText("");
    }

}