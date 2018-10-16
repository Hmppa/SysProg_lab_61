package co.example.johan_lohi.sysprog_lab_61;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.textclassifier.TextLinks;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MainActivity extends ListActivity {

    ListView lv;
    TextView tv;
    ArrayList<HashMap<String,String>> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();
        lv = getListView();

        tv = (TextView) findViewById(R.id.textView);
        RequestQueue queue = Volley.newRequestQueue(this);


        String url = "https://www.amica.fi/api/restaurant/menu/day?date=2018-10-16&language=en&restaurantPageId=66287";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {


                            JSONObject lunchMenu = response.getJSONObject("LunchMenu");
                            JSONArray setMenus = lunchMenu.getJSONArray("SetMenus");

                            for (int i = 0; i < setMenus.length(); i++)
                            {
                                JSONObject menu = setMenus.getJSONObject(i);
                                JSONArray meal = menu.getJSONArray("Meals");

                                JSONObject mealfood = meal.getJSONObject(0);
                                String meal1 = mealfood.getString("Name");
                                String meal2 = "";
                                if (!meal.isNull(1)) {
                                JSONObject meal2Food = meal.getJSONObject(1);
                                meal2 = meal2Food.getString("Name");
                            }
                                HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("food1", meal1);
                                hashMap.put("food2", meal2);
                                arrayList.add(hashMap);

                               /* for (int j = 0; j < meal.length(); j++) {
                                    JSONObject mealsJSONObject = meal.getJSONObject(j);

                                    String name = mealsJSONObject.getString("Name");
                                    tv.append(name + "\n");
                                }*/


                            }
                            ListAdapter adapter = new SimpleAdapter(
                                    MainActivity.this,
                                    arrayList,
                                    R.layout.listitems,
                                    new String[] {"food1", "food2" },
                                    new int[] {R.id.item1, R.id.item2}
                            );
                            lv.setAdapter(adapter);
                            //String meal = response.get("SortOrder").toString();
                            //tv.setText(jsonArray.get(0).toString());
                            Log.d("json1", "try");

                        } catch (Exception e) {
                            Log.d("json1", "catch");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("json", "error");
                        error.printStackTrace();
                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }
}
