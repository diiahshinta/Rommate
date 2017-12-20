package mobile.ap.rommate;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

import mobile.ap.rommate.Recycle.HomeActivity;

public class TabHostActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);

        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, HomeActivity.class);
        spec = tabHost.newTabSpec("home").setIndicator("HOME", res.getDrawable(R.drawable.ic_tab_home)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, PoiActivity.class);
        spec = tabHost.newTabSpec("poi").setIndicator("POI", res.getDrawable(R.drawable.ic_tab_akun)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, WishlistActivity.class);
        spec = tabHost.newTabSpec("wishlist").setIndicator("WISHLIST", res.getDrawable(R.drawable.ic_tab_wishlist)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, AkunActivity.class);
        spec = tabHost.newTabSpec("akun").setIndicator("AKUN", res.getDrawable(R.drawable.ic_tab_akun)).setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
}
