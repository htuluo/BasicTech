package Adapter;

public class Adapter extends Adaptee implements Target {

	@Override
	public void request() {
		this.sendRequest();
		// System.out.println("Adapter.request ssss");

	}

}
