package com.example.shoppingcart.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shoppingcart.models.CartItem;
import com.example.shoppingcart.models.Product;
import com.example.shoppingcart.repositories.CartRepository;
import com.example.shoppingcart.repositories.ShopRepository;

import java.util.List;

public class ShopViewModel extends ViewModel {

    ShopRepository shopRepository = new ShopRepository();

    CartRepository cartRepository = new CartRepository();

    //sameviewModel for getting into product detail fragment
    public MutableLiveData<Product> mutableProduct = new MutableLiveData<>();

    public LiveData<List<Product>> getProducts(){
        return shopRepository.getProducts();
    }

    //sameviewModel for getting into product detail fragment
    public void setProduct(Product product){
        mutableProduct.setValue(product);
    }

    public LiveData<Product> getProduct(){
        return mutableProduct;
    }

    public LiveData<List<CartItem>> getCartItems(){
        return cartRepository.getCart();
    }

    public boolean addItemToCart(Product product){
        return cartRepository.addItemToCart(product);
    }

    //delete Item from cart
    public void removeItemFromCart(CartItem cartItem){
        cartRepository.removeItemFromCart(cartItem);
    }

    //quanity of spinner in cart
    public void changeQuantity(CartItem cartItem, int quantity){
        cartRepository.changeQuantity(cartItem,quantity);
    }

    //for total in cart
    public LiveData<Double> getTotalPrice(){
        return cartRepository.getTotalPrice();
    }

    public void resetCart(){
        cartRepository.initCart();
    }
}
