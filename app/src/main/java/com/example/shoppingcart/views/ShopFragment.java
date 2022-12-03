package com.example.shoppingcart.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppingcart.R;
import com.example.shoppingcart.adapters.ShopListAdapter;
import com.example.shoppingcart.databinding.FragmentShopBinding;
import com.example.shoppingcart.models.Product;
import com.example.shoppingcart.viewModel.ShopViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ShopFragment extends Fragment implements ShopListAdapter.ShopInterface {

    private static final String TAG = "ShopFragment";
    FragmentShopBinding fragmentShopBinding;
    ShopListAdapter shopListAdapter;
    private ShopViewModel shopViewModel;
    private NavController navController;

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentShopBinding = FragmentShopBinding.inflate(inflater,container,false);
        fragmentShopBinding.shopRV.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));
        fragmentShopBinding.shopRV.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.HORIZONTAL));
        return fragmentShopBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //settingAdapter
        shopListAdapter = new ShopListAdapter(this);
        fragmentShopBinding.shopRV.setAdapter(shopListAdapter);

        navController = Navigation.findNavController(view);
        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                shopListAdapter.submitList(products);
            }
        });
    }

    //setup the onclick on this method by view binding in shop_row -> add to cart button
    @Override
    public void addItem(Product product) {
        Log.d(TAG, "addItem: "+product.toString());
        boolean isAdded = shopViewModel.addItemToCart(product);
        Log.d(TAG, "addItem: "+product.getName() + isAdded);
        if (isAdded){
            Snackbar.make(requireView(), product.getName()+" added ", Snackbar.LENGTH_LONG)
                    .setAction("Checkout", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            navController.navigate(R.id.action_shopFragment_to_cartFragment);
                        }
                    }).show();
        }
        else {
            Snackbar.make(requireView(), "Max quantity added ", Snackbar.LENGTH_LONG)
                    .show();

        }
    }

    @Override
    public void onItemClick(Product product) {
        Log.d(TAG, "onItemClick: "+product.toString());
        shopViewModel.setProduct(product);
        navController.navigate(R.id.action_shopFragment_to_productDetailFragment2);
    }
}