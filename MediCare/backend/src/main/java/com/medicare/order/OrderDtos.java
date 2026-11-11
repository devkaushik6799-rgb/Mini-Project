package com.medicare.order;
import jakarta.validation.constraints.*;
import java.util.List;
public class OrderDtos {
 public record ItemRequest(@NotNull Long medicineId,@NotNull @Min(1) @Max(20) Integer quantity){}
 public record CreateOrderRequest(@NotBlank String customerName,@Email @NotBlank String customerEmail,@NotEmpty List<@NotNull ItemRequest> items){}
}
