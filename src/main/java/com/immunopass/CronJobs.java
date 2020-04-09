package com.immunopass;

import com.immunopass.enums.OrderStatus;
import com.immunopass.model.Order;
import com.immunopass.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(value = "cronInstance", havingValue = "true", matchIfMissing = false)
public class CronJobs {
    private static final Logger logger = LoggerFactory.getLogger(CronJobs.class);
    private final OrderService orderService;

    @Value("${cronInstance}")
    private boolean isCronInstance;

    public CronJobs(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(fixedDelay = 5000, initialDelay = 5000)
    public void cronJobSch() {

        if (!isCronInstance) {
            logger.debug("Not a cron instance");
            return;
        }

        logger.debug("Running the cron");

        List<Order> orders = orderService.getOrdersHavingStatus(OrderStatus.CREATED);
        logger.debug("Found orders for creating vouchers " + orders.size());
        for (Order order : orders) {
            try {
                orderService.createVouchers(order.getId());
            } catch (Exception e) {
                logger.error("Error creating vouchers " + e.getLocalizedMessage());
            }
        }

        orders = orderService.getOrdersHavingStatus(OrderStatus.PROCESSING);
        logger.debug("Found orders for processing vouchers " + orders.size());
        for (Order order : orders) {
            try {
                orderService.processOrder(order.getId());
            } catch (Exception e) {
                logger.error("Error processing vouchers " + e.getLocalizedMessage());
            }
        }
    }
}
