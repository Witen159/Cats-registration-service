package ru.itmo.banks.entity.bank;

import ru.itmo.banks.tools.BankException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PercentAmount {
    private ArrayList<Integer> moneyBorders;
    private ArrayList<Double> percents;

    public PercentAmount(ArrayList<Integer> moneyBorders, ArrayList<Double> percents) {
        setup(moneyBorders, percents);
    }

    public void changePercentAmount(ArrayList<Integer> moneyBorders, ArrayList<Double> percents) {
        setup(moneyBorders, percents);
    }

    public double getCurrentPercent(double money) {
        for (int i = 0; i < moneyBorders.size(); i++) {
            if (money < moneyBorders.get(i))
                return percents.get(i);
        }

        return percents.get(percents.size() - 1);
    }

    private void setup(ArrayList<Integer> moneyBorders, ArrayList<Double> percents) {
        if (moneyBorders.size() + 1 != percents.size())
            throw new BankException("Incorrect money borders and percents match");
        this.moneyBorders = moneyBorders;
        this.percents = percents;
    }

    public List<Double> getPercents() {
        return Collections.unmodifiableList(percents);
    }

    public List<Integer> getMoneyBorders() {
        return Collections.unmodifiableList(moneyBorders);
    }
}
