package com.example.randomuser.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.randomuser.MyApplication;
import com.example.randomuser.R;
import com.example.randomuser.databinding.FragmentUserListBinding;

import javax.inject.Inject;


public class UserListFragment extends Fragment {

    private FragmentUserListBinding binding;
    @Inject
    ListViewModel listViewModel;

    private UserAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUserListBinding.inflate(inflater, container, false);
        ((MyApplication) getActivity().getApplication()).appComponent.inject(this);
        getUsersInformation();
        addObservers();
        return binding.getRoot();

    }

    private void addObservers() {
        listViewModel.getErrorMD().observe(getViewLifecycleOwner(), s -> {
             if (s != null){
                 Toast.makeText(requireContext(), s, Toast.LENGTH_LONG).show();
             }
        });
        listViewModel.getListUserMD().observe(getViewLifecycleOwner(), users -> {
            mAdapter.updateList(users);
        });
    }

    private void getUsersInformation() {
        listViewModel.getHundredUsers();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = binding.itemList;
        View itemDetailFragmentContainer = view.findViewById(R.id.item_detail_nav_container);

        setupRecyclerView(recyclerView, itemDetailFragmentContainer);
    }

    private void setupRecyclerView(
            RecyclerView recyclerView,
            View itemDetailFragmentContainer
    ) {
        UserAdapter.OnItemClickListener onItemClickListener = user -> {
            Bundle arguments = new Bundle();
            arguments.putSerializable(UserDetailFragment.ARG_USER, user);
            if (itemDetailFragmentContainer != null)
                Navigation.findNavController(itemDetailFragmentContainer)
                        .navigate(R.id.fragment_item_detail, arguments);
            else
                Navigation.findNavController(binding.getRoot())
                        .navigate(R.id.show_item_detail, arguments);
        };
        mAdapter = new UserAdapter(onItemClickListener);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}