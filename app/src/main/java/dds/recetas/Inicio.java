package dds.recetas;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.Menu;

public class Inicio extends AppCompatActivity {

    ListView listView;
    String[] favoritos = new String[] {"Tortilla Española", "Tortilla Francesa", "Mole Poblano"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new InicioFragment()).commit();

        listView = (ListView) findViewById(R.id.listaFavoritos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, favoritos);
        listView.setAdapter(adapter);


    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    android.support.v4.app.Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.navigation_inicio:
                            selectedFragment = new InicioFragment();
                            break;
                        case R.id.navigation_agregar:
                            selectedFragment = new AgregarFragment();
                            break;
                        case R.id.navigation_favoritos:
                            selectedFragment = new FavoritosFragment();
                            break;
                        default: break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };


}
