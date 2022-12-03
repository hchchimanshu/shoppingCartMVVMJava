package com.example.shoppingcart.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shoppingcart.R;
import com.example.shoppingcart.databinding.FragmentProductDetailBinding;
import com.example.shoppingcart.viewModel.ShopViewModel;

public class ProductDetailFragment extends Fragment {

//    FragmentProductDetailBinding fragmentProductDetailBinding;
    ShopViewModel shopViewModel;
    ImageView productImage;
    TextView productName, prodcutPrice, prodcutAvailability;
    Button addToCart;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        initView();
        return view;
//        fragmentProductDetailBinding = FragmentProductDetailBinding.inflate(inflater,container,false);
//        return fragmentProductDetailBinding.getRoot();
    }

    private void initView() {
        productImage = view.findViewById(R.id.product_image_IV);
        productName = view.findViewById(R.id.product_name_text_TV);
        prodcutPrice = view.findViewById(R.id.price_text_TV);
        prodcutAvailability = view.findViewById(R.id.available_text_TV);
        addToCart = view.findViewById(R.id.add_to_cart_BTN);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        Glide.with(getActivity())
                .load(shopViewModel.getProduct().getValue().getImageUrl())
                .into(productImage);
//        productImage.setImageURI(shopViewModel.getProduct().getValue().getImageUrl());
        productName.setText(shopViewModel.getProduct().getValue().getName());
        prodcutPrice.setText((int) shopViewModel.getProduct().getValue().getPrice()+"$");
        if (shopViewModel.getProduct().getValue().isAvailable()) {
            prodcutAvailability.setText("Available");
            addToCart.setVisibility(View.VISIBLE);
            addToCart.setEnabled(true);
        }
        else {
            prodcutAvailability.setText("Out of Stock");
        }

        onClickAddToCart();
//        fragmentProductDetailBinding.setShopViewModel(shopViewModel);
    }

    private void onClickAddToCart() {
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopViewModel.addItemToCart(shopViewModel.getProduct().getValue());
            }
        });
    }
}