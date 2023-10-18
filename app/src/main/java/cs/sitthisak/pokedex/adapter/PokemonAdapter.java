package cs.sitthisak.pokedex.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import cs.sitthisak.pokedex.R;
import cs.sitthisak.pokedex.model.Pokemon;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
    private Context context;
    private List <Pokemon> pokemons;

    public  PokemonAdapter(Context context, List<Pokemon> pokemons){
        this.context = context;
        this.pokemons = pokemons;
    }
    @NonNull
    @Override
    public PokemonAdapter.PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.pokemon_item, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.PokemonViewHolder holder, int position) {
        Pokemon pokemon = pokemons.get(position);
        holder.tvName.setText(pokemon.getName());
        holder.tvHeight.setText("Height: " + pokemon.getHeight());
        holder.tvWeight.setText("Weight: " + pokemon.getWieght());

        Picasso.get().load(pokemon.getPicUrl())
                .fit().centerInside().into(holder.imgvPokemon);
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvWeight, tvHeight;
        ImageView imgvPokemon;
        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvHeight = itemView.findViewById(R.id.tv_height);
            tvWeight = itemView.findViewById(R.id.tv_weight);
            imgvPokemon = itemView.findViewById(R.id.imgv_pokemon);
        }
    }
}
