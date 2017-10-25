import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class VMSim {

//	public VMSim(int numPages, int numFrames, int numPageReqs) {
//		// TODO Auto-generated constructor stub
//	}

	public static void main(String[] args) {
//		String filename = "input.txt";
		String filename = args[0];
		int numPages = 0, numFrames = 0, numPageReqs = 0;
		ArrayList<Integer> referenceStr = new ArrayList<Integer>();
		String s = null;
		try {
			BufferedReader input = new BufferedReader(new FileReader(filename));
			s = input.readLine();
			StringTokenizer st = new StringTokenizer(s);
			numPages = Integer.parseInt(st.nextToken());
			numFrames = Integer.parseInt(st.nextToken());
			numPageReqs = Integer.parseInt(st.nextToken());
//			System.out.println(numPages + "\t" + numFrames + "\t" + numPageReqs);
			while ((s = input.readLine()) != null) {
				st = new StringTokenizer(s);
//				referenceStr.add(Integer.parseInt(st.nextToken()));
				int temp = Integer.parseInt(st.nextToken());
				referenceStr.add(temp);
//				System.out.println("st = " + st + "\ttemp = " + temp);
			} 
		} catch (FileNotFoundException fnfe) {
		} catch (IOException ioe) {
		}
		try {
			PrintStream out = new PrintStream(new FileOutputStream("output.txt", true));
				System.setOut(out);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		FIFO(referenceStr, numPages, numFrames, numPageReqs);
		Optimal(referenceStr, numPages, numFrames, numPageReqs);
		LRU(referenceStr, numPages, numFrames, numPageReqs);
	}

	public static void FIFO(ArrayList<Integer> referenceStr, int numPages, int numFrames, int numPageReqs) {
		ArrayList<Integer> frames = new ArrayList<Integer>();
		Queue<Integer> q = new LinkedList<Integer>();
		int numPageFaults = 0;
		int numAvailableFrames = numFrames;
		int victimFrame = 0;
		System.out.println("FIFO");
		for (int i = 0; i < numFrames; i++) {
			frames.add(i, -1);
		}
		for (int i = 0; i < numPageReqs; i++) {
//			int currentPage = Integer.parseInt(Character.toString(referenceStr.charAt(i)));
			int currentPage = referenceStr.get(i);
			
//			System.out.println("Page " + currentPage);
//			System.out.println("queue peek " + q.peek());
			if (frames.contains(currentPage)) {
				System.out.println("Page " + currentPage + " already in Frame " + frames.indexOf(currentPage));
			}
			else if (numAvailableFrames > 0) {
				victimFrame = frames.indexOf(-1);
//				System.out.println("victimFrame " + victimFrame);
				frames.set(victimFrame, currentPage);
				numPageFaults++;
				numAvailableFrames--;
				System.out.println("Page " + currentPage + " loaded into Frame " + victimFrame);
				q.add(currentPage);
			} else {
				victimFrame = frames.indexOf(q.remove());
//				System.out.println("victimFrame " + victimFrame);
				System.out.print("Page " + frames.get(victimFrame) + " unloaded from Frame " + victimFrame + ", ");
				frames.set(victimFrame, currentPage);
				numPageFaults++;
				q.add(currentPage);
				System.out.println("Page " + currentPage + " loaded into Frame " + frames.indexOf(currentPage));
			}
//			for (int j = 0; j < numFrames; j++) {
//				System.out.println(frames.get(j) + " " + j);
//			}
		}
		System.out.println(numPageFaults + " page faults\n");
	}
	
	public static void LRU(ArrayList<Integer> referenceStr, int numPages, int numFrames, int numPageReqs) {
		ArrayList<Integer> frames = new ArrayList<Integer>();
		ArrayList<Integer> lastUsedIndex = new ArrayList<Integer>();
		int numPageFaults = 0;
		int numAvailableFrames = numFrames;
		int victimFrame = 0;
		int leastRUIndex = numPageReqs;
		System.out.println("LRU");
		for (int i = 0; i < numFrames; i++) {
			frames.add(i, -1);
			lastUsedIndex.add(i, numPageReqs-1);
		}
		for (int i = 0; i < numPageReqs; i++) {
//			int currentPage = Integer.parseInt(Character.toString(referenceStr.charAt(i)));
			int currentPage = referenceStr.get(i);
			
//			System.out.println("Page " + currentPage);
//			System.out.println("queue peek " + q.peek());
			if (frames.contains(currentPage)) {
				System.out.println("Page " + currentPage + " already in Frame " + frames.indexOf(currentPage));
			}
			else if (numAvailableFrames > 0) {
				victimFrame = frames.indexOf(-1);
//				System.out.println("victimFrame " + victimFrame);
				frames.set(victimFrame, currentPage);
				numPageFaults++;
				numAvailableFrames--;
				System.out.println("Page " + currentPage + " loaded into Frame " + victimFrame);
			} else {
//				Find the least recently used frame index
				leastRUIndex = numPageReqs;
				for (int j = 0; j < numFrames; j++) {
					for (int k = i; k >= 0; k--) {
						if (frames.get(j) == referenceStr.get(k)) {
							lastUsedIndex.set(j, k); 
							break;
						}
					}
				}
//				Find the least recently used frame itself
				for (int j = 0; j < numFrames; j++) {
//					System.out.println("lastUsedIndex " + j + " = " + lastUsedIndex.get(j)); 
//					System.out.println("leastRUIndex = " + leastRUIndex); 
					if (lastUsedIndex.get(j) < leastRUIndex) {
						leastRUIndex = lastUsedIndex.get(j);
					}
				}
//				System.out.println("leastRUIndex = " + leastRUIndex); 
				victimFrame = frames.indexOf(referenceStr.get(leastRUIndex));
//				System.out.println("victimFrame " + victimFrame);
				System.out.print("Page " + frames.get(victimFrame) + " unloaded from Frame " + victimFrame + ", ");
				frames.set(victimFrame, currentPage);
				numPageFaults++;
				System.out.println("Page " + currentPage + " loaded into Frame " + frames.indexOf(currentPage));
			}
//			for (int j = 0; j < numFrames; j++) {
//				System.out.println(frames.get(j) + " " + j);
//			}
		}
		System.out.println(numPageFaults + " page faults\n");
	}
	
	public static void Optimal(ArrayList<Integer> referenceStr, int numPages, int numFrames, int numPageReqs) {
		ArrayList<Integer> frames = new ArrayList<Integer>();
		ArrayList<Integer> futureUseIndex = new ArrayList<Integer>();
		final int INFINITY = 9000;
		int numPageFaults = 0;
		int numAvailableFrames = numFrames;
		int victimFrame = 0;
		int leastFutureUseIndex = numPageReqs;
		System.out.println("Optimal");
		for (int i = 0; i < numFrames; i++) {
			frames.add(i, -1);
			futureUseIndex.add(i, 0);
		}
		for (int i = 0; i < numPageReqs; i++) {
//			int currentPage = Integer.parseInt(Character.toString(referenceStr.charAt(i)));
			int currentPage = referenceStr.get(i);
			
//			System.out.println("Page " + currentPage);
//			System.out.println("queue peek " + q.peek());
			if (frames.contains(currentPage)) {
				System.out.println("Page " + currentPage + " already in Frame " + frames.indexOf(currentPage));
			}
			else if (numAvailableFrames > 0) {
				victimFrame = frames.indexOf(-1);
//				System.out.println("victimFrame " + victimFrame);
				frames.set(victimFrame, currentPage);
				numPageFaults++;
				numAvailableFrames--;
				System.out.println("Page " + currentPage + " loaded into Frame " + victimFrame);
			} else {
//				Find the least recently used frame index
				
				for (int j = 0; j < numFrames; j++) {
					for (int k = i; k < numPageReqs; k++) {
						if (frames.get(j) == referenceStr.get(k)) {
							futureUseIndex.set(j, k); 
							break;
						} else {
							futureUseIndex.set(j, INFINITY);
						}
					}
				}
//				Find the least recently used frame itself
				leastFutureUseIndex = 0;
				for (int j = 0; j < numFrames; j++) {
//					System.out.println("lastUsedIndex " + j + " = " + futureUseIndex.get(j)); 
//					System.out.println("leastFutureUseIndex = " + leastFutureUseIndex); 
					if (futureUseIndex.get(j) == INFINITY) {
						victimFrame = j;
						leastFutureUseIndex = INFINITY;
						break;
					} else if (futureUseIndex.get(j) > leastFutureUseIndex) {
						leastFutureUseIndex = futureUseIndex.get(j);
					}
				}
//				System.out.println("leastFutureUseIndex = " + leastFutureUseIndex); 
				if (leastFutureUseIndex != INFINITY) {
					victimFrame = frames.indexOf(referenceStr.get(leastFutureUseIndex));
				}
//				System.out.println("victimFrame " + victimFrame);
				System.out.print("Page " + frames.get(victimFrame) + " unloaded from Frame " + victimFrame + ", ");
				frames.set(victimFrame, currentPage);
				numPageFaults++;
				System.out.println("Page " + currentPage + " loaded into Frame " + frames.indexOf(currentPage));
			}
//			This sets up the page table with the corresponding frame numbers
//			for (int j = 0; j < numFrames; j++) {
//				System.out.println(frames.get(j) + " " + j);
//			}
		}
		System.out.println(numPageFaults + " page faults\n");
	}
}
