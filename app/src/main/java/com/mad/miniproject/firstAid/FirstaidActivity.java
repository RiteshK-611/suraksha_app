package com.mad.miniproject.firstAid;

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

public class FirstaidActivity extends AppCompatActivity {
    private RecyclerView tasksRecyclerView, recyclerView;
    private RecyclerAdapter tasksAdapter;
    private reAdapter adapter;
    private Toolbar toolbar;
    String[] titlearray, descriptionarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstaid);


//        tasksRecyclerView = findViewById(R.id.itemsrecycler);
//        titlearray = getResources().getStringArray(R.array.titlearray);
//        descriptionarray = getResources().getStringArray(R.array.detailarray);
//        tasksAdapter = new RecyclerAdapter(this, titlearray, descriptionarray);
//        tasksRecyclerView.setAdapter(tasksAdapter);
//        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("        search your injury here");
        ArrayList<FirstAidModel> arrayList = new ArrayList<>();

        arrayList.add(new FirstAidModel("Burn",
        "Follow these steps to immediately treat a burn:\n\n1. Flush the burned area with cool running water for several minutes.\n2. Call 911 for a severe burn.\n3. Apply a burn ointment or spray for pain.\n4. Take ibuprofen or acetaminophen for pain relief if necessary."));
        arrayList.add(new FirstAidModel("Cuts",
        "Follow these steps to immediately treat a cuts:\n\n1. Cleanse area thoroughly with soap and warm water, carefully washing away any dirt.\n2. Apply direct pressure to wound until bleeding stops.\n3. Put sterile bandage on wound.\n4. If cut is deep, get to a doctor as quickly as possible."));
        arrayList.add(new FirstAidModel("Choking",
        "If a person is choking, you should not interfere as long as he is coughing.\n If coughing does not dislodge the object from the trachea and the victim is breathing with extreme difficult or unable to talk then,\n Use the Abdominal Thrust only in an actual emergency\n\n1. Stand behind him with your arms around his waist.\n2. Place one fist, with the knuckle of the thumb against the victim's midsection, slightly above the navel but well below the breastbone.\n3. Hold your fist firmly with the other hand and pull both hands sharply toward you with an upward-and-inward jab.\n4. This procedure should be administered continually until either the object is forced out or the victim becomes unconscious.\n\n Take the patient to the doctor immediately if you're not comfortable handling the emergency"));
        arrayList.add(new FirstAidModel("Heartstroke",
        "Follow these steps to immediately treat a heartstroke:\n\n1. Cool the body of a heatstroke victim immediately.\n2. If possible, put him in cool water; wrap him in cool wet clothes; or sponge his skin with cool water, rubbing ice, or cold packs.\n3. Once the victim's temperature drops to about 101 F, lay him in the recovery position in a cool room.\n4. If the temperature begins to rise again, repeat the cooling process.\n5. If he/she is able to drink, give him/her some water.\n6. Do not give any kind of medication\n7. Seek medical attention"));
        arrayList.add(new FirstAidModel("Sprains",
        "Follow these steps to immediately treat a sprains:\n\n1. Keep the injured limb elevated.\n2. Apply ice to the injured area. Don’t apply ice directly to the skin. Wrap it in a cloth or put ice in a plastic bag.\n3. Keep the injured area compressed. Put it in a brace or tightly wrap it. Don’t wrap it so tight that it’ll cut off circulation.\n4. Ice for a while. Then compress. Repeat at intervals."));
        arrayList.add(new FirstAidModel("Fracture",
        "Follow these steps to immediately treat a fracture:\n\n1. Don’t try to straighten a fractured limb.\n2. Use a splint or padding to stabilize the area and keep it from moving.\n3. Apply a cold pack to the area. Don’t apply it directly to the skin. Wrap it in a cloth or put it in a plastic bag.\n4. Keep the area elevated, if possible.\n5. Give the person an anti-inflammatory drug, like ibuprofen."));
        

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