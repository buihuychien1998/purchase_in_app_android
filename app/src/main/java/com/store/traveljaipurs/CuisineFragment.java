package com.store.traveljaipurs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.PurchaseInfo;
import com.store.traveljaipurs.BuildConfig;
import com.store.traveljaipurs.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CuisineFragment extends Fragment implements BillingProcessor.IBillingHandler {
    BillingProcessor billingProcessor;
    private BillingClient billingClient;
    private static final String LICENSE_KEY = BuildConfig.licenceKey; // PUT YOUR MERCHANT KEY HERE;
    // put your Google merchant id here (as stated in public profile of your Payments Merchant Center)
    // if filled library will provide protection against Freedom alike Play Market simulators

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
        final ArrayList<Item> items = new ArrayList<Item>();

        items.add(new Item(getString(R.string.Rajasthani_Food_title),
                R.drawable.food_rajasthani,
                getString(R.string.Rajasthani_Food_location)));

        items.add(new Item(getString(R.string.BBQ_Nation_title),
                R.drawable.food_bbq,
                getString(R.string.BBQ_Nation_location)));

        items.add(new Item(getString(R.string.Virasat_title),
                R.drawable.food_virasat,
                getString(R.string.Virasat_location)));

        items.add(new Item(getString(R.string.Jaipur_Adda_title),
                R.drawable.food_jaipur_adda,
                getString(R.string.Jaipur_Adda_location)));

        items.add(new Item(getString(R.string.ZoloCrust_title),
                R.drawable.food_zolocrust,
                getString(R.string.ZoloCrust_location)));

        items.add(new Item(getString(R.string.Rajput_Room_title),
                R.drawable.food_the_rajput_room,
                getString(R.string.Rajput_Room_location)));

        final ItemAdapter adapter = new ItemAdapter(getActivity(), items);

        ListView listView = rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                billingProcessor.purchase(requireActivity(),"purchase_in_app");
                Item currentItem = items.get(i);

                Intent itemDetailIntent = new Intent(getContext(), PlaceDetailActivity.class);

                itemDetailIntent.putExtra(getString(R.string.intent_extra_item_title),
                        currentItem.getTitle());

                startActivity(itemDetailIntent);
            }
        });

        return rootView;
    }

    private void initView() {

        billingProcessor = BillingProcessor.newBillingProcessor(requireActivity(), LICENSE_KEY, this);
        billingProcessor.initialize();
        billingClient = BillingClient.newBuilder(requireContext())
                .enablePendingPurchases()
                .setListener((billingResult, list) -> {
                    //TODO: Hàm này sẽ trả về kết quả khi người dùng thực hiện mua hàng.
                    // Mình sẽ nói chi tiết ở phần 2 của bài viết
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        Log.d(CuisineFragment.class.getSimpleName(), "Billing client successfully set up!");
                        // Query for existing user purchases
                        // Query for products for sale
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
        productIds.add("pro");

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
                for (SkuDetails skuDetails : list) {
                    Log.d(CuisineFragment.class.getSimpleName(), new Gson().toJson(skuDetails));
                    BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                            .setSkuDetails(skuDetails)
                            .build();

                    BillingResult result = billingClient.launchBillingFlow(requireActivity(), billingFlowParams);
                    Log.d(CuisineFragment.class.getSimpleName(), "launchPurchaseFlow result ${responseCode}" + result.getResponseCode());
                }
            } else {
                Log.d(CuisineFragment.class.getSimpleName(), "No existing in app purchases found.");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable PurchaseInfo details) {
        Toast.makeText(getContext(), "onProductPurchased" + productId, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPurchaseHistoryRestored() {
        Toast.makeText(getContext(), "onPurchaseHistoryRestored", Toast.LENGTH_SHORT).show();
        for (String sku : billingProcessor.listOwnedProducts())
            Log.d(CuisineFragment.class.getSimpleName(), "Owned Managed Product: " + sku);
        for (String sku : billingProcessor.listOwnedSubscriptions())
            Log.d(CuisineFragment.class.getSimpleName(), "Owned Subscription: " + sku);
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
        if (billingProcessor != null) {
            billingProcessor.release();
        }
        super.onDestroy();
    }
}

