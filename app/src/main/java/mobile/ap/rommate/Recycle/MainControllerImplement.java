package mobile.ap.rommate.Recycle;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainControllerImplement implements MainController {

    private ProgressDialog loadingDialog;

    public void getVenuesData(String area, final List venuesList, final RecyclerView.Adapter venuesRecyclerAdapter, Context mainContext) {

        final Context context = mainContext;

        loadingDialog = new ProgressDialog(context);
        loadingDialog.setIndeterminate(false);
        loadingDialog.setCancelable(false);
        loadingDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String foursquareRequestUrl = null;
        try {
            foursquareRequestUrl = "https://api.foursquare.com/v2/venues/search?categoryId=4bf58dd8d48988d1ee931735,4bf58dd8d48988d1f8931735,4bf58dd8d48988d1fb931735,&client_id=YFRT0F0EKMLCUYYQO04YWQMUN15MXZH2KEXU5ZGN2D1QV0CF&client_secret=I2NQKHQPPL3DCCBTWU4PP4ZF2WJMGYZEOXIVXSHF0K330RJA&v=20130815%20&near=" + URLEncoder.encode(area, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, foursquareRequestUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        loadingDialog.dismiss();
                        try {
                            venuesList.clear();
                            JSONArray items = response.getJSONObject("response").getJSONArray("venues");
                            for (int i = 0; i <= items.length(); i++) {
                                JSONObject venuesJson = items.getJSONObject(i);
                                Log.i("sesuatu",venuesJson.getString("name"));
                                //Log.d("sesuatu3",venuesJson.getString("rating"));
                                Log.i("sesuatu2",String.valueOf(items.length()));
                                VenueModel ve;
                                ArrayList<VenueModel> ve2;
                                ve = new VenueModel(
                                        venuesJson.getString("name"),
                                        venuesJson.getJSONObject("location").getString("address"));
                                venuesList.add(ve);
                                Log.i("sesuatu7","ini dari ve = "+ ve.getVenueName()+", " + ve.getVenueAddress());

                                Log.i("sesuatu8","ini dari list = "+ venuesList.get(i));



                            }
                        } catch (JSONException e) {
                            venuesList.clear();
                            }
                        venuesRecyclerAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NetworkError) {
                            loadingDialog.dismiss();
                            } else {
                            loadingDialog.dismiss();
                            }
                    }
                });
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsObjRequest);
    }
}
