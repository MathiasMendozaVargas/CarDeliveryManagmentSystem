package login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    LoginModel loginModel = new LoginModel();

    @FXML
    private Label dbStatus;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label loginStatus;

    @FXML
    private Button loginBtn;

    @FXML
    private Button signUpBtn;

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        if (loginModel.isDatabaseConnected()) {
            this.dbStatus.setText("Connected");
        } else {
            this.dbStatus.setText("Not Connected");
        }
    }

    @FXML
    public void Login(ActionEvent event) {
        if (this.loginModel.isLogin(this.username.getText(), this.password.getText())) {
            Stage stage = (Stage) this.loginBtn.getScene().getWindow();
            stage.close();

            homePage();
        } else {
            this.loginStatus.setText("Wrong Credentials!");
        }
    }

    @FXML
    public void SignUp(ActionEvent event) {
        this.loginModel.createAccount(this.username.getText(), this.password.getText());

        Stage currentStage = (Stage) this.loginBtn.getScene().getWindow();
        currentStage.close();

        homePage();
    }

    @FXML
    public void homePage() {
        Stage homeStage = new Stage();
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/home/home.fxml")));
            homeStage.setScene(scene);
            homeStage.setTitle("Car Deliveries");
            homeStage.setResizable(false);
            homeStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
