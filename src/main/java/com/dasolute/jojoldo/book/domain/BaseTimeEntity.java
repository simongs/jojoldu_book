package com.dasolute.jojoldo.book.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA 엔티티 들이 해당 클래스를 상속하면 필드들도 컬럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) //해당 클래스에 Auditing 기능을 활성화합니다.
public class BaseTimeEntity {

	@CreatedDate
	private LocalDateTime createdDate;

	@LastModifiedDate
	private LocalDateTime modifiedDate;
}
