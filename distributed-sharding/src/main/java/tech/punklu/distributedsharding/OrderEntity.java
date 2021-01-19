package tech.punklu.distributedsharding;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_order_0")
public class OrderEntity {

    @Id
    private Long orderId;

    private Integer userId;

}
