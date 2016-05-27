package group50.coupletones.controller.tab.partnerslocations;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import group50.coupletones.R;
import group50.coupletones.controller.MainActivity;
import group50.coupletones.controller.tab.TabFragment;
import group50.coupletones.controller.tab.favoritelocations.map.MapFragment;

/**
 * @author Joanne Cho
 */

/**
 * A simple {@link Fragment} subclass for the Partner's Favorite Locations tab.
 */
public class PartnersFavoritesFragment extends TabFragment<Object> {
  private RecyclerView partnersList;
  private ListAdapterFavorites adapter;

  public PartnersFavoritesFragment() {
    super(Object.class);
  }

  /**
   * getResourceID
   * @return - Partner's favorite locations fragment
   */
  @Override
  protected int getResourceId() {
    return R.layout.fragment_partners_list;
  }

  /**
   * onCreateView
   * @return - View of app
   */
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_partners_list, container, false);
    partnersList = (RecyclerView) v.findViewById(R.id.partners_static_list);
    partnersList.setLayoutManager(new LinearLayoutManager(getActivity()));
    adapter = new ListAdapterFavorites(PartnerFavoritesData.getPartnerLocations(), getActivity());
    partnersList.setAdapter(adapter);
    v.findViewById(R.id.partner_mapview_button).setOnClickListener(evt -> {
      MainActivity act = (MainActivity) getActivity();
      ((MapFragment)act.getTabs().get(R.id.map)).setIsUser(false);
      act.setFragment(act.getTabs().get(R.id.map));
    });
    return v;
  }
}
