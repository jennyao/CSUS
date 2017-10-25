import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CPUScheduler {

	final static Comparator<Process> PRIORITY_ORDER = new Comparator<Process>() {
		public int compare(Process p1, Process p2) {
			return (int) (p1.getPriority() - p2.getPriority());
		}
	};
	
	final static Comparator<Process> BURST_ORDER = new Comparator<Process>() {
		public int compare(Process p1, Process p2) {
			return (int) (p1.getBurstTime() - p2.getBurstTime());
		}
	};
	
	final static Comparator<Process> ARRIVAL_ORDER = new Comparator<Process>() {
		public int compare(Process p1, Process p2) {
			return (int) (p1.getArrivalTime() - p2.getArrivalTime());
		}
	};
	
	private ArrayList<Process> resultProcesses = new ArrayList<Process>();
	private ArrayList<Process> jobQ = new ArrayList<Process>();
	private ArrayList<Process> readyQ = new ArrayList<Process>();
	private ArrayList<String> GanntChart = new ArrayList<String>();
	private int numProc, numProcOut;
	private long currTime;
	
	public CPUScheduler() {
		
	}
	
	public CPUScheduler(ArrayList<Process> givenProcess, int numProc) {
		this.numProc = numProc;
		this.currTime = 0;
		this.loadJobQueue(givenProcess);
	}
	
	public void PR_noPREMP() {
		currTime = 0;
		Process p, activeP = null;
		numProcOut = 0;
		String temp = "";
		while (!allJobsCompleted()) {
			if (!jobQ.isEmpty()) {
				for (int i = 0; i < jobQ.size(); i++) {		
					if (jobQ.get(i).getArrivalTime() == currTime) {
						jobQ.get(i).setArrived(true);
						readyQ.add(jobQ.get(i));
//						System.out.println("getArrivalTime for job: " + i + " " + jobQ.get(i).getArrivalTime());
					}
				}
				Collections.sort(readyQ, PRIORITY_ORDER);
				for (int i = 0; i < jobQ.size(); i++) 
					if (jobQ.get(i).isArrived())  jobQ.remove(i);
//				System.out.println("jobQ.size() " + jobQ.size());		
			}

			if (!readyQ.isEmpty()) {
				if (activeP == null) {
					activeP = readyQ.get(0);
					readyQ.remove(0);
					activeP.setStartTime(currTime);
					activeP.setActive(true);
					temp = Long.toString(activeP.getStartTime()) + "\t" + Long.toString(activeP.getPID());
					GanntChart.add(temp);
				}
				if (activeP.isCompleted()) {
					resultProcesses.add(activeP);
					numProcOut++;
					Collections.sort(readyQ, PRIORITY_ORDER);
					activeP = readyQ.get(0);
					readyQ.remove(0);
					activeP.setStartTime(currTime);
					activeP.setActive(true);
					temp = Long.toString(activeP.getStartTime()) + "\t" + Long.toString(activeP.getPID());
					GanntChart.add(temp);
				} 
//				System.out.println("getCPUburst activeP: " + activeP.getBurstTime());
				activeP.executing(currTime);
//				System.out.println("getCPUburst after activeP: " + activeP.getBurstTime());
				for (int i = 0; i < readyQ.size(); i++) {
					p = (Process) readyQ.get(i);
//					System.out.println("readyQ.get(wait time): " + p.getWaitTime());
					p.waiting(currTime);
//					System.out.println("after readyQ.get(wait time): " + p.getWaitTime());
				}
			}
			else if (activeP.isCompleted()) {
				resultProcesses.add(activeP);
				numProcOut++;
//				temp = Long.toString(activeP.getStartTime()) + "\t" + Long.toString(activeP.getPID());
//				GanntChart.add(temp);
			} else {
				activeP.executing(currTime);
			}
			currTime++;
		}
	}
	
	public void PR_withPREMP() {
		currTime = 0;
		Process p, activeP = null;
		numProcOut = 0;
		String temp = "";
		while (!allJobsCompleted()) {
			if (!jobQ.isEmpty()) {
				for (int i = 0; i < jobQ.size(); i++) {		
					if (jobQ.get(i).getArrivalTime() == currTime) {
						jobQ.get(i).setArrived(true);
						readyQ.add(jobQ.get(i));
					}
				}
				Collections.sort(readyQ, PRIORITY_ORDER);
				for (int i = 0; i < jobQ.size(); i++) 
					if (jobQ.get(i).isArrived())  jobQ.remove(i);
//				System.out.println("jobQ.size() " + jobQ.size());		
			}

			if (!readyQ.isEmpty()) {
				if (activeP == null) {
					activeP = readyQ.get(0);
					readyQ.remove(0);
					activeP.setStartTime(currTime);
					activeP.setActive(true);
					temp = Long.toString(currTime) + "\t" + Long.toString(activeP.getPID());
					GanntChart.add(temp);
				}
				if (activeP.isCompleted()) {
					resultProcesses.add(activeP);
					numProcOut++;
					Collections.sort(readyQ, PRIORITY_ORDER);
					activeP = readyQ.get(0);
					readyQ.remove(0);
					activeP.setStartTime(currTime);
					activeP.setActive(true);
					temp = Long.toString(currTime) + "\t" + Long.toString(activeP.getPID());
					GanntChart.add(temp);
				} else if (!readyQ.isEmpty() && readyQ.get(0).getPriority() < activeP.getPriority()) {
					activeP.setActive(false);
					readyQ.add(activeP);
					activeP = readyQ.get(0);
					readyQ.remove(0);
					if (activeP.getStartTime() == 0)
						activeP.setStartTime(currTime);
					activeP.setActive(true);
					temp = Long.toString(currTime) + "\t" + Long.toString(activeP.getPID());
					GanntChart.add(temp);
				}
//				System.out.println("getCPUburst activeP: " + activeP.getBurstTime());
				activeP.executing(currTime);
//				System.out.println("getCPUburst after activeP: " + activeP.getBurstTime());
				for (int i = 0; i < readyQ.size(); i++) {
					p = (Process) readyQ.get(i);
//					System.out.println("readyQ.get(wait time): " + p.getWaitTime());
					p.waiting(currTime);
//					System.out.println("after readyQ.get(wait time): " + p.getWaitTime());
				}
			}
			else if (activeP.isCompleted()) {
				resultProcesses.add(activeP);
				numProcOut++;
//				temp = Long.toString(activeP.getStartTime()) + "\t" + Long.toString(activeP.getPID());
//				GanntChart.add(temp);
			} else {
				activeP.executing(currTime);
			}
			currTime++;
		}
	}
	
	public void SJF() {
		currTime = 0;
		Process p, activeP = null;
		numProcOut = 0;
		String temp = "";
		while (!allJobsCompleted()) {
			if (!jobQ.isEmpty()) {
				for (int i = 0; i < jobQ.size(); i++) {		
					if (jobQ.get(i).getArrivalTime() == currTime) {
						jobQ.get(i).setArrived(true);
						readyQ.add(jobQ.get(i));
					}
				}
				for (int i = 0; i < jobQ.size(); i++) 
					if (jobQ.get(i).isArrived())  jobQ.remove(i);
//				System.out.println("jobQ.size() " + jobQ.size());		
			}

			if (!readyQ.isEmpty()) {
				if (activeP == null) {
					activeP = readyQ.get(0);
					readyQ.remove(0);
					activeP.setStartTime(currTime);
					activeP.setActive(true);
					temp = Long.toString(currTime) + "\t" + Long.toString(activeP.getPID());
					GanntChart.add(temp);
				}
				if (activeP.isCompleted()) {
					resultProcesses.add(activeP);
					numProcOut++;
					Collections.sort(readyQ, BURST_ORDER);
					activeP = readyQ.get(0);
					readyQ.remove(0);
					activeP.setStartTime(currTime);
					activeP.setActive(true);
					temp = Long.toString(currTime) + "\t" + Long.toString(activeP.getPID());
					GanntChart.add(temp);
				} 
//				System.out.println("getCPUburst activeP: " + activeP.getBurstTime());
				activeP.executing(currTime);
//				System.out.println("getCPUburst after activeP: " + activeP.getBurstTime());
				for (int i = 0; i < readyQ.size(); i++) {
					p = (Process) readyQ.get(i);
//					System.out.println("readyQ.get(wait time): " + p.getWaitTime());
					p.waiting(currTime);
//					System.out.println("after readyQ.get(wait time): " + p.getWaitTime());
				}
			}
			else if (activeP.isCompleted()) {
				resultProcesses.add(activeP);
				numProcOut++;
//				temp = Long.toString(activeP.getStartTime()) + " " + Long.toString(activeP.getPID());
//				GanntChart.add(temp);
			} else {
				activeP.executing(currTime);
			}
			currTime++;
		}
	}
	
	public void RR(int quantum) {
		currTime = 0;
		Process p, activeP = null;
		numProcOut = 0;
		String temp = "";
		while (!allJobsCompleted()) {
			if (!jobQ.isEmpty()) {
				for (int i = 0; i < jobQ.size(); i++) {		
					if (jobQ.get(i).getArrivalTime() == currTime) {
						jobQ.get(i).setArrived(true);
						readyQ.add(jobQ.get(i));
					}
				}
				for (int i = 0; i < jobQ.size(); i++) 
					if (jobQ.get(i).isArrived())  jobQ.remove(i);
//				System.out.println("jobQ.size() " + jobQ.size());		
			}

			if (!readyQ.isEmpty()) {
				if (activeP == null) {
					activeP = readyQ.get(0);
					readyQ.remove(0);
					activeP.setStartTime(currTime);
					activeP.setActive(true);
					temp = Long.toString(currTime) + "\t" + Long.toString(activeP.getPID());
					GanntChart.add(temp);
				}
				if (activeP.isCompleted()) {
					resultProcesses.add(activeP);
					numProcOut++;
					activeP = readyQ.get(0);
					readyQ.remove(0);
					activeP.setStartTime(currTime);
					activeP.setActive(true);
					temp = Long.toString(currTime) + "\t" + Long.toString(activeP.getPID());
					GanntChart.add(temp);
				} else if (currTime - activeP.getStartTime() >= quantum) {
					activeP.setActive(false);
					readyQ.add(activeP);
					activeP = readyQ.get(0);
					readyQ.remove(0);
					if (activeP.getStartTime() == 0)
						activeP.setStartTime(currTime);
					activeP.setActive(true);
					temp = Long.toString(currTime) + "\t" + Long.toString(activeP.getPID());
					GanntChart.add(temp);
				}
//				System.out.println("getCPUburst activeP: " + activeP.getBurstTime());
				activeP.executing(currTime);
//				System.out.println("getCPUburst after activeP: " + activeP.getBurstTime());
				for (int i = 0; i < readyQ.size(); i++) {
					p = (Process) readyQ.get(i);
//					System.out.println("readyQ.get(wait time): " + p.getWaitTime());
					p.waiting(currTime);
//					System.out.println("after readyQ.get(wait time): " + p.getWaitTime());
				}
			}
			else if (activeP.isCompleted()) {
				resultProcesses.add(activeP);
				numProcOut++;
			} else if (currTime - activeP.getStartTime() >= quantum) {
				activeP.setStartTime(currTime);
				temp = Long.toString(currTime) + "\t" + Long.toString(activeP.getPID());
				GanntChart.add(temp);
				activeP.executing(currTime);
			} else {
				activeP.executing(currTime);
			}
			currTime++;
		}
	}
	
	public void loadJobQueue(ArrayList<Process> allProcesses) {
	    Process p;
		for (int i = 0; i < allProcesses.size(); i++) {
			p = (Process) allProcesses.get(i);
			jobQ.add(p);
		}
		Collections.sort(jobQ, ARRIVAL_ORDER);
	}
	
	public boolean allJobsCompleted() {
		if (numProcOut < numProc)
			return false;
		return true;
	}
	
	public double getAverageWT() {
		Process p;
		double wt = 0;
		
		for (int i = 0; i < resultProcesses.size(); i++) {
			p = (Process) resultProcesses.get(i);
			wt += p.getWaitTime();
		}
		wt = wt/resultProcesses.size();
		return wt;
	}
	
	public void printGannt() {
		for (String t : GanntChart ) 
				System.out.println(t);
	} 
}
