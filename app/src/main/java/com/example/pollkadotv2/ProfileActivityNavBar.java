package com.example.pollkadotv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.common.api.Api;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.pollkadotv2.R.id.textViewQuestion;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivityNavBar extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button LogoutButton;
    public DatabaseReference mDatabaseReference;
    private EditText editTextName, editTextAddress;
    private Button buttonSave;
    public final static String ID_EXTRA="com.example.pollkadotv2";


    ArrayList<String> myArrayList = new ArrayList<>();
    ListView myListView;
    Firebase myFirebase;
    List<PollInformation> questionList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_nav_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Toast.makeText(ProfileActivityNavBar.this,"Fetching Polls", Toast.LENGTH_LONG).show();

        questionList = new ArrayList<>();
        myListView = (ListView) findViewById(R.id.ListView);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfileActivityNavBar.this, Verification.class);
                startActivity(intent);


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {

            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("PollQuestions");
        //editTextAddress=(EditText) findViewById(R.id.editTextAddress);
        //editTextName=(EditText) findViewById(R.id.editTextName);
        //buttonSave=(Button) findViewById(R.id.buttonSave);
        //LogoutButton = (Button) findViewById(R.id.LogoutButton);


        FirebaseUser user = firebaseAuth.getCurrentUser();


        //////////////SAVING INFORMATION CODE

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Welcome " + user.getEmail());


        //buttonLogout.setOnClickListener(this);
        //buttonSave.setOnClickListener(this);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                questionList.clear();
                for (com.google.firebase.database.DataSnapshot pollInformationSnapshot : snapshot.getChildren()) {
                    PollInformation pollInformation = pollInformationSnapshot.getValue(PollInformation.class);
                    int i = 0;
                    questionList.add(pollInformation);

                    Log.d("Ajay ", "Mikhayla");
                    Log.i("Value of element ", questionList.get(i).toString());

                    QuestionList adapter = new QuestionList(ProfileActivityNavBar.this, questionList);
                    myListView.setAdapter(adapter);
                    i++;

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        //Continue from here
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            private TextView Question;
            private TextView Description;
            private TextView Option1;
            private TextView Option2;



            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Question = (TextView)findViewById(R.id.textViewQuestion);
                Description = (TextView)findViewById(R.id.textViewDescription);

                Option1 = (TextView)findViewById(R.id.textViewOption1);

                Option2 = (TextView)findViewById(R.id.textViewOption2);



                Intent intent = new Intent(ProfileActivityNavBar.this, PollCount.class);

                intent.putExtra("textViewQuestion", Question.getText().toString());
                intent.putExtra("textViewDescription", Description.getText().toString());
                intent.putExtra("textViewOption1", Option1.getText().toString());
                intent.putExtra("textViewOption2", Option2.getText().toString());
                startActivity(intent);

            }
        });

*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_activity_nav_bar, menu);
        return true;
    }


/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        Fragment fragment=null;

        int id = item.getItemId();

        if (id == R.id.dashboard) {
            startActivity(new Intent(this,ProfileActivityNavBar.class));

            // / Handle the camera action
        } else if (id == R.id.createpoll) {
            startActivity(new Intent(this,Verification.class));

        } else if (id == R.id.joinpoll) {


        } else if (id == R.id.aboutus) {

            startActivity(new Intent(ProfileActivityNavBar.this, AboutUs.class));

        } else if (id == R.id.Logout) {
            firebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(ProfileActivityNavBar.this, LoginActivity.class));

        }else if (id == R.id.nav_share) {

            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String shareBody = "Create Your Own Poll!";
            String shareSub = "POLL";
            myIntent.putExtra(Intent.EXTRA_SUBJECT,shareBody);
            myIntent.putExtra(Intent.EXTRA_TEXT,shareSub);
            startActivity(Intent.createChooser(myIntent," Share Via "));

        }

        if(fragment!=null){

            FragmentManager fragmentManager= getSupportFragmentManager();
            FragmentTransaction ft=  fragmentManager.beginTransaction();
            ft.replace(R.id.frame_profile_nav_bar,fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onClick(View view) {


    }
}
