package co.example.johan_lohi.sysprog_lab_61;

import android.util.Log;

public class FetchThread extends Thread {

    public interface ThreadNotifier {
        void fetchingData(String message);
    }

    private ThreadNotifier listener = null;

    public void setListener(ThreadNotifier listener) {
        this.listener = listener;
    }

    public String uString;

    @Override
    public void run() {

        NetworkLoader networkLoader = new NetworkLoader(uString);
        try{

            String data = networkLoader.getData();
            if (listener != null) {
                Log.d("Lecture3", "if");
                this.listener.fetchingData(data);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
