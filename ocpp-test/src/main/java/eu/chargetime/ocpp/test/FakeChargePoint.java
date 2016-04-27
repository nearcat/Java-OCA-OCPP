package eu.chargetime.ocpp.test;

import eu.chargetime.ocpp.*;
import eu.chargetime.ocpp.model.*;
import eu.chargetime.ocpp.profiles.ClientCoreEventHandler;
import eu.chargetime.ocpp.profiles.CoreProfile;
import eu.chargetime.ocpp.v1_6.WebSocketTransmitter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by Thomas Volden on 15-Feb-16.
 */
public class FakeChargePoint
{
    private Client client;
    private Confirmation receivedConfirmation;
    private CoreProfile core;

    public FakeChargePoint() {
        core = new CoreProfile(new ClientCoreEventHandler() {});
        client = new Client(new WebSocketTransmitter(), new Queue(), core, new JSONCommunicator());
    }

    public void connect() {
        try {
            client.connect("ws://localhost:8887");
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }
    }

    public void sendBootNotification(String vendor, String model) {
        Request request = core.createBootNotificationRequest(vendor, model);
        send(request);
    }

    public void sendAuthorizeRequest(String idToken) {
        Request request = core.createAuthorizeRequest(idToken);
        send(request);
    }

    private void send(Request request)
    {
        client.send(request).whenComplete((s, ex) -> receivedConfirmation = s);
    }

    public void hasReceivedBootConfirmation(String status) {
        assertThat(receivedConfirmation, instanceOf(BootNotificationConfirmation.class));
        assertThat(((BootNotificationConfirmation)receivedConfirmation).getStatus(), is(status));
    }

    public void hasReceivedAuthorizeConfirmation(String status) {
        assertThat(receivedConfirmation, instanceOf(AuthorizeConfirmation.class));
        assertThat(((AuthorizeConfirmation)receivedConfirmation).getIdTagInfo().getStatus(), is(status));
    }

    public void disconnect()
    {
        client.disconnect();
    }
}
