package com.cultivation.javaBasicExtended.posMachine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

@SuppressWarnings({"WeakerAccess", "unused", "RedundantThrows"})
public class PosMachine {
    private List<Product> productList = new ArrayList<>();

    public void readDataSource(Reader reader) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        if (reader == null) throw new IllegalArgumentException();
        StringBuilder builder = new StringBuilder();
        int readerResult ;
        while ((readerResult = reader.read()) != -1) {
            builder.append((char) readerResult);
        }
        String resultString = builder.toString();
        ObjectMapper mapper = new ObjectMapper();
        productList = mapper.readValue(resultString, new TypeReference<List<Product>>() {
        });
        // --end-->
    }

    public String printReceipt(String barcodeContent) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        if(productList.size() == 0) throw new IllegalStateException();
        String lineSeparator = System.lineSeparator();
        String receiptHeader = "Receipts" + lineSeparator;
        String receiptBodyFormat = "%-32s%-11d%d" + lineSeparator;
        String receiptFooter = "Price: %d" + lineSeparator;
        String receiptSplitLine = "------------------------------------------------------------" + lineSeparator;
        if (StringUtils.isBlank(barcodeContent) || barcodeContent.equals("[]")) {
            return receiptHeader +
                    receiptSplitLine +
                    receiptSplitLine +
                    String.format(receiptFooter, 0);
        }
        LinkedHashMap<Product, Integer> totalBuyProducts = new LinkedHashMap<>();
        List<List<Product>> needToPayItems = new ArrayList<>();
        List<String> barCodes = new ObjectMapper().readValue(barcodeContent, new TypeReference<List<String>>() {
        });
        barCodes.forEach(code ->
            {
                Product buyProduct = new Product();
                for (int index = 0; index < productList.size(); index++) {
                    if (productList.get(index).getId().equals(code)) {
                        buyProduct = productList.get(index);
                        break;
                    }
                }
                if (totalBuyProducts.containsKey(buyProduct)) {
                    totalBuyProducts.put(buyProduct, totalBuyProducts.get(buyProduct) + 1);
                } else {
                    totalBuyProducts.put(buyProduct, 1);
                }
            });
        final int[] totalPrice = {0};
        StringBuilder receiptBody = new StringBuilder();
        totalBuyProducts.forEach((product, integer) -> {
            receiptBody.append(String.format(receiptBodyFormat, product.getName(), product.getPrice(), integer));
            totalPrice[0] += product.getPrice() * integer;
        });
        return receiptHeader +
                receiptSplitLine +
                receiptBody +
                receiptSplitLine +
                String.format(receiptFooter, totalPrice[0]);
        // --end-->
    }
}

@SuppressWarnings("unused")
class Product {
    private String id;
    private String name;
    private Integer price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;

        Product other = (Product) obj;

        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}