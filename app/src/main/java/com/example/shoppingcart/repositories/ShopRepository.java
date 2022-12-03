package com.example.shoppingcart.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingcart.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopRepository {

    private MutableLiveData<List<Product>> mutableProductList;

    public LiveData<List<Product>> getProducts(){
        if(mutableProductList==null){
            mutableProductList = new MutableLiveData<>();
            loadProducts();
        }
        return mutableProductList;
    }

    private void loadProducts() {
        List<Product> productList = new ArrayList<>();

        //fakeData
        productList.add(new Product(String.valueOf(UUID.randomUUID()),"iMac 21",1299,true,
                "https://www.apple.com/newsroom/images/product/imac/standard/Apple_imac-magickeyboardnum-magicmouse2-macos-wallpaper_08042020_big.jpg.large.jpg"));
        productList.add(new Product(String.valueOf(UUID.randomUUID()),"iPad Air",1199,true,
                        "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/ipad-air-witb-pink-cell-202203_FMT_WHH?wid=562&hei=744&fmt=jpeg&qlt=90&.v=1645646566364.jpg"));
        productList.add(new Product(String.valueOf(UUID.randomUUID()),"iPad Pro",1159,true,
                        "https://cdn.shopify.com/s/files/1/0529/4053/8039/products/31Mreycv_NL.jpg?v=1650883781.jpg"));
        productList.add(new Product(String.valueOf(UUID.randomUUID()),"iphone 14 Pro",1869,true,
                        "https://m.media-amazon.com/images/I/71ZDY57yTQL._SX522_.jpg"));
        productList.add(new Product(String.valueOf(UUID.randomUUID()),"iphone 14 Max",1849,true,
                        "https://d2d22nphq0yz8t.cloudfront.net/88e6cc4b-eaa1-4053-af65-563d88ba8b26/https://media.croma.com/image/upload/v1662655662/Croma%20Assets/Communication/Mobiles/Images/261979_oq7vjv.png/mxw_640,f_auto.jpg"));
        productList.add(new Product(String.valueOf(UUID.randomUUID()),"iphone 14 Pro Max",1879,true,
                        "https://www.apple.com/newsroom/images/product/iphone/geo/Apple-iPhone-14-Pro-iPhone-14-Pro-Max-silver-220907-geo_inline.jpg.large.jpg"));
        productList.add(new Product(String.valueOf(UUID.randomUUID()),"iphone 14",1829,true,
                        "https://www.xda-developers.com/files/2022/09/Apple-iPhone-14-Plus-Purple.jpg"));
        productList.add(new Product(String.valueOf(UUID.randomUUID()),"iphone 13 Pro",1685,true,
                        "https://img.giznext.com/assets/model/2/11247/apple-iphone-13-pro-34630b234a544052a6a3792d5e0672.jpg"));
        productList.add(new Product(String.valueOf(UUID.randomUUID()),"iphone 13 Max",1675,true,
                        "https://m.media-amazon.com/images/I/619m8rLBQSL._SL1500_.jpg"));
        productList.add(new Product(String.valueOf(UUID.randomUUID()),"iphone 13 Pro Max",1699,true,
                        "https://www.apple.com/newsroom/images/product/iphone/geo/Apple_iPhone-13-Pro_iPhone-13-Pro-Max_GEO_09142021_inline.jpg.slideshow-large_2x.jpg"));
        productList.add(new Product(String.valueOf(UUID.randomUUID()),"iphone X Pro",1359,true,
                        "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/refurb-iphone-11-pro-max-space-gray-2019?wid=400&hei=400&fmt=jpeg&qlt=90&.v=1611101628000.jpg"));

        mutableProductList.setValue(productList);
    }
}
