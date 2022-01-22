package com.mad.miniproject.selfDefence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.mad.miniproject.R;

import java.util.ArrayList;

public class SelfdefenceActivity extends AppCompatActivity {
    private RecyclerView tasksRecyclerView, recyclerView;
    private RecyclerAdapter tasksAdapter;
    private reAdapter adapter;
    private Toolbar toolbar;
    String[] titlearray, descriptionarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfdefence);


//        tasksRecyclerView = findViewById(R.id.itemsrecycler);
//        titlearray = getResources().getStringArray(R.array.titlearray);
//        descriptionarray = getResources().getStringArray(R.array.detailarray);
//        tasksAdapter = new RecyclerAdapter(this, titlearray, descriptionarray);
//        tasksRecyclerView.setAdapter(tasksAdapter);
//        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("        search for defence technique");
        ArrayList<SelfDefenceModel> arrayList = new ArrayList<>();

        arrayList.add(new SelfDefenceModel("Hammer strike",
        "Using your car keys is one of the easiest ways to defend yourself. Don’t use your fingernails, because you’re more at risk to injure your hands.\nInstead, if you feel unsafe while walking at night, have your keys stick out from one side of your fist for hammer strikes.\nAnother way to use your keys is to click them onto a lanyard to swing at your attacker.\n\nTo perform:\n" +
                "1. Hold your key ring in a tight fist, like holding a hammer, with keys extending from the side of your hand.\n" +
                "2. Thrust downward toward your target."));
        arrayList.add(new SelfDefenceModel("Groin kick",
        "If someone is coming at you from the front, a groin kick may deliver enough force to paralyze your attacker, making your escape possible.\n\nTo perform:\n" +
                "1. Stabilize yourself as best you can.\n" +
                "2. Lift your dominant leg off the ground and begin to drive your knee upward.\n" +
                "3. Extend your dominant leg, drive hips forward, slightly lean back, and kick forcefully, making contact between your lower shin or ball of your foot and the attacker’s groin area.\n\n" +
                "Alternative: If your attacker is too close, thrust your knee toward the groin. Make sure you’re stabilized and not at risk of falling over."));
        arrayList.add(new SelfDefenceModel("Elbow strike",
        "If your attacker is in close range and you’re unable to get enough momentum to throw a strong punch or kick, use your elbows.\n\nTo perform:\n" +
                "1. If you can, stabilize yourself with a strong core and legs to ensure a powerful blow.\n" +
                "2. Bend your arm at the elbow, shift your weight forward, and strike your elbow into your attacker’s neck, jawline, chin, or temple. These are all effective targets.\n" +
                "3. This may cause your attacker to loosen their grip, allowing you to run."));
        arrayList.add(new SelfDefenceModel("Escape from side headlock",
        "When the attacker locks their arm around your head from the side, your first instinct should be to avoid getting choked.\n\nTo perform:\n" +
                "1. Turn into the attacker’s side as much as possible to avoid being choked.\n" +
                "2. With your hand that’s furthest away, strike the groin with open-handed slaps until you have enough mobility to turn your head all the way out to disengage."));


        recyclerView = findViewById(R.id.itemsrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new reAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu,menu);

        MenuItem menuItem=menu.findItem(R.id.search_id);
        android.widget.SearchView searchView=(android.widget.SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return true;
    }
}