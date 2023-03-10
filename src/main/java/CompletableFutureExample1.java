import meetingroomscheduler.User;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class CompletableFutureExample1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // background process
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("I'll run in a separate thread than the main thread.");
        });

        // Block and wait for the future to complete
        future.get();

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return "Result of the asynchronous computation";
            }
        });

        // Block and get the result of the Future
        String result = future2.get();
        System.out.println(result);

        CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        }).thenApply(name -> {
            return "Hello " + name;
        }).thenApply(greeting -> {
            return greeting + ", Welcome to the CalliCoder Blog";
        });

        System.out.println(welcomeText.get());

//        CompletableFuture.supplyAsync(() -> {
//            return ProductService.getProductDetail(productId);
//        }).thenAccept(product -> {
//            System.out.println("Got product detail from remote service " + product.getName())
//        });
//
        CompletableFuture.supplyAsync(() -> {
            // Run some computation
            return "result";
        }).thenRun(() -> {
            // Computation Finished.
        });

        CompletableFuture.supplyAsync(() -> {
            return "Some Result";
        }).thenApplyAsync(result1 -> {
            // Executed in a different thread from ForkJoinPool.commonPool()
            return "Processed Result";
        });

        CompletableFuture<CompletableFuture<Double>> result2 = getUsersDetail("userId")
                .thenApply(user -> getCreditRating(user));

        CompletableFuture<Double> result3 = getUsersDetail("userId")
                .thenCompose(user -> getCreditRating(user));

        CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 65.0;
        });

        System.out.println("Retrieving height.");
        CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 177.8;
        });
        CompletableFuture<Double> combinedFuture = weightInKgFuture
                .thenCombine(heightInCmFuture, (weightInKg, heightInCm) -> {
                    Double heightInMeter = heightInCm/100;
                    return weightInKg/(heightInMeter*heightInMeter);
                });
    }

    static CompletableFuture<User> getUsersDetail(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            return UserService.getUserDetail(userId);
        });
    }

    static CompletableFuture<Double> getCreditRating(User user) {
        return CompletableFuture.supplyAsync(() -> {
            return CreditRatingService.getCreditRating(user);
        });
    }

    private static class UserService {
        public static User getUserDetail(String userId) {
            return new User();
        }

    }

    private static class CreditRatingService {
        public static Double  getCreditRating(User user) {
            return Double.valueOf(2);
        }
    }
}