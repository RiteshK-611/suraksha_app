package com.mad.miniproject.lawsActs;

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

public class LawsactsActivity extends AppCompatActivity {
    private RecyclerView tasksRecyclerView, recyclerView;
    private RecyclerAdapter tasksAdapter;
    private reAdapter adapter;
    private Toolbar toolbar;
    String[] titlearray, descriptionarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawsacts);


//        tasksRecyclerView = findViewById(R.id.itemsrecycler);
//        titlearray = getResources().getStringArray(R.array.titlearray);
//        descriptionarray = getResources().getStringArray(R.array.detailarray);
//        tasksAdapter = new RecyclerAdapter(this, titlearray, descriptionarray);
//        tasksRecyclerView.setAdapter(tasksAdapter);
//        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("        search for laws & acts");
        ArrayList<LawsActsModel> arrayList = new ArrayList<>();

        arrayList.add(new LawsActsModel("Sexual Harassment of Women at Workplace Act, 2013",
        "Various forms of sexual harassment such as singing lewd songs, eve-teasing, making sexual advances despite refusal, watching, capturing, or sharing images and other media of a woman engaging in a private act without prior consent have all been criminalized by the Indian Penal Code."));
        arrayList.add(new LawsActsModel("Indecent Representation of Women (Prohibition) Act, 198",
        "Any indecent representation of women is banned by the Indecent Representation of Women (Prohibition) Act, 1986. This law, as it is considered to be too narrow for this day and age, has undergone certain changes and an Amendment Bill was drafted in 2012 which broadened its scope."));
        arrayList.add(new LawsActsModel("Sections 375 and 376(2) of The Indian Penal Code",
        "These sections spell out 7 years and 10 years' imprisonment, respectively, as the punishment for rape. The key feature of these sections is the requirement for consent by the woman in question for the act not to be considered as rape.  Intercourse with a woman of unsound mind or a girl below 16 years of age is considered to be rape irrespective of consent being given. Unfortunately, marital rape has not yet been criminalized."));
        arrayList.add(new LawsActsModel("Section 67 of the Information Technology Act",
        "Harassment and crimes against women committed by online sexual predators are prohibited under S. 67 of the Information Technology Act."));
        arrayList.add(new LawsActsModel("The Protection of Women from Domestic Violence Act",
        "Domestic violence is prohibited by law and is considered to be a criminal offense according to S. 498-A of the Indian Penal Code. Certain provisions in the Indian Evidence Act and the Criminal Procedure Code also deal with this issue. Finally, legislation devoted to this issue was enacted in 2005 - The Protection of Women from Domestic Violence Act."));


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