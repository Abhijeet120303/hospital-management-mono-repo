package hospital_management_main;

import common.StageFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import user_login.UserLogin;

public class ApplicationMain extends Application {

  public static void main(String args[]) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {

    StageFactory.stage = stage;
    new UserLogin().show();
  }
}
