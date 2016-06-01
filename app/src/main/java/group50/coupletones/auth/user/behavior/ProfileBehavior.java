/**
 * @author Henry Mao
 * @since 4/22/16.
 */

package group50.coupletones.auth.user.behavior;

import com.google.firebase.database.GenericTypeIndicator;

import group50.coupletones.CoupleTones;
import group50.coupletones.controller.tab.favoritelocations.map.location.FavoriteLocation;
import group50.coupletones.controller.tab.favoritelocations.map.location.VisitedLocationEvent;
import group50.coupletones.network.sync.Sync;
import group50.coupletones.util.TimeUtility;
import group50.coupletones.util.properties.Properties;
import group50.coupletones.util.properties.PropertiesProvider;
import group50.coupletones.util.properties.Property;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

/**
 * Holds the behavior of user's profile. Strategy pattern.
 */
public class ProfileBehavior implements PropertiesProvider {
  @Inject
  public TimeUtility timeUtility;
  
  /**
   * Object responsible for syncing the object with database
   */
  private final Properties properties;
  private final Sync sync;
  private String id;
  /**
   * Boolean that handles global notification toggling
   */
  private boolean globalNotificationsAreOn;
  /**
   * Name of the user
   */
  private String name;
  /**
   * Email of the user
   */
  private String email;
  /**
   * The user's list of favorite location.
   */
  private List<FavoriteLocation> favoriteLocations = new LinkedList<>();
  /**
   * The user's list of visited locations.
   */
  private List<VisitedLocationEvent> visitedLocations = new LinkedList<>();

  /**
   * Creates a ConcreteUser
   */
  public ProfileBehavior(Properties properties, Sync sync) {
    CoupleTones.global().inject(this);

    this.properties = properties
      .property("id").bind(this)
      .property("name").bind(this)
      .property("email").bind(this)
      .property("favoriteLocations")
      .mark(new GenericTypeIndicator<List<FavoriteLocation>>() {
      })
      .bind(this)
      .property("visitedLocations")
      .mark(new GenericTypeIndicator<List<VisitedLocationEvent>>() {
      })
      .bind(this);

    this.sync = sync;
  }

  /**
   * @return Global notifications setting
   */
  public Boolean getGlobalNotificationsSetting() {
    return globalNotificationsAreOn;
  }

  /**
   * This function turns on/off the global notifications setting
   * @return globalNotificationsAreOn true if notifications are on, false if turned off
   */
  public Boolean setGlobalNotificationSetting(Boolean setting) {
    globalNotificationsAreOn = setting;
    return globalNotificationsAreOn;
  }

  /**
   * @return The id of the user
   */
  public String getId() {
    return id;
  }

  /**
   * @return The name of the user
   */
  public String getName() {
    return name;
  }

  /**
   * @return The email of the user
   */
  public String getEmail() {
    return email;
  }

  /**
   * @return The list of favorite locations of the user
   */
  public List<FavoriteLocation> getFavoriteLocations() {
    return favoriteLocations != null ? Collections.unmodifiableList(favoriteLocations) : Collections.emptyList();
  }

  /**
   * @return The list of visited locations of the user
   */
  public List<VisitedLocationEvent> getVisitedLocations() {
    boolean hasChanged = false;

    if (visitedLocations != null) {
      for (int i = 0; i < visitedLocations.size(); i++) {
        VisitedLocationEvent currEvent = visitedLocations.get(i);
        if(timeUtility.checkTime(currEvent)) {
          visitedLocations.remove(i);
          hasChanged = true;
        }
        if (hasChanged) {
          Property<Object> prop = properties.property("visitedLocations");
          prop.set(this.visitedLocations);
          sync.update(prop);
          prop.update();
        }
      }
      return visitedLocations;
    } else {
      return Collections.emptyList();
    }
  }

  /**
   * Adds a favorite location
   *
   * @param location The location to add
   */
  public void addFavoriteLocation(FavoriteLocation location) {
    if (favoriteLocations == null)
      favoriteLocations = new LinkedList<>();
    favoriteLocations.add(location);

    Property<Object> prop = properties.property("favoriteLocations");
    sync.update(prop);
    prop.update();
  }

  /**
   * Adds a visited location
   *
   * @param visitedLocation The visited location to add.
   */
  public void addVisitedLocation(VisitedLocationEvent visitedLocation) {
    if (visitedLocations == null)
      visitedLocations = new LinkedList<>();
    visitedLocations.add(visitedLocation);

    Property<Object> prop = properties.property("visitedLocations");
    prop.set(this.visitedLocations);
    sync.update(prop);
    prop.update();
  }


  /**
   * Removes a favorite location
   *
   * @param location The location to remove
   */
  public void removeFavoriteLocation(FavoriteLocation location) {
    if (favoriteLocations != null) {
      favoriteLocations.remove(location);

      Property<Object> prop = properties.property("favoriteLocations");
      prop.set(this.favoriteLocations);
      sync.update(prop);
      prop.update();
    }
  }

  @Override
  public Properties getProperties() {
    return properties;
  }
}
