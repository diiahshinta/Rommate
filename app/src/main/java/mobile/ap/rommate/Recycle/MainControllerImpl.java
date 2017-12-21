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
import java.util.List;

public class MainControllerImpl implements MainController {

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
      foursquareRequestUrl = "https://api.foursquare.com/v2/venues/search?categoryId=4bf58dd8d48988d1ee931735&client_id=SY0DE1WXUZ4F4T3SZIYSNQLF4IVZKEUUIGU11QDPJSGYZFBC&client_secret=TQMR4RQPKDQRE3P4PJ2CAPYR1EW4AH3C2DIDMKEPP5FCRME1&v=20130815%20&near=" + URLEncoder.encode(area, "UTF-8");
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
              for (int i = 0; i < items.length(); i++) {
                JSONObject venuesJson = items.getJSONObject(i);
                venuesList.add(new VenueModel(
                    venuesJson.getString("name"),
                    venuesJson.getJSONObject("location").getString("address")
                ));

                  Log.i("sesuatu", venuesJson.getString("name"));
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
