package co.example.johan_lohi.sysprog_lab_61;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.textclassifier.TextLinks;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView);
        tv = (TextView) findViewById(R.id.textView);
        RequestQueue queue = Volley.newRequestQueue(this);


        String url = "https://www.amica.fi/api/restaurant/menu/day?date=2018-10-15&language=en&restaurantPageId=66287";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        tv.setText(response.toString());
                    }
                   /*     try {
                            JSONArray array = response.getJSONArray("LunchMenu");

                            for (int i = 0; i < array.length(); i++)
                            {
                                JSONObject meal = array.getJSONObject(i);

                                String name = meal.getString("SetMenus");

                                tv.append(name + "\n");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }*/
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("json", "error");
                        error.printStackTrace();
                    }
                });
        //queue.add(jsonObjectRequest);
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }
}
