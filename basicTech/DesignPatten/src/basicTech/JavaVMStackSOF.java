package basicTech;

public class JavaVMStackSOF {
	private int stackLength = 1;

	public void stackLeak() {
		this.stackLength++;
		stackLeak();
	}

	public static void main(String[] args) {
		JavaVMStackSOF jsJavaVMStackSOF = new JavaVMStackSOF();
		try {
			jsJavaVMStackSOF.stackLeak();

		} catch (Exception e) {
			System.out.println("stack length:" + jsJavaVMStackSOF.stackLength);
			e.printStackTrace();
			// TODO: handle exception
		}
		// TODO Auto-generated method stub

	}

}
