package net.keiaxx.kclivestream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StreamStatus {

    public String liveStatus(String streamname) {
        String liveStatus = "Offline";
        BufferedReader readurl = null;
        try {


            URL url = new URL("http://api.justin.tv/api/stream/list.json?jsonp=&channel=" + streamname);
            readurl = new BufferedReader(new InputStreamReader(url.openStream()));

            String wir = readurl.readLine();
            String compare = "[]";

            if (wir.equals(compare)) {
                liveStatus = "Offline";
            } else {
                liveStatus = "Online";
            }


        } catch (IOException ex) {
            Logger.getLogger(StreamStatus.class.getName()).log(Level.SEVERE, null, ex);

            Logger.getLogger(StreamStatus.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                readurl.close();
            } catch (IOException ex) {
                Logger.getLogger(StreamStatus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return liveStatus;


    }
}
