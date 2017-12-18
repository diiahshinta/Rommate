package mobile.ap.rommate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import mobile.ap.rommate.model.User;

public class AkunActivity extends AppCompatActivity{
    private final AppCompatActivity activity = AkunActivity.this;
    Toolbar toolbar;
    private TextView name, email, phone;
    private AppCompatTextView editProfile, logout;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private String idUser, emailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        editProfile = (AppCompatTextView) findViewById(R.id.appCompatEditProfile);
        logout = (AppCompatTextView) findViewById(R.id.appCompatLogout);
        name = (TextView) findViewById(R.id.Name);
        email = (TextView) findViewById(R.id.Email);
        phone = (TextView) findViewById(R.id.Phone);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        idUser = user.getUid();
        emailUser = user.getEmail();
        loadData();

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity, EditProfilActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intentLogout = new Intent(activity, Login.class);
                intentLogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentLogout);
            }
        });
    }

    private void loadData() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("user").child(idUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null){
                    name.setText(user.getName());
                    email.setText(emailUser);
                    phone.setText(user.getPhone());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
