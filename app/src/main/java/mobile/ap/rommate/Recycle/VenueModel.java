package mobile.ap.rommate.Recycle;

import org.json.JSONArray;
import org.json.JSONException;

public class VenueModel {

  private String venueName;
  private String venueAddress;

  public VenueModel(String venueName, String venueAddress) {
    this.venueName = venueName;
    this.venueAddress = venueAddress;
  }



  public String getVenueName() {
    return venueName;
  }

  public String getVenueAddress() {
    return venueAddress;
  }




}