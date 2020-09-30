import models.PriceListMaster;
import models.Product;
import models.Promotion;
import org.junit.Assert;
import org.junit.Test;
import util.PromoCalculatorImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculatorTest {


    @Test
    public void checkNegatives() {
        List<Product> productList = new ArrayList<Product>();
        Map<String, Promotion> promos = new HashMap<>();
        Promotion promotion2 = new Promotion();
        promotion2.setSKU_ID("B");
        promotion2.setMin_order_elegibility(2);
        promotion2.setComboWith("NONE");
        promos.put("B", promotion2);
        Product x = new Product();
        x.setSKU_ID("B");
        x.setPrice(-78);
        productList.add(x);
        PromoCalculatorImpl impl = new PromoCalculatorImpl();
        Assert.assertEquals(-1, impl.generateTotalForCombos(productList, promos));
    }

    @Test
    public void positiveScenario() {

        List<Promotion> promotions = new ArrayList<Promotion>();
        Map<String, Promotion> promos = new HashMap<>();
        Promotion promotion1 = new Promotion();
        promotion1.setSKU_ID("A");
        promotion1.setMin_order_elegibility(3);
        promotion1.setRate(PriceListMaster.A3_COMBO);
        promotion1.setComboWith("NONE");
        promotions.add(promotion1);
        Promotion promotion2 = new Promotion();
        promotion2.setSKU_ID("B");
        promotion2.setMin_order_elegibility(2);
        promotion2.setComboWith("NONE");
        promotion2.setRate(PriceListMaster.B2_COMBO);
        promotions.add(promotion2);
        Promotion promotion3 = new Promotion();
        promotion3.setComboWith("D");
        promotion3.setRate(PriceListMaster.CnD_COMBO);

        Promotion promotion4 = new Promotion();
        promotion4.setComboWith("C");
        promotion4.setRate(PriceListMaster.CnD_COMBO);
        promos.put("A", promotion1);
        promos.put("B", promotion2);
        promos.put("C", promotion3);
        promos.put("D", promotion4);

        //define products
        //define products
        List<Product> productList = new ArrayList<Product>();
        Product a = new Product();
        a.setSKU_ID("A");
        a.setPrice(PriceListMaster.A_VAL);
        productList.add(a);
        productList.add(a);
        productList.add(a);
        productList.add(a);
        productList.add(a);


        Product b = new Product();
        b.setSKU_ID("B");
        b.setPrice(PriceListMaster.B_VAL);
        productList.add(b);
        productList.add(b);
        productList.add(b);
        productList.add(b);
        productList.add(b);

        Product c = new Product();
        c.setSKU_ID("C");
        c.setPrice(PriceListMaster.C_VAL);
        productList.add(c);


        PromoCalculatorImpl impl = new PromoCalculatorImpl();
        Assert.assertEquals(370, impl.generateTotalForCombos(productList, promos));
    }

    @Test
    public void nonComboProductchecker() {
        Promotion promotion1 = new Promotion();
        promotion1.setSKU_ID("A");
        promotion1.setMin_order_elegibility(3);
        promotion1.setRate(PriceListMaster.A3_COMBO);
        promotion1.setComboWith("NONE");

        Product a = new Product();
        a.setSKU_ID("A");
        a.setPrice(PriceListMaster.A_VAL);
        PromoCalculatorImpl impl = new PromoCalculatorImpl();
        Assert.assertEquals(440, impl.calculateProductPriceNonCombo("A", 10, promotion1, a));

    }

    @Test
    public void comboProductChecker() {
        List<Product> productList = new ArrayList<Product>();
        Map<String, Promotion> promos = new HashMap<>();
        Promotion promotion3 = new Promotion();
        promotion3.setComboWith("D");
        promotion3.setRate(PriceListMaster.CnD_COMBO);

        Promotion promotion4 = new Promotion();
        promotion4.setComboWith("C");
        promotion4.setRate(PriceListMaster.CnD_COMBO);
        promos.put("C", promotion3);
        promos.put("D", promotion4);


        Product c = new Product();
        c.setSKU_ID("C");
        c.setPrice(PriceListMaster.C_VAL);
        productList.add(c);

        Product d = new Product();
        d.setSKU_ID("D");
        d.setPrice(PriceListMaster.D_VAL);
        d.setPrice(PriceListMaster.D_VAL);
        productList.add(d);
        PromoCalculatorImpl impl = new PromoCalculatorImpl();
        Assert.assertEquals(30, impl.generateTotalForCombos(productList, promos));

    }

}
