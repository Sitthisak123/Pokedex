package cs.sitthisak.pokedex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import cs.sitthisak.pokedex.adapter.PokemonAdapter;
import cs.sitthisak.pokedex.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView rcvPokedex;
    private DatabaseReference refPokemon;
    private List<Pokemon> pokemons;
    private PokemonAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,  container, false);
        pokemons = new ArrayList<>();
        refPokemon = FirebaseDatabase.getInstance().getReference("pokemons");
        matchView(view);

        refPokemon.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pokemons.clear();
                for (DataSnapshot data: snapshot.getChildren()){
                    pokemons.add(data.getValue(Pokemon.class));
                }
                adapter = new PokemonAdapter(getContext(), pokemons);
                rcvPokedex.setAdapter(adapter);
                rcvPokedex.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }

    private void matchView(View view) {
        rcvPokedex = view.findViewById(R.id.rcv_pokedex);
    }
}
