package com.a205.brushbuddy.workplace.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "workplace")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Workplace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workplace_id")
    private Long workplaceId; // 기기ID

    @Column(name = "workplace_paint_price", nullable = false)
    private Short workplacePaintPrice; // 정량 당 물감 가격

    @Column(name = "workplace_address", length = 1000)
    private String workplaceAddress; // 사업장 주소

    @Column(name = "workplace_name", length = 30)
    private String workplaceName; // 사업장 이름

    @Column(name = "workplace_revenue")
    @ColumnDefault("0")
    private Long workplaceRevenue; // 사업장 수익
}
