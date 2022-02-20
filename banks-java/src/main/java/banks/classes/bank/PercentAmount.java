package banks.classes.bank;

import banks.tools.BankException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PercentAmount {
    private ArrayList<Integer> _moneyBorders;
    private ArrayList<Double> _percents;

    public PercentAmount(ArrayList<Integer> moneyBorders, ArrayList<Double> percents) throws BankException {
        setup(moneyBorders, percents);
    }

    public void changePercentAmount(ArrayList<Integer> moneyBorders, ArrayList<Double> percents) throws BankException {
        setup(moneyBorders, percents);
    }

    public double getCurrentPercent(double money) {
        for (int i = 0; i < _moneyBorders.size(); i++) {
            if (money < _moneyBorders.get(i))
                return _percents.get(i);
        }

        return _percents.get(_percents.size() - 1);
    }

    private void setup(ArrayList<Integer> moneyBorders, ArrayList<Double> percents) throws BankException {
        if (moneyBorders.size() + 1 != percents.size())
            throw new BankException("Incorrect money borders and percents match");
        _moneyBorders = moneyBorders;
        _percents = percents;
    }

    public List<Double> getPercents() {
        return Collections.unmodifiableList(_percents);
    }

    public List<Integer> getMoneyBorders() {
        return Collections.unmodifiableList(_moneyBorders);
    }
}
