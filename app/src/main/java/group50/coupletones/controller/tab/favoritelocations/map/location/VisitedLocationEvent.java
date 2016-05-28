package group50.coupletones.controller.tab.favoritelocations.map.location;

import android.location.Address;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

import group50.coupletones.util.sound.VibeTone;
import group50.coupletones.util.storage.Storable;
import group50.coupletones.util.storage.Storage;

/**
 * @Author Joseph
 * @Since 5/21/16
 */
public class VisitedLocationEvent implements Storable {

  private FavoriteLocation location;
  private Date timeVisited;
  private Date timeLeft;

  //Should only be used when loading.
  public VisitedLocationEvent()
  {
    location = new FavoriteLocation();
  }

  public VisitedLocationEvent(String name, LatLng position, Date timeVisited, Date timeLeft, VibeTone tone)
  {
    location = new FavoriteLocation(name, position, 0, tone);
    this.timeVisited = timeVisited;
    this.timeLeft = timeLeft;
  }

  /**
   *Constructs a new visited location from the passed favorite location.
   */
  public VisitedLocationEvent(FavoriteLocation favoriteLocation, Date timeVisited)
  {
    this.location = favoriteLocation;
    this.timeVisited = timeVisited;
  }

  public String getName()
  {
    return location.getName();
  }

  public LatLng getPosition()
  {
    return location.getPosition();
  }

  public Address getAddress()
  {
    return location.getAddress();
  }

  public Date getTimeVisited()
  {
    return timeVisited;
  }

  public Date getTimeLeft()
  {
    return timeLeft;
  }

  public VibeTone getVibeTone()
  {
    return location.getTone();
  }

  @Override
  public void save(Storage storage)
  {
    location.save(storage);
    storage.setLong("date", timeVisited.getTime());
  }

  @Override
  public void load(Storage storage)
  {
    location.load(storage);
    timeVisited = new Date(storage.getLong("date"));
  }

  public boolean equals(Object object)
  {
    try {
      VisitedLocationEvent other = (VisitedLocationEvent) object;
      if (!location.equals(other.location)) return false;
      if (!timeVisited.equals(other.timeVisited)) return false;
      if (!timeLeft.equals(other.timeLeft)) return false;
      return true;
    }
    catch (ClassCastException e)
    {
      return false;
    }
  }

}
