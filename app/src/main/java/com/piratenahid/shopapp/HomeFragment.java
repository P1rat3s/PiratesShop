package com.piratenahid.shopapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.piratenahid.shopapp.Adapters.BannerImageAdapter;
import com.piratenahid.shopapp.Adapters.BrandHighLightAdapter;
import com.piratenahid.shopapp.Adapters.CategoriesAdapter;
import com.piratenahid.shopapp.Adapters.ProductAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class HomeFragment extends Fragment {
    List<List<String>> brands, products;
    List<String> categories, images, images1,images2,images3,images4, product_images, names, prices;
    LinearLayoutManager layoutManager3;
    RecyclerView recyclerView, recyclerView2, recyclerView3, productRecyclerview;
    LinearLayout bannersAndExtra;
    ImageButton search, showProducts;
    int productsShowing;

    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        showProducts = view.findViewById(R.id.show_onlyProducts);
        bannersAndExtra = view.findViewById(R.id.bannersAndExtra);

        productsShowing = View.GONE;

        showProducts.setOnClickListener(view1 -> {
            bannersAndExtra.setVisibility(productsShowing);
            if(productsShowing == View.GONE){
                 productsShowing = View.VISIBLE;
                 showProducts.setImageDrawable(getContext().getDrawable(R.drawable.ic_arrow_downward_24));
            }else {
                productsShowing = View.GONE;
                showProducts.setImageDrawable(getContext().getDrawable(R.drawable.ic_arrow_upward_24));
            }



        });


        images = new ArrayList<>();
        brands = new ArrayList<>();
        categories = new ArrayList<>();
        products = new ArrayList<>();

        images1 = new ArrayList<>();
        images2 = new ArrayList<>();
        images3 = new ArrayList<>();
        images4 = new ArrayList<>();
        names = new ArrayList<>();
        prices = new ArrayList<>();
        product_images = new ArrayList<>();


        db.collection("homeBanner").get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                String name = (String) document.getData().get("image");
                                images.add(name);


                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                        BannerImageAdapter adapter = new BannerImageAdapter(getContext(), images);
                        recyclerView.setAdapter(adapter);

                    }
                }
        );


        db.collection("categories")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                String name = (String) document.getData().get("name");
                                categories.add(name);


                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getContext(), categories);
                        recyclerView3.setAdapter(categoriesAdapter);
                    }
                });

        db.collection("brandAd")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                String name1 = (String) document.getData().get("image1");
                                String name2 = (String) document.getData().get("image2");
                                String name3 = (String) document.getData().get("image3");
                                String name4 = (String) document.getData().get("image4");

                                images1.add(name1);
                                images2.add(name2);
                                images3.add(name3);
                                images4.add(name4);


                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }

                        brands.add(images1);
                        brands.add(images2);
                        brands.add(images3);
                        brands.add(images4);

                        BrandHighLightAdapter brandHighLightAdapter = new BrandHighLightAdapter(getContext(),brands);
                        recyclerView2.setAdapter(brandHighLightAdapter);
                    }
                });


        db.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                String image = (String) document.getData().get("image");
                                String name = (String) document.getData().get("name");
                                String price = (String) document.getData().get("price");

                                product_images.add(image);
                                names.add(name);
                                prices.add(price);


                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }

                        products.add(product_images);
                        products.add(names);
                        products.add(prices);

                        ProductAdapter productAdapter = new ProductAdapter(getContext(), products);
                        productRecyclerview.setAdapter(productAdapter);
                    }
                });







        recyclerView = view.findViewById(R.id.banner_recyclerView);
        recyclerView2 = view.findViewById(R.id.brand_highlights_reView);
        recyclerView3 = view.findViewById(R.id.categoriesRecycle);
        productRecyclerview = view.findViewById(R.id.productsRecycle);




        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false),
                layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,  false);
        RecyclerView.LayoutManager product_layout_manager = new GridLayoutManager(getContext(),3);
        productRecyclerview.setLayoutManager(product_layout_manager);
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);



    }
}