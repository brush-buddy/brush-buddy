package com.a205.brushbuddy.machine.domain;


import com.a205.brushbuddy.user.domain.User;
import com.a205.brushbuddy.workplace.domain.Workplace;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@Table(name = "machine")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machine_id")
    private Long machineId; // 기기ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workplace_id", referencedColumnName = "workplace_id", nullable = false)
    private Workplace workplaceId; // 사업장 계정 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 사용 중인 사용자 ID

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('personal','admin')", name = "owner", nullable = false)
    private OwnerType owner; // 소유자 (personal or business)

    @Column(name = "machine_paint_amount")
    @ColumnDefault("0")
    private Integer machinePaintAmount; // 기기 도장 양
}
