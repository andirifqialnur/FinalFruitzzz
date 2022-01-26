package com.example.fruitzzz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fruitzzz.databinding.FragmentHomePageBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends Fragment {

    FragmentHomePageBinding binding;

    TextClock Clock, AMPM, Date;

    View view;
    RecyclerView recipeRecycle, infoRecycle;

    TextView SeeRecipe, SeeInfo, GreetingName;
    String greetingName, user;

    AppCompatButton FruitzBtn;

    private List<HomeRecipe> listRecipe;
    private List<HomeInfo> listInfo;

    SharedPreferences sp1;

    public HomePage() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_page, container, false);

        Clock = view.findViewById(R.id.clock);
        AMPM = view.findViewById(R.id.ampm);
        Date = view.findViewById(R.id.date);

        SeeRecipe = view.findViewById(R.id.see_recipe);
        SeeInfo = view.findViewById(R.id.see_info);

        FruitzBtn = view.findViewById(R.id.fruitzzz_btn);

        recipeRecycle = view.findViewById(R.id.recycle_view);
        infoRecycle = view.findViewById(R.id.infooo);

        sp1 = getActivity().getSharedPreferences("AllData", Context.MODE_PRIVATE);

        GreetingName = view.findViewById(R.id.greetingName);

        greetingName = sp1.getString("username", null);
        GreetingName.setText(greetingName);

//        if (GreetingName == null) {
//            GreetingName.setText(user);
//        } else {
//            GreetingName.setText(greetingName);
//        }

//      currentDate();
        HomeRecipeAdapter recipeAdapter = new HomeRecipeAdapter(getContext(), listRecipe);
        HomeInfoAdapter infoAdapter = new HomeInfoAdapter(getContext(), listInfo);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL, false);
        recipeRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        recipeRecycle.setLayoutManager(layoutManager);
        recipeRecycle.setAdapter(recipeAdapter);

        LinearLayoutManager layoutManagerInfo = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL, false);
        infoRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        infoRecycle.setLayoutManager(layoutManagerInfo);
        infoRecycle.setAdapter(infoAdapter);

        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listRecipe = new ArrayList<>();
        listRecipe.add(new HomeRecipe("Salad Buah", "Lorem Ipsum dolor amet gihan", "33", "25", "30", R.drawable.recipe1));
        listRecipe.add(new HomeRecipe("Mango Pudding", "Lorem Ipsum dolor amet gihan",  "12", "20", "73", R.drawable.recipe2));
        listRecipe.add(new HomeRecipe("Ice Grape", "Lorem Ipsum dolor amet gihan",  "22", "27","21", R.drawable.recipe3));
        listRecipe.add(new HomeRecipe("Fruit Soup", "Lorem Ipsum dolor amet gihan",  "35", "5", "65", R.drawable.recipe4));
        listRecipe.add(new HomeRecipe("Orange Juice", "Lorem Ipsum dolor amet gihan",  "12", "3","19", R.drawable.recipe5));

        listInfo = new ArrayList<>();
        listInfo.add(new HomeInfo("Orange for Health", "Andi Rifqial Nur", R.drawable.info1, R.drawable.ic_icon_viewall));
        listInfo.add(new HomeInfo("BlueBerry for Heart", "Gilang Ramadhan", R.drawable.info2, R.drawable.ic_icon_viewall));
        listInfo.add(new HomeInfo("Strawberry and Smile", "Dian Reski Dirman", R.drawable.info3, R.drawable.ic_icon_viewall));

        binding = FragmentHomePageBinding.inflate(getLayoutInflater());
        binding.getRoot();

        String[] titles = {"Salad Buah", "Mango Pudding", "Ice Grape", "Fruit Soup", "Orange Juice"};
        String[] calories = {"33.3 kCal", "12.4 kCal", "22.7 kCal", "35.5 kCal", "12 kCal"};
        String[] cook = {"12 min", "27 min", "30 min", "21 min", "15 min"};
        String[] amount = {"~$ 34.8", "~$ 12.9", "~$ 45.3", "~$ 56.2", "~$ 7.89"};
        Integer[] decs = {R.string.long_text1, R.string.long_text1, R.string.long_text1, R.string.long_text1, R.string.long_text1};
        Integer[] ingredients = {R.string.long_text1, R.string.long_text1, R.string.long_text1, R.string.long_text1, R.string.long_text1};
        Integer[] steps = {R.string.long_text1, R.string.long_text1, R.string.long_text1, R.string.long_text1, R.string.long_text1};
        int[] imageDetail = {R.drawable.detail_recipe1, R.drawable.detail_recipe2, R.drawable.detail_recipe3, R.drawable.detail_recipe4, R.drawable.detail_recipe5};

        ArrayList<HomeRecipeDetail> homeRecipeDetailArrayList = new ArrayList<>();

        for (int i = 0; i < imageDetail.length; i++) {
            HomeRecipeDetail homeRecipeDetail = new HomeRecipeDetail(titles[i], calories[i], cook[i], amount[i], decs[i], ingredients[i], steps[i], imageDetail[i]);
            homeRecipeDetailArrayList.add(homeRecipeDetail);
        }

        RecipeDetailAdapter recipeDetailAdapter = new RecipeDetailAdapter(this.getActivity(), homeRecipeDetailArrayList);

        Intent intent = new Intent(getActivity(), RecipeDetail.class);
        intent.putExtra("title", titles.getClass().toString());
        intent.putExtra("calorie", calories.getClass().toString());
        intent.putExtra("cook", cook.getClass().toString());
        intent.putExtra("amount", amount.getClass().toString());
        intent.putExtra("desc", decs.getClass().toString());
        intent.putExtra("ingredient", ingredients.getClass().toString());
        intent.putExtra("step", steps.getClass().toString());
        intent.putExtra("image_detail", imageDetail.getClass().toString());
    }
}