package util;

import models.Product;
import models.Promotion;

import java.util.List;
import java.util.Map;

public interface PromoCalculator {
    long generateTotalForCombos(List<Product> products, Map<String, Promotion> promos);

    long generateTotalForDiscounts(List<Product> products, Map<String, Promotion> promos);


}
