/**
 * @author Henry Mao
 * @since 4/22/16.
 */

package group50.coupletones.auth;

import group50.coupletones.util.storage.Storage;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import group50.coupletones.controller.AddPartnerActivity;

/**
 * Represents a User logged in via Google sign in.
 * Wraps the GoogleSignInAccount object.
 */
public class GoogleUser implements LocalUser {

  private final GoogleSignInAccount account;
  private User partner;

  /**
   * Creates a GoogleUser
   *
   * @param account The Google sign in account object
   */
  public GoogleUser(GoogleSignInAccount account) {
    this.account = account;
  }

  /**
   * @return The id of the user
   */
  @Override
  public String getId() {
    return account.getId();
  }

  /**
   * @return The name of the user
   */
  @Override
  public String getName() {
    return account.getDisplayName();
  }

  /**
   * @return The email of the user
   */
  @Override
  public String getEmail() {
    return account.getEmail();
  }

  /**
   * @return The partner of the user
   */
  @Override
  public User getPartner() {
    return partner;
  }

  /**
   * Sets partner
   *
   * @param partner
   */
  @Override
  public void setPartner(Partner partner) {
    this.partner = partner;
  }

  /**
   * Save User data onto phone
   */
  @Override
  public void save(Storage s) {

    if (getPartner() != null) {
      s.write("Partner's Name: ", getPartner().getName());
      s.setString("Partner's Email: ", getPartner().getEmail());
      s.setBoolean("hasPartner", true);
    } else
      s.setBoolean("hasPartner", false);

    // TODO: Implement Save FaveLocations
  }

  /**
   * Load User data from phone
   */
  @Override
  public void load(Storage s) {

    if (getBoolean("hasPartner")) {
      name = s.getString(partnerName);
      email = s.getString(partnerEmail);
      Partner partner = new Partner(name, email);
      setPartner(partner);
    } else
      setPartner(null);

    // TODO: Implement Load FaveLocations

  }


}
