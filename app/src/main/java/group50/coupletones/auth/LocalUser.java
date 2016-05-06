package group50.coupletones.auth;

import group50.coupletones.util.storage.Storable;
import group50.coupletones.util.storage.Storage;

/**
 * Created by brandon on 5/5/2016.
 */
public interface LocalUser extends User, Storable {

  /**
   * @return The partner of the user
   */
  User getPartner();

  /**
   * @param partner
   */
  void setPartner(Partner partner);

  /**
   * Save User data onto phone
   */
  void save(Storage s);

  /**
   * Load User data from phone
   */
  void load(Storage s);


}
