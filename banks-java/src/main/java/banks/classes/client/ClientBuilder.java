package banks.classes.client;

import banks.tools.BankException;

public class ClientBuilder implements IClientBuilder {
    private Client client = new Client();

    public ClientBuilder() {
        this.reset();
    }

    public void reset() {

        this.client = new Client();
    }

    public void buildId(int id) {
        this.client.setId(id);
    }

    public void buildName(String name) {
        this.client.setName(name);
    }

    public void buildSurname(String surname) {
        this.client.setSurname(surname);
    }

    public void buildAddress(String address) {
        this.client.setAddress(address);
    }

    public void buildPassport(int passportNumber) throws BankException {
        this.client.setPassport(passportNumber);
    }

    public void buildVerification() {
        this.client.setVerification((this.client.getAddress() != null) && (this.client.getPassportNumber() != 0));
    }

    public Client getClient() {
        Client result = this.client;
        reset();
        return result;
    }
}
