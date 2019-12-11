package devops.vishal.weather_data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private List<TempData> list_data = new ArrayList<>();
    private RecyclerView recycler;
    private AdapterClass adapter;
    private TextView firstname, lastname, city, country, rideCount, freeRideCount, totalCredit;

    private CircleImageView userImage;


    private String URl = "https://gist.githubusercontent.com/iranjith4/522d5b466560e91b8ebab54743f2d0fc/raw/7b108e4aaac287c6c3fdf93c3343dd1c62d24faf/radius-mobile-intern.json";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Connecting widgets with java code
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);
        rideCount = findViewById(R.id.ridecount);
        freeRideCount = findViewById(R.id.freeridecount);
        totalCredit = findViewById(R.id.totalcredit);

        userImage = findViewById(R.id.userImage);

        recycler = findViewById(R.id.recycler_view);


        //Setting Adapters for the Recyclerview
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter=new AdapterClass(list_data);


        getDateFromServer();




    }

    private void getDateFromServer() {

        StringRequest request = new StringRequest(Request.Method.GET, URl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject obj = new JSONObject(response);

                    //Top JSon Object of the User
                    JSONObject UserData = obj.getJSONObject("data");



                    //Json Object for User Profile Informations
                    JSONObject ProfileData = UserData.getJSONObject("profile");

                    String uname = ProfileData.getString("first_name");
                    String lname = ProfileData.getString("last_name");
                    String profileImageUrl = ProfileData.getString("middle_name");
                    String ucity = ProfileData.getString("city");
                    String ucountry = ProfileData.getString("Country");


                    //Json Object for User Stats Information
                    JSONObject statsData = UserData.getJSONObject("stats");
                    String trides = statsData.getString("rides");
                    String frides = statsData.getString("free_rides");


                    //Json Object for User Credits Information
                    JSONObject creditData = statsData.getJSONObject("credits");
                    String uvalue = creditData.getString("value");
                     String csymbol = creditData.getString("currency_symbol");




                    //Setting TextView Foe the User Information

                    firstname.setText(uname);
                    lastname.setText(lname);
                    city.setText(ucity);
                    country.setText(ucountry);
                    rideCount.setText(trides);
                    freeRideCount.setText(frides);
                    totalCredit.setText(uvalue);


                    Glide.with(getApplicationContext())
                            .asBitmap()
                            .load(profileImageUrl)
                            .into(userImage);




                    //Json Array for User Rides Information
                    JSONArray trips = UserData.getJSONArray("trips");

                    for(int i=0; i < trips.length(); i++){

                        //Json Object for Particular Rides
                        JSONObject TripData = trips.getJSONObject(i);

                        String fromAddr = TripData.getString("from");
                        String ToAddr = TripData.getString("to");
                        String fromTimeDate = TripData.getString("from_time");
                        String ToTimeDate = TripData.getString("to_time");
                        String totalDuration = TripData.getString("trip_duration_in_mins");


                        //For converting total duration in hours and min seperately
                        int duration = Integer.parseInt(totalDuration);
                        int hours = duration / 60;
                        int min = duration % 60;

                        String fhour = String.valueOf(hours);
                        String fmin = String.valueOf(min);

                        String finalDuration = fhour+"h"+" "+fmin+"m";




                        //Json Object for Cost of rides of the uUser
                        JSONObject costData = TripData.getJSONObject("cost");
                        String Value = costData.getString("value");
                        String Symbol = costData.getString("currency_symbol");


                        //For Debug Process Logs are placed
                        Log.d("////RESPONSE////", fromAddr);
                        Log.d("////RESPONSE////", ToAddr);
                        Log.d("////RESPONSE////", fromTimeDate);
                        Log.d("////RESPONSE////", ToTimeDate);
                        Log.d("////RESPONSE////", totalDuration);
                        Log.d("////RESPONSE////",  String.valueOf(Value));
                        Log.d("////RESPONSE////", Symbol);




                        //Construction TempData class objects and Setting it into the ListView
                        TempData tempClass = new TempData(fromAddr,ToAddr,fromTimeDate,ToTimeDate,Value,Symbol,finalDuration);
                        list_data.add(tempClass);



                    }


                    //Setting the ListView in the Adapter
                    recycler.setAdapter(adapter);


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });



        //Adding Request to the Queue
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);





    }

}
