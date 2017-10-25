import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CPUSchedulerSim {
	private static PrintStream out;
	private static ArrayList<Process> allProcesses = new ArrayList<Process>();
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
//		String filename = "input1.txt";
		String filename = args[0];
		String algName = null;
		int quantum = 0;
		int numProc = 0;
		
		Process proc = null;
		String s = null;
		long pid = 0, at = 0, bt = 0, pr = 0;
		System.out.println("Input");
		try {
			BufferedReader input = new BufferedReader(new FileReader(filename));
			s = input.readLine();
			StringTokenizer st = new StringTokenizer(s);
			algName = st.nextToken();
			System.out.print(algName + " ");
			if (algName.equals("RR")) {
				quantum = Integer.parseInt(st.nextToken());
				System.out.print(quantum);
			}
			System.out.println();
			s = input.readLine();
			st = new StringTokenizer(s);
			numProc = Integer.parseInt(st.nextToken());
				System.out.println(numProc);
			while ((s = input.readLine()) != null) {
				st = new StringTokenizer(s);
				pid = Long.parseLong(st.nextToken());
				at = Long.parseLong(st.nextToken());
				bt = Long.parseLong(st.nextToken());
				pr = Long.parseLong(st.nextToken());
//				print it out like the input file for debugging
				System.out.println(pid + "\t" + at + "\t" + bt + "\t" + pr + "\t");
				proc = new Process(pid, at, bt, pr);
				allProcesses.add(proc);
			}
		} catch (FileNotFoundException fnfe) {
		} catch (IOException ioe) {
		}
		try {
//				out = new PrintStream(new FileOutputStream("output.txt"));
				out = new PrintStream(new FileOutputStream("output.txt", true));
				System.setOut(out);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

//		for (Process temp : p ) 
//			System.out.println(	temp.getPID() + "\t" + temp.getArrivalTime() + "\t" + 
//								temp.getBurstTime() + "\t" + temp.getPriority());		

	CPUScheduler c = new CPUScheduler(allProcesses, numProc);
//	System.out.println("num of proc: " + numProc);
	
	if (algName.equals("SJF"))					c.SJF();
	else if (algName.equals("RR"))				c.RR(quantum);
	else if (algName.equals("PR_noPREMP"))		c.PR_noPREMP();
	else if (algName.equals("PR_withPREMP")) 	c.PR_withPREMP(); 
	
	System.out.println("\nOutput");
	System.out.print(algName);
	if (algName.equals("RR"))	System.out.print(" " + quantum);
	System.out.println();
	c.printGannt();
	System.out.println("AVG Waiting Time: " + c.getAverageWT());
	}
}
