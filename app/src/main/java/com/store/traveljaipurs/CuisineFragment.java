package com.store.traveljaipurs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.PurchaseInfo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CuisineFragment extends Fragment implements BillingProcessor.IBillingHandler {
//    BillingProcessor billingProcessor;
    private BillingClient billingClient;
//    private static final String LICENSE_KEY = BuildConfig.licenceKey; // PUT YOUR MERCHANT KEY HERE;
    // put your Google merchant id here (as stated in public profile of your Payments Merchant Center)
    // if filled library will provide protection against Freedom alike Play Market simulators
    final List<Item> items = new ArrayList<Item>();
    final List<SkuDetails> skuDetailsList = new ArrayList<>();
    int clickedIndex = -1;

    public CuisineFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.item_listview, container, false);
        initView();

        initData();

        final ItemAdapter adapter = new ItemAdapter(getActivity(), items);

        ListView listView = rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
//                billingProcessor.purchase(requireActivity(),"purchase_in_app");
            clickedIndex = i;
            if(skuDetailsList.isEmpty()) {
                Toast.makeText(requireActivity(), "This feature is coming soon", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                        .setSkuDetails(skuDetailsList.get(clickedIndex))
                        .build();

                BillingResult result = billingClient.launchBillingFlow(requireActivity(), billingFlowParams);
            }catch (Exception e){
                Log.d(CuisineFragment.class.getSimpleName(), e.getMessage());
            }
        });

        return rootView;
    }

    private void initData() {
        items.add(new Item(getString(R.string.tinh_thanh_hoa),
                R.drawable.tinh_thanh_hoa,
                getString(R.string.description_tinh_thanh_hoa)));

        items.add(new Item(getString(R.string.tinh_nghe_an),
                R.drawable.tinh_nghe_an,
                getString(R.string.description_tinh_nghe_an)));

        items.add(new Item(getString(R.string.tinh_ha_tinh),
                R.drawable.tinh_ha_tinh,
                getString(R.string.description_tinh_ha_tinh)));

        items.add(new Item(getString(R.string.tinh_quang_binh),
                R.drawable.tinh_quang_binh,
                getString(R.string.description_tinh_quang_binh)));

        items.add(new Item(getString(R.string.tinh_quang_tri),
                R.drawable.tinh_quang_tri,
                getString(R.string.description_tinh_quang_tri)));

        items.add(new Item(getString(R.string.tinh_thua_thien_hue),
                R.drawable.tinh_thua_thien_hue,
                getString(R.string.description_tinh_thua_thien_hue)));
    }

    private void openPlaceDetailActivity(int i) {
        Item currentItem = items.get(i);

        Intent itemDetailIntent = new Intent(getContext(), PlaceDetailActivity.class);

        itemDetailIntent.putExtra(getString(R.string.intent_extra_item_title),
                currentItem.getTitle());

        startActivity(itemDetailIntent);
    }

    private void initView() {

//        billingProcessor = BillingProcessor.newBillingProcessor(requireActivity(), LICENSE_KEY, this);
//        billingProcessor.initialize();
        billingClient = BillingClient.newBuilder(requireContext())
                .enablePendingPurchases()
                .setListener((billingResult, list) -> {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK &&list != null) {
                        for (Purchase purchase: list) {
                            if(purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED){
                                Log.d(CuisineFragment.class.getSimpleName(), "PURCHARED");
                                if(clickedIndex == -1) return;
                                openPlaceDetailActivity(clickedIndex);
                            }
                        }
                        // Query for existing user purchases
                        // Query for products for sale
                        Log.d(CuisineFragment.class.getSimpleName(), "Billing client successfully set up!");
                    }
                    Log.d(CuisineFragment.class.getSimpleName(), "billingResult: " + billingResult.getResponseCode());
                })
                .build();

        //TODO: Connect ứng dụng của bạn với Google Billing
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                //TODO: Sau khi connect thành công, thử lấy thông tin các sản phẩm
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    Log.d(CuisineFragment.class.getSimpleName(), "Billing client successfully set up!");
                    // Query for existing user purchases
                    // Query for products for sale
                }
                queryProducts();
            }

            @Override
            public void onBillingServiceDisconnected() {
                //TODO: Connect Google Play not success
                Log.d(CuisineFragment.class.getSimpleName(), "Billing service disconnected");
            }
        });

    }

    private void queryProducts() {
        if (!billingClient.isReady()) {
            Log.d(CuisineFragment.class.getSimpleName(), "queryPurchases: BillingClient is not ready");
        }
        // TODO: tạo list các product id (chính là product id bạn đã nhập ở bước trước) để lấy thông tin
        List<String> productIds = new ArrayList<>();
        for (Item element: items) {
            productIds.add(element.getTitle());
        }

        SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder()
                .setSkusList(productIds)
                .setType(BillingClient.SkuType.INAPP)
                //TODO: Sử dụng INAPP với one-time product và SUBS với các gói subscriptions.
                .build();

        // TODO: Thực hiện query
        // Query for existing in app products that have been purchased. This does NOT include subscriptions.

        billingClient.querySkuDetailsAsync(skuDetailsParams, (billingResult, list) -> {
            if (list != null && !list.isEmpty()) {
                //Đã lấy được thông tin các sản phẩm đang bán theo các product id ở trên
                skuDetailsList.clear();
                skuDetailsList.addAll(list);
                for (SkuDetails skuDetails : list) {
                    items.get(getItemIndexByTitle(skuDetails.getTitle())).setCost(skuDetails.getPrice());
                    Log.d(CuisineFragment.class.getSimpleName(), new Gson().toJson(skuDetails));

                }
            } else {
                Log.d(CuisineFragment.class.getSimpleName(), "No existing in app purchases found.");
            }
        });
    }

    private int getItemIndexByTitle(String title) {
        try {
            for (int i = 0; i < items.size(); i++) {
                Item currentItem = items.get(i);
                String currentTitle = currentItem.getTitle();
                if (title.equals(currentTitle)) {
                    return i;
                }
            }
        }
        catch (Error error) {
            error.printStackTrace();
        }
        return -1;
    }


    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable PurchaseInfo details) {
        Toast.makeText(getContext(), "onProductPurchased" + productId, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPurchaseHistoryRestored() {
        Toast.makeText(getContext(), "onPurchaseHistoryRestored", Toast.LENGTH_SHORT).show();
//        for (String sku : billingProcessor.listOwnedProducts())
//            Log.d(CuisineFragment.class.getSimpleName(), "Owned Managed Product: " + sku);
//        for (String sku : billingProcessor.listOwnedSubscriptions())
//            Log.d(CuisineFragment.class.getSimpleName(), "Owned Subscription: " + sku);
    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
        Toast.makeText(getContext(), "Billing Error: " + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBillingInitialized() {
        Toast.makeText(getContext(), "Billing Api is Initialized", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
//        if (billingProcessor != null) {
//            billingProcessor.release();
//        }
        super.onDestroy();
    }
}

