package basicTech;

public class ReferenceCountingGc {
	private Object instace = null;
	private static final int _1MB = 1024 * 1024;
	private byte[] bigSize = new byte[2 * _1MB];

	public static void main(String[] args) {
		ReferenceCountingGc refA = new ReferenceCountingGc();
		ReferenceCountingGc refB = new ReferenceCountingGc();
		refA.instace = refB;
		refB.instace = refA;
		refA = null;
		refB = null;
		System.gc();
		// TODO Auto-generated method stub

	}

}
