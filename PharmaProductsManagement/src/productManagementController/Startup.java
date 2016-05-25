package productManagementController;

public class Startup {
	public Startup(ProductManagementFrameController mainApk){
		new LoginController(mainApk.getFrame());
	}
}
