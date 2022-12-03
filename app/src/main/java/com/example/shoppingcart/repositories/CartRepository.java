package com.example.shoppingcart.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingcart.models.CartItem;
import com.example.shoppingcart.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartRepository {

    //to get the list of items inside cart
    private MutableLiveData<List<CartItem>> mutableCart = new MutableLiveData<>();

    //get total price for cart
    private MutableLiveData<Double> mutableTotalPrice = new MutableLiveData<>();

    //connect shopfragment to shopRepo , we use shopView model to access this method
    public LiveData<List<CartItem>> getCart(){
        if (mutableCart.getValue() == null){
            initCart();
        }

        return mutableCart;
    }

    public void initCart() {
        mutableCart.setValue(new ArrayList<>());
        calculateCartTotal();
    }

    public boolean addItemToCart(Product product){
        if (mutableCart.getValue() == null){
            initCart();
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());

        for (CartItem cartItem : cartItemList) {
            if (cartItem.getProduct().getId().equals(product.getId())) {
                if (cartItem.getQuantity() == 5) {
                    return false;
                }

                int index = cartItemList.indexOf(cartItem);
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemList.set(index, cartItem);

                mutableCart.setValue(cartItemList);
                return true;
            }
        }
        CartItem cartItems = new CartItem(product,1);
        cartItemList.add(cartItems);

        mutableCart.setValue(cartItemList);
        calculateCartTotal();
        return true;
    }

    public void removeItemFromCart(CartItem cartItem){
        if (mutableCart.getValue() == null){
            return;
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        if (cartItemList.contains(cartItem)){
            int index = cartItemList.indexOf(cartItem);
            if(cartItemList.get(index).getQuantity()>1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartItemList.set(index, cartItem);
                mutableCart.setValue(cartItemList);
            }else{
                cartItemList.remove(cartItem);
                mutableCart.setValue(cartItemList);
            }
        }
        else {
            cartItemList.remove(cartItem);
            mutableCart.setValue(cartItemList);
        }
        calculateCartTotal();
    }

    public void changeQuantity(CartItem cartItem, int quantity){
        if (mutableCart.getValue() == null){
            return;
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        CartItem updatedItem = new CartItem(cartItem.getProduct(),quantity);
        cartItemList.set(cartItemList.indexOf(cartItem),updatedItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
    }

    private void calculateCartTotal(){
        if (mutableCart.getValue() == null) return;
        double total = 0.0;
        List<CartItem> cartItemList = mutableCart.getValue();
        for (CartItem cartItem : cartItemList){
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        mutableTotalPrice.setValue(total);
    }

    public LiveData<Double> getTotalPrice(){
        if (mutableTotalPrice.getValue() == null){
            mutableTotalPrice.setValue(0.0);
        }
        return mutableTotalPrice;
    }
}
