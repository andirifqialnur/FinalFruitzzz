package com.example.fruitzzz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Object MyOnBoardingSupportFragment;

//    private OnBoardingAdapter onBoardingAdapter;
//    private LinearLayout layoutOnBoardingIndicator;
//    private AppCompatButton nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);
//        startActivity(new Intent(this, OnBoardingActivity.class));

//        SharedPreferences sharedPreferences =
//                PreferenceManager.getDefaultSharedPreferences(this);
//        // Check if we need to display our OnboardingSupportFragment
//        if (!sharedPreferences.getBoolean(
//                MyOnBoardingSupportFragment.COMPLETED_ONBOARDING_PREF_NAME, false)) {
//            // The user hasn't seen the OnboardingSupportFragment yet, so show it
//            startActivity(new Intent(this, OnBoardingActivity.class));
//        }
        // back activity

        startActivity(new Intent(this, OnBoardingActivity.class));

//      OnBoarding Setting.......................
        SharedPreferences sharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String FirstTime = sharedPreferences.getString("FirstTimeInstall", "");

        if (FirstTime.equals("Yes")){
            startActivity(new Intent(this, Authentication.class));
            onBackPressed();
        }
        else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FirstTimeInstall", "Yes");
            editor.apply();
        }
//        layoutOnBoardingIndicator = findViewById(R.id.indicator);
//        nextButton = findViewById(R.id.next_btn);
//
//        setupOnBoardingItemList();
//
//        ViewPager2 onBoardingViewPager = findViewById(R.id.on_boarding_view);
//        onBoardingViewPager.setAdapter(onBoardingAdapter);
//
//        setupOnBoardingIndicators();
//        setCurrentOnBoardingIndicator(0);
//
//        onBoardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                setCurrentOnBoardingIndicator(position  );
//            }
//        });
//        nextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (onBoardingViewPager.getCurrentItem() + 1 < onBoardingAdapter.getItemCount()) {
//                    onBoardingViewPager.setCurrentItem(onBoardingViewPager.getCurrentItem() + 1);
//                } else {
//                    startActivity(new Intent(getApplicationContext(), Authentication.class));
//                    finish();
//                }
//            }
//        });
    }

//    @Override
//    public void onBackPressed() {
//        moveTaskToBack(true);
//    }
    //    @Override
//    protected void onFinishFragment() {
//        super.onFinishFragment();
//        // User has seen OnboardingSupportFragment, so mark our SharedPreferences
//        // flag as completed so that we don't show our OnboardingSupportFragment
//        // the next time the user launches the app.
//        SharedPreferences.Editor sharedPreferencesEditor =
//                PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
//        sharedPreferencesEditor.putBoolean(
//                COMPLETED_ONBOARDING_PREF_NAME, true);
//        sharedPreferencesEditor.apply();
//    }
//    private void setupOnBoardingItemList() {
//        List<OnBoardingItem> onBoardingItemList = new ArrayList<>();
//
//        OnBoardingItem itemOne = new OnBoardingItem();
//        itemOne.setTitle("Friends With Fruits");
//        itemOne.setDescription("Lorem ipsum dolor sit amet, consectetur \nadipiscing elit. Vitae, praesent augue \nelementum diam convallis. Tempus \nporttitor et id quam risus at. \nPellentesque mattis proin quis quis.");
//        itemOne.setImage(R.drawable.splash1);
//
//        OnBoardingItem itemTwo = new OnBoardingItem();
//        itemTwo.setTitle("Love Your Day");
//        itemTwo.setDescription("Lorem ipsum dolor sit amet, consectetur \nadipiscing elit. Vitae, praesent augue \nelementum diam convallis. Tempus \nporttitor et id quam risus at. \nPellentesque mattis proin quis quis.");
//        itemTwo.setImage(R.drawable.splash2);
//
//        OnBoardingItem itemThree = new OnBoardingItem();
//        itemThree.setTitle("More Health Now");
//        itemThree.setDescription("Lorem ipsum dolor sit amet, consectetur \nadipiscing elit. Vitae, praesent augue \nelementum diam convallis. Tempus \nporttitor et id quam risus at. \nPellentesque mattis proin quis quis.");
//        itemThree.setImage(R.drawable.splash3);
//
//        onBoardingItemList.add(itemOne);
//        onBoardingItemList.add(itemTwo);
//        onBoardingItemList.add(itemThree);
//
//        onBoardingAdapter = new OnBoardingAdapter(onBoardingItemList);
//    }
//    private void setupOnBoardingIndicators() {
//        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParams.setMargins(8, 0, 8, 0);
//
//        for(int i = 0; i < indicators.length; i++){
//            indicators[i] = new ImageView(getApplicationContext());
//            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_not_selected));
//            indicators[i].setLayoutParams(layoutParams);
//            layoutOnBoardingIndicator.addView(indicators[i]);
//        }
//    }
//    private void setCurrentOnBoardingIndicator(int index) {
//        int childCount = layoutOnBoardingIndicator.getChildCount();
//        for(int i = 0; i < childCount; i++) {
//            ImageView imageView = (ImageView) layoutOnBoardingIndicator.getChildAt(i);
//            if (i == index) {
//                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_selected));
//            } else {
//                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_not_selected));
//            }
//        }
//        if (index == onBoardingAdapter.getItemCount() - 1){
//            nextButton.setText("Get Started");
//        } else {
//            nextButton.setText("Ok, Got It");
//        }
//    }
}