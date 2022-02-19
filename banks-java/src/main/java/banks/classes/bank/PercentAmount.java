package banks.classes.bank;

import banks.tools.BankException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PercentAmount {
    private ArrayList<Integer> _moneyBorders;
    private ArrayList<Double> _percents;

    public PercentAmount(ArrayList<Integer> moneyBorders, ArrayList<Double> percents) throws BankException {
        Setup(moneyBorders, percents);
    }

    // public IReadOnlyList<Integer> MoneyBorders =>_moneyBorders;
    // public IReadOnlyList<Double> Percents =>_percents;

    public void ChangePercentAmount(ArrayList<Integer> moneyBorders, ArrayList<Double> percents) throws BankException {
        Setup(moneyBorders, percents);
    }

    public double GetCurrentPercent(double money) {
        for (int i = 0; i < _moneyBorders.size(); i++) {
            if (money < _moneyBorders.get(i))
                return _percents.get(i);
        }

        return _percents.get(_percents.size() - 1);
    }

    private void Setup(ArrayList<Integer> moneyBorders, ArrayList<Double> percents) throws BankException {
        if (moneyBorders.size() + 1 != percents.size())
            throw new BankException("Incorrect money borders and percents match");
        _moneyBorders = moneyBorders;
        _percents = percents;
    }

    public List<Double> GetPercents() {
        return Collections.unmodifiableList(_percents);
    }

    public List<Integer> GetMoneyBorders() {
        return Collections.unmodifiableList(_moneyBorders);
    }
}
