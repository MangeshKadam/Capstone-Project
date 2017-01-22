package com.udacity.capstone.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.capstone.data.AartiKathaDetails;
import com.udacity.capstone.R;
import com.udacity.capstone.data.AartiConstants;
import com.udacity.capstone.data.AppConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AartiKathaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AartiKathaFragment extends Fragment {


    private View view;

    @Bind(R.id.grid_view)
    RecyclerView mRecyclerView;

    private ArrayList<AartiKathaDetails> mAartiKathaDetailsArrayList;

    String[] names = null;

    RecyclerViewAdapter adapter;

    private int[] images = null;

    String category = null;

    public AartiKathaFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AartiKathaFragment newInstance(String category, int[] imagesArray, String[] namesArray) {
        AartiKathaFragment fragment = new AartiKathaFragment();
        Bundle args = new Bundle();
        args.putString(AppConstants.CATEGORY, category);
        args.putIntArray(AppConstants.IMAGES, imagesArray);
        args.putStringArray(AppConstants.NAMES, namesArray);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            category = getArguments().getString(AppConstants.CATEGORY);
            names = getArguments().getStringArray(AppConstants.NAMES);
            images = getArguments().getIntArray(AppConstants.IMAGES);
        }
        mAartiKathaDetailsArrayList = new ArrayList();
        int size = images.length;
        for (int i = 0; i < size; i++) {
            mAartiKathaDetailsArrayList.add(new AartiKathaDetails(names[i], i, images[i]));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initGridView();
        if (mAartiKathaDetailsArrayList != null) {
            initRecyclerView();
        }


        return view;
    }


    private void initRecyclerView() {
        adapter = new RecyclerViewAdapter(getActivity(), mAartiKathaDetailsArrayList);
        mRecyclerView.setAdapter(adapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mRecyclerView.scheduleLayoutAnimation();
        }
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, AartiKathaDetails viewModel) {
                if(category.equalsIgnoreCase(AppConstants.CATEGORY_AARTI)) {
                    showAartiCollectionActivity(viewModel);
                }
                else {
                    showVratKathaActivity(viewModel);
                }
            }
        });


    }


    private void initGridView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), (R.dimen.item_offset));
        mRecyclerView.addItemDecoration(itemDecoration);

    }


    private void showAartiCollectionActivity(AartiKathaDetails dataModel) {
        String[] aartiList = null;
        Intent intentAartis = null;
        ArrayList<String> al = null;
        Collection l = null;
        switch (dataModel.getIndex()) {
            case 0: //god_ganesh

                ((BaseActivity)getActivity()).sendEvent("AARTI", "GANESH");

                aartiList = getActivity().getResources().getStringArray(R.array.ganesh_aartis);
                intentAartis = new Intent(getActivity(), LordAartiCollectionActivity.class);
                l = Arrays.asList(aartiList);
                al = new ArrayList<String>();
                al.addAll(l);

                intentAartis.putStringArrayListExtra(AppConstants.AARTI_LIST, al);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_FILE_LIST, AartiConstants.ganesh_aartis);
                break;
            case 1: //god_shankar

                ((BaseActivity)getActivity()).sendEvent("AARTI", "SHANKAR");
                aartiList = getActivity().getResources().getStringArray(R.array.shankar_aartis);
                intentAartis = new Intent(getActivity(), LordAartiCollectionActivity.class);
                l = Arrays.asList(aartiList);
                al = new ArrayList<String>();
                al.addAll(l);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_LIST, al);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_FILE_LIST, AartiConstants.shankar_aartis);
                break;
            case 2: //god krishna
                ((BaseActivity)getActivity()).sendEvent("AARTI", "KRISHNA");
                aartiList = getActivity().getResources().getStringArray(R.array.krishna_aartis);
                intentAartis = new Intent(getActivity(), LordAartiCollectionActivity.class);
                l = Arrays.asList(aartiList);
                al = new ArrayList<String>();
                al.addAll(l);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_LIST, al);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_FILE_LIST, AartiConstants.krishna_aartis);
                break;
            case 3: //god ram
                ((BaseActivity)getActivity()).sendEvent("AARTI", "RAM");
                aartiList = getActivity().getResources().getStringArray(R.array.ram_aartis);
                intentAartis = new Intent(getActivity(), LordAartiCollectionActivity.class);
                l = Arrays.asList(aartiList);
                al = new ArrayList<String>();
                al.addAll(l);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_LIST, al);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_FILE_LIST, AartiConstants.ram_aartis);
                break;
            case 4: //god hanuman
                ((BaseActivity)getActivity()).sendEvent("AARTI", "HANUMAN");
                aartiList = getActivity().getResources().getStringArray(R.array.hanuman_aartis);
                intentAartis = new Intent(getActivity(), LordAartiCollectionActivity.class);
                l = Arrays.asList(aartiList);
                al = new ArrayList<String>();
                al.addAll(l);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_LIST, al);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_FILE_LIST, AartiConstants.hanuman_aartis);
                break;
            case 5: //god vishnu
                ((BaseActivity)getActivity()).sendEvent("AARTI", "VISHNU");
                aartiList = getActivity().getResources().getStringArray(R.array.vishnu_aartis);
                intentAartis = new Intent(getActivity(), LordAartiCollectionActivity.class);
                l = Arrays.asList(aartiList);
                al = new ArrayList<String>();
                al.addAll(l);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_LIST, al);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_FILE_LIST, AartiConstants.vishnu_aartis);
                break;
            case 6: //god mahalaxmi
                ((BaseActivity)getActivity()).sendEvent("AARTI", "MAHALAXMI");
                aartiList = getActivity().getResources().getStringArray(R.array.lakshmi_aartis);
                intentAartis = new Intent(getActivity(), LordAartiCollectionActivity.class);
                l = Arrays.asList(aartiList);
                al = new ArrayList<String>();
                al.addAll(l);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_LIST, al);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_FILE_LIST, AartiConstants.lakshmi_aartis);
                break;
            case 7: //god durga
                ((BaseActivity)getActivity()).sendEvent("AARTI", "DURGA");
                aartiList = getActivity().getResources().getStringArray(R.array.durga_and_kali_aartis);
                intentAartis = new Intent(getActivity(), LordAartiCollectionActivity.class);
                l = Arrays.asList(aartiList);
                al = new ArrayList<String>();
                al.addAll(l);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_LIST, al);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_FILE_LIST, AartiConstants.durga_aartis);
                break;
            case 8: //god shani
                ((BaseActivity)getActivity()).sendEvent("AARTI", "SHANIDEV");
                aartiList = getActivity().getResources().getStringArray(R.array.shani_aartis);
                intentAartis = new Intent(getActivity(), LordAartiCollectionActivity.class);
                l = Arrays.asList(aartiList);
                al = new ArrayList<String>();
                al.addAll(l);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_LIST, al);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_FILE_LIST, AartiConstants.shri_shani);
                break;
            case 9: //god gayatri
                ((BaseActivity)getActivity()).sendEvent("AARTI", "GAYATRI");
                aartiList = getActivity().getResources().getStringArray(R.array.gayatri_and_saraswati_aartis);
                intentAartis = new Intent(getActivity(), LordAartiCollectionActivity.class);
                l = Arrays.asList(aartiList);
                al = new ArrayList<String>();
                al.addAll(l);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_LIST, al);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_FILE_LIST, AartiConstants.gayatri_aartis);
                break;
            case 10: //god surya
                ((BaseActivity)getActivity()).sendEvent("AARTI", "SURYADEV");
                aartiList = getActivity().getResources().getStringArray(R.array.surya_navgrah_aartis);
                intentAartis = new Intent(getActivity(), LordAartiCollectionActivity.class);
                l = Arrays.asList(aartiList);
                al = new ArrayList<String>();
                al.addAll(l);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_LIST, al);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_FILE_LIST, AartiConstants.surya_aartis);
                break;
            default:
                intentAartis = new Intent(getActivity(), LordAartiCollectionActivity.class);
                break;
        }
        intentAartis.putExtra(AppConstants.IMAGE_ID, images[dataModel.getIndex()]);
        intentAartis.putExtra(AppConstants.GOD_TITLE, dataModel.getAartiName());
        startActivity(intentAartis);
    }



    private void showVratKathaActivity(AartiKathaDetails dataModel) {
        String[] aartiList = null;
        Intent intentAartis = null;
        ArrayList<String> al = null;
        Collection l = null;
        switch (dataModel.getIndex()) {

            case 0: //Ekadashi
                ((BaseActivity)getActivity()).sendEvent("VRATKATHA", "EKADASHI");
                aartiList = getActivity().getResources().getStringArray(R.array.ekadashi_vratkatha);
                intentAartis = new Intent(getActivity(), LordAartiCollectionActivity.class);
                l = Arrays.asList(aartiList);
                al = new ArrayList<>();
                al.addAll(l);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_LIST, al);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_FILE_LIST, AartiConstants.ekadashi_vrat_katha);
                break;
            case 1: //Saptahik
                ((BaseActivity)getActivity()).sendEvent("VRATKATHA", "SAPTAHIK");
                aartiList = getActivity().getResources().getStringArray(R.array.saptahik_vratkatha);
                intentAartis = new Intent(getActivity(), LordAartiCollectionActivity.class);
                l = Arrays.asList(aartiList);
                al = new ArrayList<>();
                al.addAll(l);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_LIST, al);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_FILE_LIST, AartiConstants.saptahik_vrat_katha);
                break;
            case 2: //Vishishta
                ((BaseActivity)getActivity()).sendEvent("VRATKATHA", "VISHISHTA");
                aartiList = getActivity().getResources().getStringArray(R.array.vishista_vratkatha);
                intentAartis = new Intent(getActivity(), LordAartiCollectionActivity.class);
                l = Arrays.asList(aartiList);
                al = new ArrayList<>();
                al.addAll(l);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_LIST, al);
                intentAartis.putStringArrayListExtra(AppConstants.AARTI_FILE_LIST, AartiConstants.vishishta_vrat_katha);
                break;

            default:
                intentAartis = new Intent(getActivity(), LordAartiCollectionActivity.class);
                break;
        }
        intentAartis.putExtra(AppConstants.IMAGE_ID, images[dataModel.getIndex()]);
        intentAartis.putExtra(AppConstants.GOD_TITLE, dataModel.getAartiName());
        startActivity(intentAartis);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();

    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
