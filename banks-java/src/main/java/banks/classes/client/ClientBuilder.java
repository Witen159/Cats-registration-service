package banks.classes.client;

import banks.tools.BankException;

public class ClientBuilder implements IClientBuilder {
    private Client _client = new Client();

    public ClientBuilder() {
        this.Reset();
    }

    public void Reset() {

        this._client = new Client();
    }

    public void BuildId(int id) {
        this._client.setId(id);
    }

    public void BuildName(String name) {
        this._client.setName(name);
    }

    public void BuildSurname(String surname) {
        this._client.setSurname(surname);
    }

    public void BuildAddress(String address) {
        this._client.SetAddress(address);
    }

    public void BuildPassport(int passportNumber) throws BankException {
        this._client.SetPassport(passportNumber);
    }

    public void BuildVerification() {
        this._client.setVerification((this._client.getAddress() != null) && (this._client.getPassportNumber() != 0));
    }

    public Client GetClient() {
        Client result = this._client;

        return result;
    }
}
