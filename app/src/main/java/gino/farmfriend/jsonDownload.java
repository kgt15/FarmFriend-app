package gino.farmfriend;

/**
 * Created by Gino on 19-02-2016.
 */
//public class jsonDownload {
//}

import android.content.Context;
        import android.content.ContextWrapper;
        import android.os.AsyncTask;
        import android.util.Log;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.json.JSONException;
        import org.json.JSONObject;
        import org.w3c.dom.Text;

        import java.io.BufferedInputStream;
        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;

/**
 * Created by Hassan on 17-02-2016.
 */
public class jsonDownload extends AsyncTask<URL, Void, Void> {

    public static JSONObject jsonObject;
    public static String json;

    @Override
    protected Void doInBackground(URL... urls) {
        int count = urls.length;
        for(int i = 0; i < count; i++) {
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) urls[i].openConnection();
                InputStream in = new BufferedInputStream(connection.getInputStream());
                readStream(in);
                //jsonObject = new JSONObject((java.util.Map) in);
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if (connection != null) connection.disconnect();
            }
        }
        return null;
    }

    private void readStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        //String line = reader.readLine();
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        in.close();
        json = sb.toString();
        //MainActivity.t.setText(json);
        forum.strTest = json;
        System.out.println("Checking :::::::::"+line);
        Log.v("Checking............->", line);
    }
}
