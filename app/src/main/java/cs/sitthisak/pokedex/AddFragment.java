package cs.sitthisak.pokedex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import cs.sitthisak.pokedex.model.Pokemon;

public class AddFragment extends Fragment {
    private TextInputLayout tiplName, tiplHeight, tiplWeight, tiplPicUrl;
    private MaterialButton btnadd;
    private DatabaseReference refPokemon;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add,  container, false);
        refPokemon = FirebaseDatabase.getInstance().getReference("pokemons");

        matchView(view);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = tiplName.getEditText().getText().toString();
                String height = tiplHeight.getEditText().getText().toString();
                String weight = tiplWeight.getEditText().getText().toString();
                String picUrl = tiplPicUrl.getEditText().getText().toString();
                if (name.isEmpty() || height.isEmpty() || weight.isEmpty() || picUrl.isEmpty()){
                    Toast.makeText(
                            getContext(),
                            "Please Full Fill DATA",
                            Toast.LENGTH_SHORT
                    ).show();
                }else{
                    Pokemon pokemon = new Pokemon (name, height, weight, picUrl);
                    String id = refPokemon.push().getKey();
                    refPokemon.child(id).setValue(pokemon);

                    tiplName.getEditText().setText("");
                    tiplHeight.getEditText().setText("");
                    tiplWeight.getEditText().setText("");
                    tiplPicUrl.getEditText().setText("");
                    tiplName.requestFocus();
                    Toast.makeText(getContext(),
                            "Add New Pokemon Successful",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });


        return view;
    }

    private void matchView(View view) {
        tiplName = view.findViewById(R.id.tipl_name);
        tiplHeight = view.findViewById(R.id.tipl_height);
        tiplWeight = view.findViewById(R.id.tipl_weight);
        tiplPicUrl = view.findViewById(R.id.tipl_pic_url);
        btnadd = view.findViewById(R.id.btn_add);
    }
}
