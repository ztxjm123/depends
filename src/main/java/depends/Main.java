package depends;

import static java.lang.System.exit;

import depends.extractor.AbstractLangWorker;
import depends.extractor.LangWorkers;
import depends.extractor.java.JavaWorker;

public class Main {

	/**
	 * python /home/gangz/work/deper/python-fire-master python-fire-test-sep14
	 * python-fire-test-sep14 111111111 java /home/gangz/work/deper/src/main/java
	 * java-test java-test 111111111
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		// System.out.println("\ninput parameters:" + "srcDir usageDir projectName
		// deps=[111111111]");
		if (args.length < 4) {
			System.out.println("Not enough parameters!");
			exit(1);
		}
		
		new JavaWorker().register();
		
		String lang = args[0];
        String inputDir = args[1];
        String usageDir = args[2];
        String projectName = args[3];
        String depMask = args[4];
		AbstractLangWorker worker = LangWorkers.getRegistry().getWorkerOf(lang);
		
		if (worker == null){
			System.out.println("Not support this language: " + args[0]);
			exit(1);
		}

		long startTime = System.currentTimeMillis();
		worker.work(lang,inputDir,usageDir,projectName,depMask);
		long endTime = System.currentTimeMillis();
		System.out.println("Consumed time: " + (float) ((endTime - startTime) / 1000.00) + " s,  or "
				+ (float) ((endTime - startTime) / 60000.00) + " min.");

	}

}