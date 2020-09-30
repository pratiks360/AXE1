package util;

import models.Product;
import models.Promotion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PromoCalculatorImpl implements PromoCalculator {

    /*
    Author: Pratik Shukla
    Company: AXE1
    Mailto: pratiks360@gmail.com
    */


    public long generateTotalForCombos(List<Product> products, Map<String, Promotion> promos) {
        //get individual product quantities
        Map<String, Long> counts =
                products.stream().collect(Collectors.groupingBy(e -> e.getSKU_ID(), Collectors.counting()));

        ArrayList<Integer> negatives = new ArrayList<>();
        for (Product p : products) {
            negatives.add(p.getPrice());
        }
        boolean isNegativePrice = negatives.stream().anyMatch(a -> a < 0);
        if (isNegativePrice) {
            return -1;
        }


        String avoidconcurrent = "NULL";
        long total = 0;
        long result = 0;
        for (Map.Entry<String, Long> entry : counts.entrySet()) {
            if (avoidconcurrent.equalsIgnoreCase(entry.getKey())) {
                continue;
            }

            //Identify the product
            Product ind = products.stream()
                    .filter(product -> entry.getKey().equals(product.getSKU_ID()))
                    .findAny()
                    .orElse(null);


            if ("NONE".equalsIgnoreCase(promos.get(entry.getKey()).getComboWith())) {
                result = calculateProductPriceNonCombo(entry.getKey(), entry.getValue(), promos.get(entry.getKey()), ind);

            } else {
                String couple = promos.get(entry.getKey()).getComboWith();

                //Skip the second combo product
                boolean idExists = products.stream()
                        .anyMatch(str -> couple.equalsIgnoreCase(str.getSKU_ID()));


                if (idExists) {
                    avoidconcurrent = couple;

                    Map<String, Long> combosCount = new HashMap<>();

                    long firstQuantity = counts.get(entry.getKey());
                    long secondQuantity = counts.get(couple);
                    combosCount.put(entry.getKey(), firstQuantity);
                    combosCount.put(couple, secondQuantity);
                    List<Product> combos = new ArrayList<>();
                    combos.add(ind);
                    Product ind2 = products.stream()
                            .filter(product -> couple.equals(product.getSKU_ID()))
                            .findAny()
                            .orElse(null);
                    combos.add(ind2);


                    result = calculateProductCombo(combosCount, promos.get(entry.getKey()), combos);
                } else {
                    result = counts.get(entry.getKey()) * ind.getPrice();
                }
            }

            total += result;


        }


        return total;
    }

    @Override
    public long generateTotalForDiscounts(List<Product> products, Map<String, Promotion> promos) {
        return 0;
    }


    public long calculateProductCombo(Map<String, Long> combosCount, Promotion promo, List<Product> products) {

        String firstProduct = products.get(0).getSKU_ID();
        String secondProduct = products.get(1).getSKU_ID();
        long firstQuantity = combosCount.get(products.get(0).getSKU_ID());
        long secondQuantity = combosCount.get(products.get(1).getSKU_ID());
        long total;


        long greater = firstQuantity - secondQuantity;

        if (greater == 0) {
            return promo.getRate() * firstQuantity;
        }

        if (greater < 0) {
            total = (promo.getRate() * firstQuantity) + (Math.abs(greater) * products.get(1).getPrice());
        } else
            total = (promo.getRate() * secondQuantity) + (Math.abs(greater) * products.get(0).getPrice());

        return total;
    }


    public long calculateProductPriceNonCombo(String product, long quantity, Promotion promo, Product ind) {

        long res = 0;

        int moq = promo.getMin_order_elegibility();
        res = quantity % moq;
        if (res == 0) {
            return promo.getRate();
        }
        int totatPromoOfProduct = (int) ((quantity - 1) / moq);

        return (totatPromoOfProduct * promo.getRate()) + res * ind.getPrice();


    }


}
