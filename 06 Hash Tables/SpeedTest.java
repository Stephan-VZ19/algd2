import java.util.Locale;

/*
 * Created on 13.05.2016
 */
/**
 * @author Wolfgang Weck
 */
public class SpeedTest {
	private static int size = 0X80000, step = 123;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		write("f1:", runtimeOf(SpeedTest::f1));
		write("f2:", runtimeOf(SpeedTest::f2));
		write("f3:", runtimeOf(SpeedTest::f3));
		write("f4:", runtimeOf(SpeedTest::f4));
	}

	private static void write(String name, int time) {
		System.out.printf(Locale.forLanguageTag("de-ch"), "%s %,14d%n", name, time);
	}

	private static int runtimeOf(Runnable r) {
		runIt(r);
		long t = System.nanoTime();
		runIt(r);
		t = System.nanoTime() - t;
		return (int)t;
	}

	private static void runIt(Runnable r) {
		for (int i = 0; i < 10; i++) {
			for (size = 8; size <= 0x80_0000; size = size * 2) {
				step = size / 4 - 1;
				r.run();
			}
		}
	}

	private static void f1() {
		int i0 = size / 2, i = i0 + step;
		while (i != i0)
			i = (i + step) % size;
	}

	private static void f2() {
		int i0 = size / 2, i = i0 + step, mask = size - 1;
		while (i != i0)
			i = (i + step) & mask;
	}

	private static void f3() {
		int i0 = size / 2, i = i0 + step;
		while (i != i0) {
			i = i + step;
			if (i >= size) i -= size;
		}
	}

	private static void f4() {
		int i0 = size / 2, i = i0 - step;
		while (i != i0) {
			i = i - step;
			if (i < 0) i += size;
		}
	}
}
