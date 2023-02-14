package com.example.randomuser.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.randomuser.R;
import com.example.randomuser.databinding.FragmentUserDetailBinding;
import com.example.randomuser.model.User;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link UserListFragment}
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */
public class UserDetailFragment extends Fragment {

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_USER = "user";

    /**
     * The placeholder content this fragment is presenting.
     */
    private User mUser;

    private FragmentUserDetailBinding binding;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UserDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUserDetailBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        // Show the placeholder content as text in a TextView & in the toolbar if available.
        updateContent();
        setNewContactAction();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void updateContent() {
        Bundle args = getArguments();
        if (args != null){
            mUser = (User) args.getSerializable(ARG_USER);
            bindUserData(mUser);
        }
    }

    private void bindUserData(@NonNull User user) {
        setProfilePicture(user.getLargePicture());
        setContactData(user);
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
        binding.birthday.setText(dateFormat.format(user.getDateOfBirth()));
        setLocationData(user);
        generateQrCode(user);
    }

    private void setProfilePicture(String url) {
        Glide.with(binding.getRoot())
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.profile_picture)
                .into(binding.picture);
    }

    private void setContactData(User user) {
        String titleText = String.format("%s (%s)", user.getName(), user.getAge());
        binding.titleText.setText(titleText);
        binding.phone.setText(user.getPhone());
        binding.cell.setText(user.getCell());
        binding.mail.setText(user.getMail());
    }

    private void setLocationData(@NonNull User user) {
        binding.streetValue.setText(user.getStreet());
        binding.cityValue.setText(user.getCity());
        binding.stateValue.setText(user.getState());
        binding.countryValue.setText(user.getCountry());
    }

    private void generateQrCode(@NonNull User user) {
        final String qrStruct = "BEGIN:VCARD\n" +
                "FN:%s\n" +
                "TEL:%s\n" +
                "EMAIL;TYPE=WORK:%s\n" +
                "END:VCARD";
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            String qrContent = String.format(qrStruct,user.getName(), user.getPhone(), user.getMail());
            Bitmap bitmap = barcodeEncoder.encodeBitmap(qrContent, BarcodeFormat.QR_CODE, 400, 400);
            binding.qrContainer.setImageBitmap(bitmap);
        } catch(Exception e) {

        }
    }

    private void setNewContactAction() {
        binding.newContact.setOnClickListener(v -> {
            Intent intent = getNewContactIntent();
            startActivity(intent);
        });
    }

    private Intent getNewContactIntent() {
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME,mUser.getName());
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, mUser.getMail())
                .putExtra(ContactsContract.Intents.Insert.PHONE, mUser.getPhone());
        return intent;
    }
}