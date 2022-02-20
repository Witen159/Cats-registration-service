package banks.classes.client;

import banks.tools.BankException;

public class ClientBuilder implements IClientBuilder {
    private Client _client = new Client();

    public ClientBuilder() {
        this.reset();
    }

    public void reset() {

        this._client = new Client();
    }

    public void buildId(int id) {
        this._client.setId(id);
    }

    public void buildName(String name) {
        this._client.setName(name);
    }

    public void buildSurname(String surname) {
        this._client.setSurname(surname);
    }

    public void buildAddress(String address) {
        this._client.setAddress(address);
    }

    public void buildPassport(int passportNumber) throws BankException {
        this._client.setPassport(passportNumber);
    }

    public void buildVerification() {
        this._client.setVerification((this._client.getAddress() != null) && (this._client.getPassportNumber() != 0));
    }

    public Client getClient() {
        Client result = this._client;
        reset();
        return result;
    }
}
