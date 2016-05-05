package group50.coupletones.map;

import javax.inject.Inject;

import group50.coupletones.network.NetworkManager;
import group50.coupletones.network.message.Message;
import group50.coupletones.network.message.MessageType;
import group50.coupletones.network.message.OutgoingMessage;

/**
 * @author Sharmaine Manalo
 * @since 5/5/16
 */
public class LocationNotificationObserver implements NotificationObserver {
  private NetworkManager network;

  @Inject
  public LocationNotificationObserver(NetworkManager network) {
    this.network = network;
  }

  @Override
  public void onEnterLocation(VisitedLocation location) {
    network.send((OutgoingMessage) new OutgoingMessage(MessageType.SEND_LOCATION_NOTIFICATION.value)
        .setString("name",location.getName())
        .setDouble("lat", location.getPosition().latitude)
        .setDouble("long", location.getPosition().longitude)
    );
  }

}
