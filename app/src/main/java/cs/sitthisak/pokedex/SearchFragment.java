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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import cs.sitthisak.pokedex.adapter.PokemonAdapter;
import cs.sitthisak.pokedex.model.Pokemon;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private TextInputLayout tiplSearch;
    private MaterialButton btnSearch;
    private RecyclerView rcvSearch;
    private List<Pokemon> pokemons;
    private PokemonAdapter adapter;
    private DatabaseReference refPokemon;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,  container, false);

        pokemons = new ArrayList<>();
        refPokemon = FirebaseDatabase.getInstance().getReference("pokemons");

        matchView(view);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = tiplSearch.getEditText().getText().toString();
                Query query = refPokemon.orderByChild("name")
                        .startAt(search).endAt(search+"~");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        pokemons.clear();
                        for(DataSnapshot data: snapshot.getChildren()){
                            pokemons.add(data.getValue(Pokemon.class));
                        }
                        adapter = new PokemonAdapter(getContext(), pokemons);
                        rcvSearch.setAdapter(adapter);
                        rcvSearch.setLayoutManager(new LinearLayoutManager(getContext()));
                        tiplSearch.getEditText().setText("");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        return view;
    }

    private void matchView(View view) {
        btnSearch = view.findViewById(R.id.btn_search);
        tiplSearch = view.findViewById(R.id.tipl_search);
        rcvSearch = view.findViewById(R.id.rcv_search);
    }
}
