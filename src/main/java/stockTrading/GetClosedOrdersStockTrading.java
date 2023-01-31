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

    public ArrayList<Order> closedOrders = new ArrayList<>();

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


    public int addOrder(int price, int qty, String productType, OrderType orderType){
        Order order = Order.builder().price(price).orderType(orderType)
                .orderStatus(OrderStatus.OPEN)
                .id(idGenerator.getAndIncrement())
                .productType(productType).qty(qty).build();
        if(OrderType.BUY.equals(order.orderType)){
            buyOrders.putIfAbsent(productType, new HashMap<>());
            buyOrders.get(productType).putIfAbsent(price, new PriorityQueue<>(Comparator.comparingInt(Order::getId)));
            buyOrders.get(productType).get(price).offer(order);
        }else {
            sellOrders.putIfAbsent(productType, new HashMap<>());
            sellOrders.get(productType).putIfAbsent(price, new PriorityQueue<>(Comparator.comparingInt(Order::getId)));
            sellOrders.get(productType).get(price).offer(order);
        }
        return order.getId();
    }

    public List<Order> getBacklogOrders(String productType, Integer price) {
        List<Order> openOrders = new ArrayList<>();
        PriorityQueue<Order> buyOrdersQueue =  null;
        PriorityQueue<Order> sellOrdersQueue =  null;
        if(!CollectionUtils.isEmpty(buyOrders) && buyOrders.containsKey(productType)){
            buyOrdersQueue = buyOrders.get(productType).getOrDefault(price, null);
        }
        if(!CollectionUtils.isEmpty(sellOrders) && sellOrders.containsKey(productType)){
            sellOrdersQueue = sellOrders.get(productType).getOrDefault(price, null);
        }
        while (!buyOrdersQueue.isEmpty() && !sellOrdersQueue.isEmpty()) {
                int buyQty = buyOrdersQueue.peek().getQty();
                int sellQty = sellOrdersQueue.peek().getQty();
                int minQtyReConcillable = Math.min(sellQty, buyQty);

                buyOrdersQueue.peek().setQty(buyQty-minQtyReConcillable);
                sellOrdersQueue.peek().setQty(sellQty-minQtyReConcillable);

                if (buyOrdersQueue.peek().getQty() == 0) {
                    buyOrdersQueue.peek().setOrderStatus(OrderStatus.CLOSED);
                    closedOrders.add(buyOrdersQueue.peek());
                    buyOrdersQueue.poll();
                }
                if (sellOrdersQueue.peek().getQty() == 0){
                    sellOrdersQueue.peek().setOrderStatus(OrderStatus.CLOSED);
                    closedOrders.add(sellOrdersQueue.peek());
                    sellOrdersQueue.poll();
                }
        }
        for (Order o :  sellOrdersQueue)
            openOrders.add(o);
        for (Order o : buyOrdersQueue)
            openOrders.add(o);
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
    public void getOpenOrders(){
        int id = addOrder(12, 4, "maza", OrderType.BUY);
        Assert.assertEquals(id,1000);
        int id2 = addOrder(12, 2, "maza", OrderType.SELL);
        Assert.assertEquals(id2,1001);
        List<Order> openorder = getBacklogOrders("maza", 12);
        int openOrderId = openorder.get(0).getId();
        Assert.assertEquals(openOrderId, 1000);
    }
}
