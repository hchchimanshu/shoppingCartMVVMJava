package com.example.shoppingcart.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingcart.databinding.CartRowBinding;
import com.example.shoppingcart.models.CartItem;

import java.util.List;

public class CartListAdapter extends ListAdapter<CartItem, CartListAdapter.CartViewHolder>  {

    CartInterface cartInterface;

    public CartListAdapter(CartInterface cartInterface) {
        super(CartItem.itemCallback);
        this.cartInterface = cartInterface;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CartRowBinding cartRowBinding = CartRowBinding.inflate(inflater,parent,false);
        return new CartViewHolder(cartRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.cartRowBinding.setCartItem(getItem(position));

        //if binding are pendings
        holder.cartRowBinding.executePendingBindings();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{

        CartRowBinding cartRowBinding;

        public CartViewHolder(@NonNull CartRowBinding cartRowBinding) {
            super(cartRowBinding.getRoot());
            this.cartRowBinding= cartRowBinding;

            cartRowBinding.deleteCartIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartRowBinding.spinner.setSelection((getItem(getAdapterPosition()).getQuantity())-1);
                    cartInterface.deleteItem(getItem(getAdapterPosition()));
                    notifyDataSetChanged();
                }
            });

            //quanity of spinner
            cartRowBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int quantity = position + 1;
                    if(quantity == getItem(getAdapterPosition()).getQuantity()){
                        return;
                    }
                    cartInterface.changeQuantity(getItem(getAdapterPosition()), quantity);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    public interface CartInterface{

        void deleteItem(CartItem cartItem);
        void changeQuantity(CartItem cartItem,int quantity);
    }
}
