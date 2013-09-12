/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.tkorg.utilitys;

import com.googlecode.jpingy.Ping;
import com.googlecode.jpingy.Ping.Backend;
import com.googlecode.jpingy.PingArguments;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class PingGoogle {
    
    public static void main(String[] args) {
		
          
        Timer timer = new Timer ();
        TimerTask hourlyTask = new TimerTask () {
            @Override
            public void run () {
                try {
                    Process p=Runtime.getRuntime().exec("cmd /c start http://www.google.com.vn"); 
                    Process m=Runtime.getRuntime().exec("cmd /c start http://citeseerx.ist.psu.edu/index"); 

                    // your code here...
                } catch (IOException ex) {
                    Logger.getLogger(PingGoogle.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        };
        // schedule the task to run starting now and then every half hour...
        timer.schedule (hourlyTask, 0l, 1000*60*30);

    }
}
