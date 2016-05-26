package group50.coupletones.auth.user;

import group50.coupletones.controller.tab.favoritelocations.map.location.FavoriteLocation;

import java.util.List;

/**
 * @author Brandon Chi
 * @since  5/5/2016
 */

/**
 * The Local User interface represents
 * a local user in the app.
 */
public interface LocalUser extends User {

  /**
   * @return The partner of the user
   */
  User getPartner();

  /**
   * Sets the partner
   * @param id The partner's user id to set
   */
  void setPartner(String id);

  /**
   * Adds a favorite location
   * @param location The location to add
   */
  void addFavoriteLocation(FavoriteLocation location);

  /**
   * Removes a favorite location
   *
   * @param location The location to remove
   */
  void removeFavoriteLocation(FavoriteLocation location);

  /**
   * Requests to partner with this user.
   *
   * @param requester The user sending the request
   */
  void requestPartner(User requester);

  /**
   * @return A list of partner ids requesting to be partner with this user. Immutable. Never null.
   */
  List<String> getPartnerRequests();
}
