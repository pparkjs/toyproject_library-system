package main;

import controller.FrontController;

public class Application {

	public static void main(String[] args) throws Exception {
		FrontController frontController = new FrontController();
		frontController.process();
	}

}
