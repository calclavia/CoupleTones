package group50.coupletones.controller.tab;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import group50.coupletones.R;

/**
 * A simple {@link Fragment} subclass for the Favorite Locations tab.
 * Activities that contain this fragment must implement the {@link Listener} interface to handle interaction events.
 * Use the {@link SettingsFragment#build} factory class to create an instance of this fragment.
 */
public class SettingsFragment extends TabFragment<SettingsFragment.Listener> {

    public SettingsFragment() {
        super(Listener.class);
    }

    /**
     * Use this factory method to create a new instance of SettingsFragment.
     */
    public static SettingsFragment build() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        // TODO: Set arguments
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_settings;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //TODO: Read arguments
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        Typeface pierSans = Typeface.createFromAsset(getActivity().getAssets(),
                getString(R.string.pier_sans));
        TextView yourProfileText = (TextView) v.findViewById(R.id.my_profile_header);
        TextView yourNameText = (TextView) v.findViewById(R.id.your_name_header);
        TextView yourName = (TextView) v.findViewById(R.id.your_name);
        TextView yourAccountText = (TextView) v.findViewById(R.id.your_account_header);
        TextView yourAccount = (TextView) v.findViewById(R.id.your_email);
        TextView partnersProfileText = (TextView) v.findViewById(R.id.partners_profile_text);
        TextView partnerNameText = (TextView) v.findViewById(R.id.partner_name_header);
        TextView partnerName = (TextView) v.findViewById(R.id.partner_name);
        TextView partnerAccountText = (TextView) v.findViewById(R.id.partner_account_header);
        TextView partnerAccount = (TextView) v.findViewById(R.id.partner_email);
        yourProfileText.setTypeface(pierSans);
        yourNameText.setTypeface(pierSans);
        yourName.setTypeface(pierSans);
        yourAccountText.setTypeface(pierSans);
        yourAccount.setTypeface(pierSans);
        partnersProfileText.setTypeface(pierSans);
        partnerNameText.setTypeface(pierSans);
        partnerName.setTypeface(pierSans);
        partnerAccountText.setTypeface(pierSans);
        partnerAccount.setTypeface(pierSans);
        return v;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface Listener {
        // TODO: Fill with interface methods
    }
}