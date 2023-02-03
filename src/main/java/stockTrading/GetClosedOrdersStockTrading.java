package stockTrading;

import lombok.*;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import stockTrading.OrderType;

import javax.swing.text.Utilities;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class GetClosedOrdersStockTrading {

    private static final AtomicInteger idGenerator = new AtomicInteger(1000);

    public  HashMap<String, HashMap<Integer, PriorityQueue<Order>>> buyOrders = new HashMap<>();
    public  HashMap<String, HashMap<Integer, PriorityQueue<Order>>> sellOrders = new HashMap<>();

    @Data
    @Builder(toBuilder = true)
    @AllArgsConstructor
    public static class Order{
        private Integer id;
        private int price;
        private int qty;
        private String productType;
        private OrderType orderType;
        private OrderStatus orderStatus;

    }

    /**
     * Can improve code by using more generic method
     * Map<OrderType, Map<String, Map<Integer, PriorityQueue<Order>>>> orderTypeToOrderBook = new HashMap<>();
     * orderTypeToOrderBook.put(OrderType.BUY, buyOrders);
     * orderTypeToOrderBook.put(OrderType.SELL, sellOrders);
     *
     * Map<String, Map<Integer, PriorityQueue<Order>>> orderBook = orderTypeToOrderBook.get(orderType);
     * @param price
     * @param qty
     * @param productType
     * @param orderType
     * @return
     */
    public int addOrder(int price, int qty, String productType, OrderType orderType){
        Order order = Order.builder().price(price).orderType(orderType)
                .orderStatus(OrderStatus.OPEN)
                .id(idGenerator.getAndIncrement())
                .productType(productType).qty(qty).build();
        HashMap<String, HashMap<Integer,PriorityQueue<Order>>> orderBook = (orderType == OrderType.BUY) ? buyOrders : sellOrders;

        orderBook.putIfAbsent(productType, new HashMap<>());
        HashMap<Integer,PriorityQueue<Order>> innerMap = orderBook.get(productType);
        innerMap.putIfAbsent(price, new PriorityQueue<>(Comparator.comparingInt(Order::getId)));
        synchronized (innerMap.get(price)) {
            innerMap.get(price).offer(order);
        }
        return order.getId();
    }

    public List<Order> getBacklogOrders(String productType, Integer price) throws ProductNotFoundException, PriceNotFoundException {
        List<Order> openOrders = new ArrayList<>();

        if(!CollectionUtils.isEmpty(buyOrders) && !buyOrders.containsKey(productType) || (!CollectionUtils.isEmpty(sellOrders) && !sellOrders.containsKey(productType))){
            throw new ProductNotFoundException("Product is not found in buy/ sell orders");
        }
        if(!CollectionUtils.isEmpty(buyOrders) && !buyOrders.get(productType).containsKey(price) || (!CollectionUtils.isEmpty(sellOrders) && !sellOrders.containsKey(productType))){
            throw new PriceNotFoundException(String.format("Price of productType %s is not found in buy/ sell orders", productType));
        }
        synchronized (productType + price){
            PriorityQueue<Order> buyOrdersQueue = buyOrders.get(productType).get(price);
            PriorityQueue<Order> sellOrdersQueue = sellOrders.get(productType).get(price);
            while (!buyOrdersQueue.isEmpty() && !sellOrdersQueue.isEmpty()) {
                int buyQty = buyOrdersQueue.peek().getQty();
                int sellQty = sellOrdersQueue.peek().getQty();
                int minQtyReConcillable = Math.min(sellQty, buyQty);

                buyOrdersQueue.peek().setQty(buyQty - minQtyReConcillable);
                sellOrdersQueue.peek().setQty(sellQty - minQtyReConcillable);

                if (buyOrdersQueue.peek().getQty() == 0) {
                    buyOrdersQueue.peek().setOrderStatus(OrderStatus.CLOSED);
                    buyOrdersQueue.poll();
                }
                if (sellOrdersQueue.peek().getQty() == 0) {
                    sellOrdersQueue.peek().setOrderStatus(OrderStatus.CLOSED);
                    sellOrdersQueue.poll();
                }
            }
            for (Order o : sellOrdersQueue)
                openOrders.add(o);
            for (Order o : buyOrdersQueue)
                openOrders.add(o);
        }
        return openOrders;
    }


    @Test
    public void addOrderBuyType() {
        int id = addOrder(12, 4, "maza", OrderType.BUY);
        Assert.assertEquals(id,1000);
    }

    @Test
    public void addOrderSellType() {
        int id = addOrder(12, 4, "maza", OrderType.SELL);
        Assert.assertEquals(id,1001);
    }

    @Test
    public void getOpenOrders() throws PriceNotFoundException, ProductNotFoundException {
        int id = addOrder(12, 4, "maza", OrderType.BUY);
        Assert.assertEquals(id,1000);
        int id2 = addOrder(12, 2, "maza", OrderType.SELL);
        Assert.assertEquals(id2,1001);
        List<Order> openorder = getBacklogOrders("maza", 12);
        int openOrderId = openorder.get(0).getId();
        Assert.assertEquals(openOrderId, 1000);
    }
}
