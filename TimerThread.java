package ca2;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TimerThread implements Runnable {
	
	// booleans indicating whether the timer is running, and whether the pause button has been pressed.
	boolean running;
	boolean paused;
	
	// text fields to be updated.
	JTextField countdownField;
	JTextField elapsedField;
	
	// counters of seconds
	long elapsedSeconds;
	long countdownSeconds;
	
	Timer timer;
	// The constructor:
	// This should set up initial values for the text fields, booleans and start time.
	// It initialises the text fields to be updated along with countdownSeconds and elapsedSeconds.
	// It can then start the thread.
	    TimerThread(JTextField countdownField, JTextField elapsedField, long secondsToRun, long secondsSinceStart, Timer timer1) {
	    this.countdownField = countdownField;
	    this.elapsedField = elapsedField;
		this.countdownSeconds = secondsToRun;
		this.elapsedSeconds = secondsSinceStart;
		this.timer = timer1;
		running = true;
		paused = false;
		

	}

	
	// This needs to update the text fields for countdown and elapsed seconds every second.
	// The thread should run until countdownSeconds is 0. If the pause button is activated, the number of elapsed
	// seconds will increase by 1 every second, whereas if pause is not activated, elapsed seconds will be increased
	// by 1 each second, while the countdown seconds should similarly decrease by 1. The text fields should be updated
	// each second. When countdownSeconds reaches 0, a message should be displayed saying that time is up.
	    
	public synchronized void run() {
	/* insert code here */
		while(running == true ) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (paused == true) {	 
	  			elapsedSeconds = elapsedSeconds + 01;
	 		}
			else if (paused == false) {
			elapsedSeconds = elapsedSeconds + 01;
			countdownSeconds = countdownSeconds - 01;
			}
			timer.updateTotalSeconds(convertToHMSString(elapsedSeconds), convertToHMSString(countdownSeconds));
			
  		if (countdownSeconds == 0) {
  			 
  			JOptionPane.showMessageDialog(timer, "Times Up");
  			break;
  		}  		
  		
		}
	}
	
	// This should pause or resume the application depending on the current value of paused.
	public void pause() {
    /* insert code here */
		
		if (paused == false) {

		paused = true;
		}
		
		else if (paused == true) {
			 
			 paused = false;
			
		}
		 

	}
	
	// This should stop the application.
	public void stop() {
   /* insert code here */
		running = false;

	}
	
	// These methods can be used by the Timer class to get the values of elapsed and countdown seconds.
	public long getElapsedSeconds() {
		return elapsedSeconds;
	}
	
	public long getCountdownSeconds() {
		return countdownSeconds;
	}
	
	// This method is to convert a long integer representing the number of seconds for which a thread has been running
	// into a displa
	// You could also use DateFormat.
	public String convertToHMSString(long seconds) {
		
		long secs, mins, hrs;
		// String to be displayed
		String returnString = "";
		
		// Split time into its components
		secs = seconds % 60;
		mins = (seconds / 60) % 60;
		hrs = (seconds / 60) / 60;
		
		// Insert 0 to ensure each component has two digits
		if (hrs < 10) {
			returnString = returnString + "0" + hrs;
		}
		
		else returnString = returnString + hrs;
		returnString = returnString + ":";
		
		if (mins < 10) {
			returnString = returnString + "0" + mins;		
		}
		
		else returnString = returnString + mins;
		returnString = returnString + ":";
		
		if (secs < 10) {
			returnString = returnString + "0" + secs;		
		}
		
		else returnString = returnString + secs;
		
		return returnString;
		
	}

}

