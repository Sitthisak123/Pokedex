package cs.sitthisak.pokedex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bnvPokemon;
    private NavigationBarView.OnItemSelectedListener listener =
    new NavigationBarView.OnItemSelectedListener(){
        @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            if (item.getItemId()==R.id.menu_home){
                fragment = new HomeFragment();
            } else if (item.getItemId()==R.id.menu_add){
                fragment = new AddFragment();
            } else if (item.getItemId()==R.id.menu_search) {
                fragment = new SearchFragment();
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_pokemon, fragment)
                    .commit();

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matchView();
        bnvPokemon.setOnItemSelectedListener(listener);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_pokemon, new HomeFragment())
                .commit();
    }

    private void matchView() {
        bnvPokemon = findViewById(R.id.bnv_pokemon);
    }
}