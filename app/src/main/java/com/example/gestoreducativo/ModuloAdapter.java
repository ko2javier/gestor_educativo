package com.example.gestoreducativo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ModuloAdapter
        extends RecyclerView.Adapter<ModuloAdapter.ModuloViewHolder> {

    // ===== INTERFACE =====
    public interface OnModuloClickListener {
        void onEditarClick(Modulo modulo);
        void onBorrarClick(Modulo modulo);
    }

    private List<Modulo> listaModulos;
    private OnModuloClickListener listener;

    public ModuloAdapter(List<Modulo> listaModulos,
                         OnModuloClickListener listener) {
        this.listaModulos = listaModulos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ModuloViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.elementolistamodulo, parent, false);

        return new ModuloViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ModuloViewHolder holder, int position) {

        Modulo modulo = listaModulos.get(position);

        holder.txtNombreModulo.setText(modulo.getNombreCompleto());
        holder.imgModulo.setImageResource(
                android.R.drawable.btn_star_big_on
        );

        //  CLICK EDITAR
        holder.btnEditar.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditarClick(modulo);
            }
        });

        //  CLICK BORRAR
        holder.btnBorrar.setOnClickListener(v -> {
            if (listener != null) {
                listener.onBorrarClick(modulo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaModulos.size();
    }

    // ===== VIEW HOLDER =====
    static class ModuloViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombreModulo;
        ImageView imgModulo;
        ImageButton btnEditar, btnBorrar;

        public ModuloViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNombreModulo = itemView.findViewById(R.id.txtNombreModulo);
            imgModulo = itemView.findViewById(R.id.imgModulo);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnBorrar = itemView.findViewById(R.id.btnBorrar);
        }
    }
}
