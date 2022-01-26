package com.example.fruitzzz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InfoPage extends Fragment {

    View view;

    RecyclerView GridRecycle;

    private List<InfoPages> listInfoPages;

    public InfoPage() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_info_page, container, false);

        GridRecycle = view.findViewById(R.id.gridInfo);

        InfoPageAdapter infoPageAdapter = new InfoPageAdapter(getContext(), listInfoPages);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2, GridLayoutManager.VERTICAL, false);
        GridRecycle.setLayoutManager(gridLayoutManager);
        GridRecycle.setAdapter(infoPageAdapter);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listInfoPages = new ArrayList<>();
        listInfoPages.add(new InfoPages("Orange Fun Fact", "Andi Rifqial Nur", R.drawable.fix_info1, R.drawable.ic_icon_viewall));
        listInfoPages.add(new InfoPages("Grape Benefits", "Anthony Elly", R.drawable.fix_info2, R.drawable.ic_icon_viewall));
        listInfoPages.add(new InfoPages("Strawberries", "Daniel Frost", R.drawable.fix_info3, R.drawable.ic_icon_viewall));
        listInfoPages.add(new InfoPages("Kiwi for Baby", "Christina Alice", R.drawable.fix_info4, R.drawable.ic_icon_viewall));
        listInfoPages.add(new InfoPages("Apple and smile", "Ferdinand", R.drawable.fix_info5, R.drawable.ic_icon_viewall));
        listInfoPages.add(new InfoPages("Rare Berries", "Dishter Yaka", R.drawable.fix_info6, R.drawable.ic_icon_viewall));
        listInfoPages.add(new InfoPages("2 Oranges", "Ferdinand", R.drawable.fix_info1, R.drawable.ic_icon_viewall));
        listInfoPages.add(new InfoPages("A Grape", "Arnold", R.drawable.fix_info2, R.drawable.ic_icon_viewall));
        listInfoPages.add(new InfoPages("Red is good?", "Hisbergston", R.drawable.fix_info3, R.drawable.ic_icon_viewall));
        listInfoPages.add(new InfoPages("Berries", "Alexander Pure", R.drawable.fix_info6, R.drawable.ic_icon_viewall));

    }

}